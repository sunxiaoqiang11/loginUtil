package com.example.smartcity_1.ui.notifications

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import kotlinx.android.synthetic.main.pintaijiancefragment.*
import kotlin.random.Random

class PinTaiJianCeFragment : BaseFragment(R.layout.pintaijiancefragment) {
    var handler:Handler?=null
    override fun initViews() {
        handler = object :Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                t1.text = Random.nextInt(100).toString()
                t2.text = Random.nextInt(100).toString()
                t3.text = Random.nextInt(100).toString()
                t4.text = Random.nextInt(100).toString()
                t5.text = Random.nextInt(100).toString()
                t6.text = Random.nextInt(100).toString()
                t7.text = Random.nextInt(100).toString()
                t8.text = Random.nextInt(100).toString()
                t9.text = Random.nextInt(100).toString()
                t10.text = Random.nextInt(100).toString()
                sendEmptyMessageDelayed(0,3000)
            }
        }
        handler?.sendEmptyMessage(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeMessages(0)
    }
}