package com.example.smartcity_1.ui.notifications

import android.app.AlertDialog
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.home.onTab
import com.example.smartcity_1.ui.personal.getTexts
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.yanglaoyuaninfofragment.*
import kotlinx.android.synthetic.main.yuyue_dialog_item.view.*

class YangLaoYuanInfoFragment : BaseFragment(R.layout.yanglaoyuaninfofragment) {
    override fun initViews() {
        arguments?.get("A")?.let {
            imageView.setImageResource(it.toString().toInt())
        }
        textView.text = "随着经济的发展，人民的生活水平得到普遍提高，随之而来的是我国的老龄化程度越来越高，人们已经普遍意识到老龄化问题将会带来的问题。整个社会在趋向于“衰老型”发展，人口老龄化的问题日益严重，空巢老人的现象也日益加剧。呈现出老年人口基数大、增速快、高龄化、失能化、空巢化趋势明显的态势，再加上我国未富先老的国情和家庭小型化的结构叠加在一起，养老问题异常严峻。智慧养老平台主要围绕着利用先进的信息技术手段实现“以入住老人为中心，规范养老服务，强化养老管理”，同时，针对老年人心理生理特点，以信息化技术为核心，采用先进的计算机技术、通信技术、无线传输技术、控制技术，为老人提供一个安全、便捷、高效、舒适的养老综合服务，具体功能如下。"
        val str1 = "设施一\n设施二\n设施三\n设施四\n设施五"
        val str2 = "服务体系一\n服务体系二\n服务体系三\n服务体系四\n服务体系五"
        textView2.text = str1
        tabLayout.onTab {
            textView2.text =
            if (it.position == 0) {
                str1
            }else str2
        }
        button.setOnClickListener {
            AlertDialog.Builder(mContext).apply {
                setTitle("预约")
                val inflate = layoutInflater.inflate(R.layout.yuyue_dialog_item, null, false)
                setView(inflate)
                val create = create()
                inflate.button1.setOnClickListener { create.dismiss() }
                inflate.button2.setOnClickListener {
                    val texts = getTexts(inflate.et1, inflate.et2, inflate.et3, inflate.et4)
                    if (texts.size == 4) {
                        "预约成功".show()
                        create.dismiss()
                    }
                }
                create.show()
                create.setCancelable(false)
            }
        }
    }
}