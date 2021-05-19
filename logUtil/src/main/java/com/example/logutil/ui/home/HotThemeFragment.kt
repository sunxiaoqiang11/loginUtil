package com.example.smartcity_1.ui.home

import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.Ok
import kotlinx.android.synthetic.main.hotthemefragment.*
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.news_item.view.imageView

class HotThemeFragment : BaseFragment(R.layout.hotthemefragment) {
    override fun initViews() {
        getB().let {
            Ok.setImage(it["imgUrl"].toString(), imageView)
            it["content"]?.let { textView.text = it.toString() }
            it["title"]?.let { setTitle(it.toString()) }

        }
    }
}