package com.example.smartcity_1.ui.personal

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.smartcity_1.R
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.activity_personal_info.*
import kotlinx.android.synthetic.main.activity_personal_info.view.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PersonalInfoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)
        setTitle("个人信息")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        AppClient.userInfo?.let {
            Ok.setImage(it.avatar,imageView)
            etNickName.setText(it.nickName)
            etUserName.setText(it.userName)
            etTel.setText(it.phonenumber)
            it.email.let { etEmail.setText(it) }
            it.idCard?.let {
                etIdCard.setText(StringBuilder(it).replace(2,14,"************"))
                etIdCard.isEnabled = false
            }
            rgSex.check(if(it.sex=="0")R.id.rbWoMan else R.id.rbMan)
            imageView.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE),999)
                }else startActivityForResult(Intent(Intent.ACTION_PICK).apply { setType("image/*") },888)
            }
            button.setOnClickListener {
                val texts = getTexts(etIdCard, etEmail,etNickName, etTel)
                if (texts.size == 4) {
                    /**
                     * "userId"="2",
                    "idCard"="211224198525625254",
                    "nickName"=吴霄",
                    "email"="123456@qq.com",
                    "phonenumber"="17767746537",
                    "sex"="2",
                    "file"=(file)
                     */
                    val multipartBody = MultipartBody.Builder()
                        .addFormDataPart("email", texts[1])
                        .addFormDataPart("nickName", texts[2])
                        .addFormDataPart("phonenumber", texts[3])
                        .addFormDataPart("sex", if (rbMan.isChecked) "1" else "0")
                        .addFormDataPart("userId", AppClient.userInfo?.userId.toString())
                    if (etIdCard.isEnabled) {
                        multipartBody.addFormDataPart("idCard",texts[0])
                    }
                    imgFile?.let {
                        multipartBody.addFormDataPart("file","abc.jpeg",it.asRequestBody("application/octet-stream".toMediaType()))
                    }
                    Ok.post("/system/user/updata",multipartBody.build()){
                        "修改成功，请重新登录后更新数据".show()
                        AppClient.userInfo = null
                        finish()
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
    var imgFile:File?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                imageView.setImageURI(it)
                val arr = arrayOf(MediaStore.Images.Media.DATA)
                contentResolver?.query(it,arr,null,null,null)?.let {
                    if (it.moveToFirst()) {
                        imgFile = File(it.getString(it.getColumnIndexOrThrow(arr[0])))
                    }
                }
            }
        }
    }
}

fun getTexts(vararg views: View): MutableList<String> {
    val mutableListOf = mutableListOf<String>()
    for (view in views) {
        val text =
            when (view) {
                is TextView -> view.text.toString().trim()
                is EditText -> view.text.toString().trim()
                else -> null
            }
        if (text.isNullOrEmpty()) {
            "输入框内容不能为空".show()
        }else mutableListOf.add(text)
    }
    return mutableListOf
}