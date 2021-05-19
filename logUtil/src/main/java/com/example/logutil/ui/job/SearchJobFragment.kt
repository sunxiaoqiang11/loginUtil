package com.example.smartcity_1.ui.job

import android.widget.SimpleAdapter
import android.widget.TextView
import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.adapter.MyBannerAdapter
import com.example.smartcity_1.bean.Post
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.home.mapList
import com.example.smartcity_1.ui.home.onSearch
import com.example.smartcity_1.ui.home.toBundler
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.example.smartcity_1.utils.show
import com.google.gson.reflect.TypeToken
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.searchjobfragment.*

class SearchJobFragment : BaseFragment(R.layout.searchjobfragment) {
    override fun initViews() {
        val imgs = arrayOf(
            R.drawable.zgz1,
            R.drawable.zgz2,
            R.drawable.zgz3,
            R.drawable.zgz7
        )
        jobBanner.apply {
            addBannerLifecycleObserver(this@SearchJobFragment)
            indicator = CircleIndicator(mContext)
            adapter = MyBannerAdapter(imgs.toMutableList() as List<Any>?)
        }
        jobSearch.onSearch {
            onload("/userinfo/post/list?pageNum=1&pageSize=2&name=$it")
        }
        Ok.get("/userinfo/profession/list") {
            val mapList = it.mapList()
            gridView.adapter = SimpleAdapter(mContext,mapList,R.layout.remen_item, arrayOf("professionName"),
                intArrayOf(R.id.text))
            gridView.setOnItemClickListener { parent, view, position, id ->
                onload("/userinfo/post/list?pageNum=1&pageSize=2&professionId=${position+1}")
            }
        }
        onload()

    }

    fun onload(path: String = "/userinfo/post/list?pageNum=1&pageSize=10") {
        Ok.get(path) {
            val posts = gson.fromJson<MutableList<Post>>(it.optString("rows"),
                object : TypeToken<MutableList<Post>>() {}.type)
            if (posts.isEmpty()) {
                "没有查询到任何信息".show()
            }
            listView.apply {
                adapter = MyAdapter<Post>(posts, R.layout.simple_textviwe_item) { a,b,c->
                    (a as TextView).setText(b.name)
                }
                setOnItemClickListener { parent, view, position, id ->
                    parentFragmentManager.beginTransaction().replace(R.id.fragment,JobInfoFragment::class.java,posts[position].toBundler()).addToBackStack(null).commit()
                }
            }

        }

    }
}