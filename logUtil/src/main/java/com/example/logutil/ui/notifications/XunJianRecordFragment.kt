package com.example.smartcity_1.ui.notifications

import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import kotlinx.android.synthetic.main.xunjian_item.view.*
import kotlinx.android.synthetic.main.xunjianrecordfragment.*
import java.text.SimpleDateFormat
import kotlin.random.Random

class XunJianRecordFragment : BaseFragment(R.layout.xunjianrecordfragment) {
    override fun initViews() {
        val arr = arrayOf("擦窗户", "通风", "洗衣服", "按摩")
        val mutableListOf = mutableListOf<Map<String, String>>()
        val sdf = SimpleDateFormat("yyyy-MM")
        repeat(10) {
            mutableListOf.add(mapOf("a" to "${sdf.format(System.currentTimeMillis())}-$it","b" to arr[Random.nextInt(4)],"c" to Random.nextDouble(5.0).toString()))
        }
        listView.adapter = object : SimpleAdapter(mContext,mutableListOf,R.layout.xunjian_item, arrayOf("a","b"),
            intArrayOf(R.id.text1,R.id.text2)){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                return super.getView(position, convertView, parent).apply {
                    this.rb.rating = mutableListOf[position]["c"].toString().toFloat()
                }
            }
        }
    }
}