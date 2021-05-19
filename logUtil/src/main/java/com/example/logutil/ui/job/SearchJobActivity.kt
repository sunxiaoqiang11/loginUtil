package com.example.smartcity_1.ui.job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.home.onTab
import kotlinx.android.synthetic.main.activity_search_job.*

class SearchJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_job)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().add(R.id.fragment,SearchJobFragment()).commitNow()
        tabLayout.onTab {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment,
                when (it.position) {
                    0 -> SearchJobFragment()
                    1 -> JobRecordFragment()
                    else -> ResumeFragment()
                }
            ).commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}