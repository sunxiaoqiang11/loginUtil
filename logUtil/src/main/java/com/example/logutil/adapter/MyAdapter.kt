package com.example.smartcity_1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.smartcity_1.bean.News

class MyAdapter<T>(
    var datas:MutableList<T>,
    val layoutId:Int,
    val block: (View, T, Int) -> Unit
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view:View?=null
        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(layoutId,parent,false)
        }
        if (view != null) {
            block(view,getItem(position),position)
        }
        return view
    }

    override fun getItem(position: Int): T = datas[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = datas.size
}