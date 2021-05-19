package com.example.smartcity_1.utils

import android.app.Application
import android.content.Context
import com.example.smartcity_1.bean.UserInfo

class AppClient : Application() {
    companion object{
        lateinit var mContext: Context
        var userInfo:UserInfo?=null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        val sp = getSharedPreferences("config", Context.MODE_PRIVATE)
        Ok.post("/login", mapOf(
            "username" to sp.getString("username","xiaok"),
            "password" to sp.getString("password","123456")
        )){
            Ok.token = it.optString("token")
            Ok.get("/getInfo") {
                userInfo = gson.fromJson(it.optString("user"), UserInfo::class.java)
            }
        }
    }
}