package com.example.smartcity_1.ui.personal

import android.text.Editable
import android.text.TextWatcher
import com.example.smartcity_1.R
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.feedbackfragment.*
import kotlinx.android.synthetic.main.passwordfragment.button

class FeedbackFragment : BaseFragment(R.layout.feedbackfragment) {
    override fun initViews() {
        et.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text.text = "${s?.length}/150"
            }

        })
        button.setOnClickListener {
            val texts = getTexts(et)
            if (texts.isNotEmpty()) {
                Ok.post("/userinfo/feedback", mapOf("content" to texts.first(),"userId" to AppClient.userInfo?.userId)){
                    it.optString("msg").show()
                    back()
                }
            }
        }
    }
}