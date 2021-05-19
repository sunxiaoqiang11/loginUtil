    package com.example.smartcity_1.ui.home

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.adapter.MyBannerAdapter
import com.example.smartcity_1.adapter.Sadapter
import com.example.smartcity_1.bean.News
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.*
import com.google.android.material.tabs.TabLayout
import com.google.gson.reflect.TypeToken
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.news_item.view.*
import org.json.JSONObject

fun JSONObject.mapList(name: String = "rows") = gson.fromJson<MutableList<Map<String, Any>>>(
    optString(name),
    type
)

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    override fun initViews() {
        homeSearch.onSearch { q ->
            Ok.get("/press/press/list") {
                val news = gson.fromJson<MutableList<News>>(it.optString("rows"),
                    object : TypeToken<MutableList<News>>() {}.type)
                val mutableListOf = mutableListOf<News>()
                for (new in news) {
                    if (new.title.contains(q)) mutableListOf.add(new)
                }
                if (mutableListOf.isEmpty()) {
                    "没有搜索到任何内容".show()
                } else goTo(R.id.searchNewsFragment, mutableListOf.toBundler())
            }
        }
        Ok.get("/userinfo/rotation/list?pageNum=1&pageSize=10&type=45") {
            banner.apply {
                addBannerLifecycleObserver(this@HomeFragment)
                indicator = CircleIndicator(mContext)
                adapter = MyBannerAdapter(it.mapList() as List<Any>?)
                setOnBannerListener { any, i ->
                    goTo(R.id.hotThemeFragment, any.toBundler())
                }
            }
        }
        Ok.get("/service/service/list?pageNum=1&pageSize=10") {
            val mapList = it.mapList()
            if (mapList.size > 9) {
                mapList.removeAt(9)
            } else {
                mapList.add(mapOf("serviceName" to "找工作", "imgUrl" to R.drawable.gengduo))
                mapList.add(mapOf("serviceName" to "堵车移车", "imgUrl" to R.drawable.gengduo))
            }
            mapList.add(mapOf("serviceName" to "更多服务", "imgUrl" to R.drawable.gengduo))
            gridView1.apply {
                adapter = Sadapter(
                    mContext, mapList, R.layout.services_item, arrayOf("serviceName", "imgUrl"),
                    intArrayOf(R.id.textView, R.id.imageView)
                )
                setOnItemClickListener { parent, view, position, id ->
                    from(mapList[position]["serviceName"].toString())
                }
            }
        }
        Ok.get("/press/press/list?pageNum=1&pageSize=10&pressCategory=48") {
            val mapList = it.mapList()
            gridView2.apply {
                adapter = Sadapter(
                    mContext, mapList, R.layout.hottheme_item, arrayOf("title", "imgUrl"),
                    intArrayOf(R.id.textView, R.id.imageView)
                )
                setOnItemClickListener { parent, view, position, id ->
                    goTo(R.id.hotThemeFragment, mapList[position].toBundler())
                }
            }
        }
        Ok.get("/system/dict/data/type/press_category") {
            val mapList = it.mapList("data")
            tabLayout.apply {
                for (map in mapList) {
                    addTab(newTab().setText(map["dictLabel"].toString()).setTag(map["dictCode"]))
                }
                onTab {
                    onload(it.tag.toString())
                }
            }
        }
        onload("36")
    }

    fun onload(path: String) {
        Ok.get("/press/press/list?pageNum=1&pageSize=10&pressCategory=$path") {
            val news = gson.fromJson<MutableList<News>>(it.optString("rows"),
                object : TypeToken<MutableList<News>>() {}.type)
            listView.apply {
                adapter = MyAdapter<News>(news, R.layout.news_item) { a, b, c ->
                    a.apply {
                        Ok.setImage(b.imgUrl, imageView)
                        text1.text = b.title
                        text2.text = b.content
                        text3.text = "评论：${b.viewsNumber}\u3000\u3000时间：${b.createTime}"
                    }
                }

            }
        }
    }

}

fun SearchView.onSearch(block: (String) -> Unit) {
    isSubmitButtonEnabled = true
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { block(it) }
            setQuery("",false)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean = false
    })
}

fun TabLayout.onTab(block: (TabLayout.Tab) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let(block)
        }
    })
}

fun Any.toBundler(): Bundle {
    return bundleOf("A" to this)
}