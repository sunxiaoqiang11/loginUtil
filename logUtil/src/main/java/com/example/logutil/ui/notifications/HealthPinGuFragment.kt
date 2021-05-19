package com.example.smartcity_1.ui.notifications

import android.app.AlertDialog
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import kotlinx.android.synthetic.main.healthpingufragment.*

class HealthPinGuFragment : BaseFragment(R.layout.healthpingufragment) {
    override fun initViews() {
        val arr = arrayOf(100, 300, 500, 800)
        button.setOnClickListener {
            val money = arr[s1.selectedItemPosition] + arr[s2.selectedItemPosition] + arr[s3.selectedItemPosition] + arr[s4.selectedItemPosition]
            AlertDialog.Builder(mContext).apply {
                setTitle("评估结果")
                setMessage("根据您填写的信息评估得出，您需要以下配套服务：\n" +
                        "配置一 + 配置二 + 配置三\n" +
                        "服务费用：$money")
                setPositiveButton("关闭",null)
            }.show()
        }
    }
}