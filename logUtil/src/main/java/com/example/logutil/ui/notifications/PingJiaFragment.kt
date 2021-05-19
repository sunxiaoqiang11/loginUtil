package com.example.smartcity_1.ui.notifications

import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.personal.getTexts
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.pingjiafragment.*

class PingJiaFragment : BaseFragment(R.layout.pingjiafragment) {
    override fun initViews() {
        button.setOnClickListener {
            val texts = getTexts(et)
            if (texts.isNotEmpty()) {
                "评价成功".show()
                back()
            }
        }
    }
}