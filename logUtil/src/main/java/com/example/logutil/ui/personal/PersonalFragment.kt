package com.example.smartcity_1.ui.personal

import android.content.Intent
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import kotlinx.android.synthetic.main.personalfragment.*

class PersonalFragment : BaseFragment(R.layout.personalfragment) {
    override fun initViews() {
        AppClient.userInfo?.let {
            Ok.setImage(it.avatar,ivTouX)
            tvUserName.text = it.userName
        }
        q1.setOnClickListener { startActivity(Intent(mContext, PersonalInfoActivity::class.java)) }
        q2.setOnClickListener { startActivity(Intent(mContext, OrderActivity::class.java)) }
        button.setOnClickListener { startActivityForResult(Intent(mContext, LoginActivity::class.java), 999) }
        q3.setOnClickListener { goTo(R.id.passwordFragment) }
        q4.setOnClickListener { goTo(R.id.feedbackFragment) }
    }

    override fun onResume() {
        super.onResume()
        if (AppClient.userInfo == null) {
            startActivityForResult(Intent(mContext, LoginActivity::class.java), 999)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        initViews()
    }

}