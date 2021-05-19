package com.example.smartcity_1.ui.job

import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.bean.Deliver
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.google.android.material.snackbar.Snackbar
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.four_textview_item.view.*
import kotlinx.android.synthetic.main.jobrecordfragment.*

class JobRecordFragment : BaseFragment(R.layout.jobrecordfragment) {
    var snackbar:Snackbar?=null
    override fun initViews() {
        Ok.get("/userinfo/deliver/list?userId=${AppClient.userInfo?.userId}") {
            var delivers = gson.fromJson<MutableList<Deliver>>(it.optString("rows"),object : TypeToken<MutableList<Deliver>>(){}.type)
            if (delivers.isEmpty()) {
                snackbar = Snackbar.make(mView,"没有任何投递记录",Snackbar.LENGTH_INDEFINITE)
                snackbar?.show()
            }else{
                listView.adapter = MyAdapter<Deliver>(delivers, R.layout.four_textview_item) { a,b,c->
                    a.apply {
                        this.text1.text = "岗位名称：${b.postName}"
                        this.text2.text = "公司名称：${b.companyName}"
                        this.text3.text = "薪资：${b.money}"
                        this.text4.text = "投递时间：${b.satrTime}"
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        snackbar?.let { it.dismiss() }
    }
}