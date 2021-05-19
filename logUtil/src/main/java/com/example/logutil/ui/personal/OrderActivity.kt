package com.example.smartcity_1.ui.personal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.bean.Order
import com.example.smartcity_1.ui.home.onTab
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.order_item.view.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setTitle("订单列表")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Ok.get("/userinfo/orders/list?pageNum=1&userId=${AppClient.userInfo?.userId}") {
            val orders = gson.fromJson<MutableList<Order>>(it.optString("data"),object :
                TypeToken<MutableList<Order>>(){}.type)
            val unPaid = mutableListOf<Order>()
            val paid = mutableListOf<Order>()
            for (order in orders) {
                if(order.status == 0)unPaid.add(order) else paid.add(order)
            }
            val adapter = MyAdapter<Order>(unPaid, R.layout.order_item) { a, b, c->
                a.apply {
                    textView7.text = "订单号：${b.orderNum}"
                    textView14.text = "订单日期：${b.createTime}"
                }

            }
            listView.apply {
                this.adapter = adapter
                setOnItemClickListener { parent, view, position, id ->
                    startActivity(Intent(this@OrderActivity,OrderInfoActivity::class.java).apply {
                        putExtra("A",adapter.datas[position])
                    })
                }
            }
            tabLayout.onTab {
                adapter.datas =
                if(it.position == 0) unPaid else paid
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}