package com.example.smartcity_1.ui.notifications

import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import kotlinx.android.synthetic.main.yanglao_item.view.*
import kotlinx.android.synthetic.main.yanglaojigouchaxunfragment.*
import kotlin.random.Random

class YangLaoJiGouChaXunFragment : BaseFragment(R.layout.yanglaojigouchaxunfragment) {
    override fun initViews() {
        val imgs = arrayOf(
            R.drawable.yanglao1,
            R.drawable.yanglao2,
            R.drawable.yanglao3,
            R.drawable.yanglao4,
            R.drawable.yanglao5
        )
        val datas = mutableListOf<Map<String, Any>>()
        for (img in imgs) {
            datas.add(mapOf("name" to "~~~养老院~~~","level" to Random.nextDouble(5.0),"img" to img))
            listView.adapter = object : SimpleAdapter(mContext,datas,R.layout.yanglao_item, arrayOf("name","img"),
                intArrayOf(R.id.textView16,R.id.imageView5)){
                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                    return super.getView(position, convertView, parent).apply {
                        this.ratingBar.rating = datas[position]["level"].toString().toFloat()
                    }
                }
            }
            listView.setOnItemClickListener { parent, view, position, id ->
                goTo(R.id.pingJiaFragment)
            }
        }
    }
}