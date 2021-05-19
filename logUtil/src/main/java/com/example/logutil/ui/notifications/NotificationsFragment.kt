package com.example.smartcity_1.ui.notifications

import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyBannerAdapter
import com.example.smartcity_1.ui.BaseFragment
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : BaseFragment(R.layout.fragment_notifications){
    override fun initViews() {
        val imgs = arrayOf(
            R.drawable.yanglao1,
            R.drawable.yanglao2,
            R.drawable.yanglao3,
            R.drawable.yanglao4,
            R.drawable.yanglao5
        )
        banner.apply {
            addBannerLifecycleObserver(this@NotificationsFragment)
            indicator = CircleIndicator(mContext)

            adapter = MyBannerAdapter(imgs.toMutableList() as List<Any>?)
        }
        val titles = arrayOf("养老机构查询", "健康评估", "预约养老院", "平台监测", "巡检记录", "集中监测")
        listView.adapter = ArrayAdapter(mContext,R.layout.simple_textviwe_item_2,titles)
        listView.setOnItemClickListener { parent, view, position, id ->
            when(position){
                0 -> findNavController().navigate(R.id.yangLaoJiGouChaXunFragment)
                1 -> goTo(R.id.healthPinGuFragment)
                2 -> goTo(R.id.yuYueYangLaoYuanFragment)
                3 -> goTo(R.id.pinTaiJianCeFragment)
                4 -> goTo(R.id.xunJianRecordFragment)
                5 -> goTo(R.id.jiZhongJianCeFragment)
            }
        }
    }

}