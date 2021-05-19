package com.example.smartcity_1.ui.personal

import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.passwordfragment.*

class PasswordFragment : BaseFragment(R.layout.passwordfragment) {
    override fun initViews() {
        button.setOnClickListener {
            val texts = getTexts(et1, et2)
            if (texts.size == 2) {
                Ok.put("/system/user/resetPwd", mapOf("userId" to AppClient.userInfo?.userId,"oldPwd" to texts[0],"password" to texts[1])){
                    it.optString("msg").show()
                    back()
                }
            }
        }
    }
}