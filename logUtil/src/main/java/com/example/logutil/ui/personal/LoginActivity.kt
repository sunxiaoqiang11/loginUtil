package com.example.smartcity_1.ui.personal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.UserInfo
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.passwordfragment.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("登录")
        button.setOnClickListener {
            val texts = getTexts(et1, et2)
            if (texts.size == 2) {
                Ok.post("/login", mapOf(
                    "username" to texts.first(),
                    "password" to texts.last()
                )){
                    getSharedPreferences("config",Context.MODE_PRIVATE).edit {
                        putString("username",texts.first())
                        putString("password",texts.last())

                    }
                    Ok.token = it.optString("token")
                    Ok.get("/getInfo") {
                        AppClient.userInfo = gson.fromJson(it.optString("user"), UserInfo::class.java)
                        "登录成功".show()
                        finish()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}