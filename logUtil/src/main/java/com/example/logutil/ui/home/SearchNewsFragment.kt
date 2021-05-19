package com.example.smartcity_1.ui.home

import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.bean.News
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.Ok
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.searchnewsfragment.*

class SearchNewsFragment : BaseFragment(R.layout.searchnewsfragment) {
    override fun initViews() {
        getA<MutableList<News>>().let {
            listView.adapter = MyAdapter(it, R.layout.news_item) { a, b, c->
                a.apply {
                    Ok.setImage(b.imgUrl,this.imageView)
                    text1.text = b.title
                    text2.text = b.content
                    text3.text = "评论：${b.viewsNumber}\u3000\u3000时间：${b.createTime}"
                }

            }
        }
    }
}