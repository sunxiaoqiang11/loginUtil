package com.example.smartcity_1.ui.notifications

import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.home.toBundler
import kotlinx.android.synthetic.main.yuyueyanglaoyuanfragment.*
import kotlin.random.Random

class YuYueYangLaoYuanFragment : BaseFragment(R.layout.yuyueyanglaoyuanfragment) {
    override fun initViews() {
        val imgs = arrayOf(
            R.drawable.yanglao1,
            R.drawable.yanglao2,
            R.drawable.yanglao3,
            R.drawable.yanglao4,
            R.drawable.yanglao5
        )
        val str = "随着经济的发展，人民的生活水平得到普遍提高，随之而来的是我国的老龄化程度越来越高，人们已经普遍意识到老龄化问题将会带来的问题。整个社会在趋向于“衰老型”发展，人口老龄化的问题日益严重，空巢老人的现象也日益加剧。呈现出老年人口基数大、增速快、高龄化、失能化、空巢化趋势明显的态势，再加上我国未富先老的国情和家庭小型化的结构叠加在一起，养老问题异常严峻。智慧养老平台主要围绕着利用先进的信息技术手段实现“以入住老人为中心，规范养老服务，强化养老管理”，同时，针对老年人心理生理特点，以信息化技术为核心，采用先进的计算机技术、通信技术、无线传输技术、控制技术，为老人提供一个安全、便捷、高效、舒适的养老综合服务，具体功能如下。"
        val datas = mutableListOf<Map<String, Any>>()
        for (img in imgs) {
            datas.add(mapOf("name" to "~~~养老院~~~","content" to str,"img" to img))
            listView.adapter = SimpleAdapter(mContext,datas,R.layout.yanglao_item_2, arrayOf("name","content","img"),
                intArrayOf(R.id.textView16,R.id.textView17,R.id.imageView5))
            listView.setOnItemClickListener { parent, view, position, id ->
                goTo(R.id.yangLaoYuanInfoFragment,datas[position]["img"]?.toBundler())
            }
        }
    }
}