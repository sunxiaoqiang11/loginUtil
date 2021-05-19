package com.example.smartcity_1.ui.illegal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Illegal
import kotlinx.android.synthetic.main.activity_illegal_info.*

class IllegalInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illegal_info)
        setTitle("违法详情")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.getSerializableExtra("A")?.let {
            (it as Illegal).let {
                val arr = arrayOf(
                    mapOf("a" to "违法时间", "b" to it.badTime),
                    mapOf("a" to "违法地点", "b" to it.illegalSites),
                    mapOf("a" to "违法行为", "b" to it.trafficOffence),
                    mapOf("a" to "通知书号", "b" to it.noticeNo),
                    mapOf("a" to "违章记分", "b" to it.deductMarks),
                    mapOf("a" to "罚款金额", "b" to "${it.money}元")
                )
                listView.adapter = SimpleAdapter(this,arr.toMutableList(),R.layout.two_textview_item,
                    arrayOf("a","b"), intArrayOf(R.id.text1,R.id.text2))
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}