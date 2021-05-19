package com.example.smartcity_1.ui.illegal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartcity_1.R
import com.example.smartcity_1.adapter.MyAdapter
import com.example.smartcity_1.bean.Illegal
import com.example.smartcity_1.ui.home.toBundler
import kotlinx.android.synthetic.main.activity_illegal_list.*
import kotlinx.android.synthetic.main.illegal_item.view.*

class IllegalListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illegal_list)
        setTitle("违章记录")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (intent?.extras?.get("A") as MutableList<Illegal>).let {
            var mutableListOf = mutableListOf<Illegal>()
            if (it.size > 6) {
                mutableListOf = it.subList(0,6)
            }else{
                mutableListOf = it
                btMore.isEnabled = false
            }
            val adapter = MyAdapter(mutableListOf, R.layout.illegal_item) { a, b, c ->
                a.apply {
                    text1.text = "违法时间：${b.badTime}"
                    text2.text = "违章地点：${b.illegalSites}"
                    text3.text = "违章记分：${b.deductMarks}"
                    text4.text = "罚款金额：${b.money}"
                    text5.text = "处理状态：${if (b.disposeState == "0") "未处理" else "已处理"}"
                }
            }
            listView.adapter = adapter
            listView.setOnItemClickListener { parent, view, position, id ->
                startActivity(Intent(this,IllegalInfoActivity::class.java).apply {
                    putExtra("A",mutableListOf[position])
                })
            }
            btMore.setOnClickListener {_->
                adapter.datas = it
                adapter.notifyDataSetChanged()
            }
        }
//        intent?.getParcelableArrayExtra("A")?.let {
//            var illegals = mutableListOf<Illegal>()
//            if (it.size > 6) {
//                illegals = (it.toMutableList() as MutableList<Illegal>).subList(0,6)
//            } else {
//                illegals = it.toMutableList() as MutableList<Illegal>
//                btMore.isEnabled = false
//            }
//
//            btMore.setOnClickListener {_->
//                adapter.datas = it.toMutableList() as MutableList<Illegal>
//                adapter.notifyDataSetChanged()
//            }
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}