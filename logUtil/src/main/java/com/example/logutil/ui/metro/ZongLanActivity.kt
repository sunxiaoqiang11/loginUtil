package com.example.smartcity_1.ui.metro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity_1.R

class ZongLanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zong_lan)
        setTitle("总览图")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}