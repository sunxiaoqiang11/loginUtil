package com.example.smartcity_1.ui.personal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Order
import kotlinx.android.synthetic.main.activity_order_info.*

class OrderInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_info)
        setTitle("订单详情")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.getParcelableExtra<Order>("A")?.let {
            val arr = arrayOf(
                mapOf("a" to "订单号", "b" to it.orderNum),
                mapOf("a" to "路线", "b" to it.path),
                mapOf("a" to "起点", "b" to it.start),
                mapOf("a" to "终点", "b" to it.end),
                mapOf("a" to "价格", "b" to it.price),
                mapOf("a" to "用户", "b" to it.userName),
                mapOf("a" to "联系方式", "b" to it.userTel),
                mapOf("a" to "支付状态", "b" to if (it.status == 0) "未支付" else "已支付")
            )
            listView.adapter = SimpleAdapter(this,arr.toMutableList(),R.layout.two_textview_item,
                arrayOf("a","b"), intArrayOf(R.id.text1,R.id.text2))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}