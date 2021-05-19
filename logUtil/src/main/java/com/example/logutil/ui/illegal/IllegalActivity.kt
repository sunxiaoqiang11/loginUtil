package com.example.smartcity_1.ui.illegal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Illegal
import com.example.smartcity_1.bean.News
import com.example.smartcity_1.ui.home.toBundler
import com.example.smartcity_1.ui.personal.getTexts
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.example.smartcity_1.utils.show
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_illegal.*

class IllegalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illegal)
        setTitle("违章查询")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        button.setOnClickListener {
            val texts = getTexts(et1, et2)
            if (texts.size == 2) {
                Ok.get("/userinfo/illegal/list?pageNum=1&catType=${s1.selectedItem}&engineNumber=${texts[1]}&licencePlate=${s2.selectedItem}${texts[0]}"){
                    val illegals = gson.fromJson<MutableList<Illegal>>(it.optString("rows"),object :
                        TypeToken<MutableList<Illegal>>(){}.type)
                    if (illegals.isEmpty()) {
                        "没有查询到任何数据".show()
                    }else {
                        startActivity(Intent(this,IllegalListActivity::class.java).apply {
                            putExtras(illegals.toBundler())
                        })
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