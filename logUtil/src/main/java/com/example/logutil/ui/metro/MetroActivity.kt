package com.example.smartcity_1.ui.metro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.home.mapList
import com.example.smartcity_1.ui.home.toBundler
import com.example.smartcity_1.utils.Ok
import kotlinx.android.synthetic.main.activity_metro.*

class MetroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro)
        setTitle("城市地铁")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Ok.get("/metro/list?pageNum=1&pageSize=10&currentName=建国门") {
            val mapList = it.mapList()
            listView.adapter = SimpleAdapter(this,mapList,R.layout.metro_item, arrayOf("lineName","lastName","reachTime"),
                intArrayOf(R.id.textView2,R.id.textView4,R.id.textView6))
            listView.setOnItemClickListener { parent, view, position, id ->
                startActivity(Intent(this,MetroInfoActivity::class.java).apply { putExtras(mapList[position].toBundler()) })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.zonglan) {
            startActivity(Intent(this,ZongLanActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.metro_menu,menu)
        return true
    }
}