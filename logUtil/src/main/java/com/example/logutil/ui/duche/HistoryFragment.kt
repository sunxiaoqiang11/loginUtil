package com.example.smartcity_1.ui.duche

import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.bean.CarAction
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.historyfragment.*
import kotlinx.android.synthetic.main.three_textview_item.view.*

class HistoryFragment : BaseFragment(R.layout.historyfragment) {
    override fun initViews() {
        Ok.get("/userinfo/car/list?userId=${AppClient.userInfo?.userId}") {
            val carActions = gson.fromJson<MutableList<CarAction>>(it.optString("rows"),object :
                TypeToken<MutableList<CarAction>>(){}.type)
            listView.adapter = MyAdapter<CarAction>(carActions, R.layout.three_textview_item) { a,b,c->
                a.apply {
                    this.text1.text = "车牌号：${b.plates}"
                    this.text2.text = "车主电话：${b.tel}"
                    this.text3.text = "地址：${b.address}"
                }
            }



        }
    }
}