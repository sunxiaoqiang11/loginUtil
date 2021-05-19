package com.example.smartcity_1.ui.duche

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.home.onTab
import kotlinx.android.synthetic.main.activity_duche.*

class DuCheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duche)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("堵车移车")
        supportFragmentManager.beginTransaction().add(R.id.fragment,ZiZhuFragment()).commitNow()
        tabLayout.onTab {
            supportFragmentManager.beginTransaction().replace(R.id.fragment,
            if(it.position==0)ZiZhuFragment() else HistoryFragment()).commit()
            setTitle(it.text)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}