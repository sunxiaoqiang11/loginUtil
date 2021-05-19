package com.example.smartcity_1.utils

import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

val type = object : TypeToken<MutableList<Map<String, Any>>>() {}.type
val gson = GsonBuilder().registerTypeAdapter(type, GsonTypeAdapter()).create()

object Ok {
    val baseUrl = "http://121.5.160.121:8880/api"
    var token: String? = null
    val handler = Handler(Looper.getMainLooper())
    private val mClient = OkHttpClient.Builder()
        .addInterceptor {
            val newBuilder = it.request().newBuilder()
            token?.let { newBuilder.addHeader("Authorization", it) }
            return@addInterceptor it.proceed(newBuilder.build())
        }.build()

    fun get(path: String, block: (JSONObject) -> Unit) {
        val build = Request.Builder().url("$baseUrl$path").get().build()
        mClient.newCall(build).enqueue(MyCallback(block))
    }


    fun post(path: String, body: Any, block: (JSONObject) -> Unit) {
        val build = Request.Builder().url("$baseUrl$path")
            .post(gson.toJson(body).toRequestBody("application/json".toMediaType())).build()
        mClient.newCall(build).enqueue(MyCallback(block))
    }

    fun put(path: String, body: Any, block: (JSONObject) -> Unit) {
        val build = Request.Builder().url("$baseUrl$path")
            .put(gson.toJson(body).toRequestBody("application/json".toMediaType())).build()
        mClient.newCall(build).enqueue(MyCallback(block))
    }

    fun put(path: String, body: MultipartBody, block: (JSONObject) -> Unit) {
        val build = Request.Builder().url("$baseUrl$path").put(body).build()
        mClient.newCall(build).enqueue(MyCallback(block))
    }

    fun post(path: String, body: MultipartBody, block: (JSONObject) -> Unit) {
        val build = Request.Builder().url("$baseUrl$path").post(body).build()
        mClient.newCall(build).enqueue(MyCallback(block))
    }

    fun setImage(path: String, imageView: ImageView) {
        Glide.with(imageView).load("$baseUrl$path").into(imageView)
    }


    private class MyCallback(val block: (JSONObject) -> Unit) : Callback {
        override fun onFailure(call: Call, e: IOException) {
            handler.post { "网络链接异常".show() }
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val jsonObject = JSONObject(response.body?.string())
            handler.post {
                when (jsonObject.optInt("code")) {
                    200 -> block(jsonObject)
                    else -> jsonObject.optString("msg").show()
                }
            }
        }

    }
}