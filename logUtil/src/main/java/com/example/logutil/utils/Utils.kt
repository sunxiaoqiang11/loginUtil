package com.example.smartcity_1.utils

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.duche.DuCheActivity
import com.example.smartcity_1.ui.illegal.IllegalActivity
import com.example.smartcity_1.ui.job.SearchJobActivity
import com.example.smartcity_1.ui.metro.MetroActivity

private var toast = Toast.makeText(AppClient.mContext,"",Toast.LENGTH_SHORT)


fun String.show(){
    toast.cancel()
    toast = Toast.makeText(AppClient.mContext,this,Toast.LENGTH_SHORT)
    toast.show()
}

fun Fragment.from(name: String) {
    when (name) {
        "更多服务" -> findNavController().navigate(R.id.navigation_dashboard)
        "城市地铁" -> startActivity(Intent(requireContext(),MetroActivity::class.java))
        "违章查询" -> startActivity(Intent(requireContext(),IllegalActivity::class.java))
        "堵车移车" -> startActivity(Intent(requireContext(),DuCheActivity::class.java))
        "找工作" -> startActivity(Intent(requireContext(),SearchJobActivity::class.java))
    }
}