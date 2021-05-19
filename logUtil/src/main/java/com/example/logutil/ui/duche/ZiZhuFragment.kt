package com.example.smartcity_1.ui.duche

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.personal.getTexts
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.show
import com.lljjcoder.Interface.OnCityItemClickListener
import com.lljjcoder.bean.CityBean
import com.lljjcoder.bean.DistrictBean
import com.lljjcoder.bean.ProvinceBean
import com.lljjcoder.citywheel.CityConfig
import com.lljjcoder.style.citypickerview.CityPickerView
import kotlinx.android.synthetic.main.two_textview_item.view.*
import kotlinx.android.synthetic.main.zizhufragment.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class ZiZhuFragment : BaseFragment(R.layout.zizhufragment) {
    override fun initViews() {
        e5.setOnClickListener {
            CityPickerView().apply {
                this.init(mContext)
                setConfig(CityConfig.Builder().build())
                setOnCityItemClickListener(object : OnCityItemClickListener() {
                    override fun onSelected(
                        province: ProvinceBean?,
                        city: CityBean?,
                        district: DistrictBean?
                    ) {
                        super.onSelected(province, city, district)
                        e5.setText("$province-$city-$district")
                    }
                })
            }.showCityPicker()
        }
        imageView.setOnClickListener {
            try {
                if (ContextCompat.checkSelfPermission(
                        mContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE),666)
                }else startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),999)
            } catch (e: Exception) {
                "当前设备不支持调用相机".show()
            }
        }
        button.setOnClickListener {
            val texts = getTexts(e1, e2, e3, e4, e5, e6)
            if (texts.size == 6) {
                /**
                 * cardId=423016199812141526
                names=张三
                userId=2
                tel=18546235454
                address=高新区街道 10 号
                file=（file）
                plates=辽 F1M080
                 */
                val multipartBody = MultipartBody.Builder()
                    .addFormDataPart("plates", texts[0])
                    .addFormDataPart("names", texts[1])
                    .addFormDataPart("tel", texts[2])
                    .addFormDataPart("userId", AppClient.userInfo?.userId.toString())
                    .addFormDataPart("cardId", texts[3])
                    .addFormDataPart("address", "${texts[4]} ${texts[5]}")
                if (imgFile.exists()) {
                    multipartBody.addFormDataPart("file","abc.jpeg",imgFile.asRequestBody("application/octet-stream".toMediaType()))
                }
                Ok.post("/userinfo/car",multipartBody.build()){
                    if (it.has("data")) {
                        val data = it.optJSONObject("data")
                        AlertDialog.Builder(mContext).apply {
                            setTitle("车主信息")
                            val inflate =
                                layoutInflater.inflate(R.layout.two_textview_vertical_item, null, false)
                            setView(inflate)
                            val phone = data.optString("phone")
                            inflate.apply {
                                this.text1.text = "车牌号：${data.optString("licensePlate")}"
                                this.text2.text = "车主电话：${phone}"
                                this.text2.setOnClickListener {
                                    try {
                                        startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:$phone")))
                                    } catch (e: Exception) {
                                        "当前设备不支持打电话".show()
                                    }
                                }
                            }
                            setPositiveButton("关闭",null)
                        }.show()
                    }else{
                        "没有查询到车主信息".show()
                    }
                }
            }
        }
    }
    val imgFile = File("/sdcard/Pictures/${Random.nextInt()}.jpeg")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.extras?.get("data")?.let {
                (it as Bitmap).let {
                    imageView.setImageBitmap(it)
                    val bufferedOutputStream = BufferedOutputStream(FileOutputStream(imgFile))
                    it.compress(Bitmap.CompressFormat.JPEG,100,bufferedOutputStream)
                    bufferedOutputStream.flush()
                    bufferedOutputStream.close()
                }
            }
        }
    }
}