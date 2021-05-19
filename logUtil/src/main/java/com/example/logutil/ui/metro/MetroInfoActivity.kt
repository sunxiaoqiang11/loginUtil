package com.example.smartcity_1.ui.metro

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Metro
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import kotlinx.android.synthetic.main.activity_metro_info.*
import kotlinx.android.synthetic.main.lines_item.view.*

class MetroInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.extras?.get("A")?.let {
            (it as Map<*,*>).let {
                setTitle(it["lineName"].toString())
                val lineId = it["lineId"].toString()
                Ok.get("/metro/$lineId") {
                    val metro = gson.fromJson(it.optString("data"),Metro::class.java)
                    textView9.text = metro.metroStepsList.first().name
                    textView10.text = metro.metroStepsList.last().name
                    textView11.text = "剩余时间：${metro.remainingTime}分钟"
                    textView12.text = "间隔：${metro.stationsNumber}站"
                    textView13.text = "剩余距离：${metro.km}KM"
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MetroInfoActivity,LinearLayoutManager.HORIZONTAL,false)
                        this.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
                            override fun onCreateViewHolder(
                                parent: ViewGroup,
                                viewType: Int
                            ): RecyclerView.ViewHolder = object :RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.lines_item,parent,false)){}

                            override fun getItemCount(): Int = metro.metroStepsList.size

                            override fun onBindViewHolder(
                                holder: RecyclerView.ViewHolder,
                                position: Int
                            ) {
                                holder.itemView.apply {
                                    val name = metro.metroStepsList[position].name
                                    textView.text =name
                                    if (name == metro.runStationsName) {
                                        imageView.visibility = View.VISIBLE
                                    }else imageView.visibility = View.GONE
                                }
                            }

                        }
                        smoothScrollToPosition(metro.metroStepsList.indexOfFirst { it.name == metro.runStationsName })
                    }

                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}