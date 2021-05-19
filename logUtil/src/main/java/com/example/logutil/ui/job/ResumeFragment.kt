package com.example.smartcity_1.ui.job

import android.content.Intent
import android.widget.ArrayAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Resume
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.ui.personal.PersonalInfoActivity
import com.example.smartcity_1.ui.personal.getTexts
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.gson
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.feedbackfragment.*
import kotlinx.android.synthetic.main.resumefragment.*
import okhttp3.MultipartBody

class ResumeFragment : BaseFragment(R.layout.resumefragment) {
    override fun initViews() {
        button3.setOnClickListener {
            startActivity(Intent(mContext, PersonalInfoActivity::class.java))
        }
        val positions = arrayOf("java 开发工程师", "设计", "外教", "前端工程师", "牙医", "全栈开发工程师")
        s4.adapter =
            ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item, positions)
        Ok.get("/userinfo/resume/${AppClient.userInfo?.userId}") {
            if (it.has("data")) {
                val resume = gson.fromJson(it.optString("data"), Resume::class.java)
                a1.setText(resume.experience)
                a2.setText(resume.mostEducation)
                a3.setText(resume.address)
                s4.setSelection(resume.positionId.toInt()-1)
                a5.setText(resume.money)
                a6.setText(resume.individualResume)
                a7.setText(resume.education)
                a8.setText(resume.experience)
                /**
                 * Id=1
                mostEducation=博士
                education=本科
                address=北京
                experience=1 年
                individualResume=性格有点内向、乐观上进、有爱心并善于施教…
                money=5000
                positionId=2

                 */
                button4.setOnClickListener {
                    val texts = getTexts(a1, a2, a3, a5, a6, a7, a8)
                    if (texts.size == 7) {
                        val multipartBody = MultipartBody.Builder()
                            .addFormDataPart("experience", texts[0])
                            .addFormDataPart("mostEducation", texts[1])
                            .addFormDataPart("address", texts[2])
                            .addFormDataPart("positionId", (s4.selectedItemPosition + 1).toString())
                            .addFormDataPart("money", texts[3])
                            .addFormDataPart("individualResume", texts[4])
                            .addFormDataPart("education", texts[5])
                            .addFormDataPart("Id", resume.id.toString())
                        Ok.put("/userinfo/resume",multipartBody.build()){
                            "修改成功".show()
                        }
                    }
                }
            } else {
                button4.setOnClickListener {
                    val texts = getTexts(a1, a2, a3, a5, a6, a7, a8)
                    if (texts.size == 7) {
                        val multipartBody = MultipartBody.Builder()
                            .addFormDataPart("experience", texts[0])
                            .addFormDataPart("mostEducation", texts[1])
                            .addFormDataPart("address", texts[2])
                            .addFormDataPart("positionId", (s4.selectedItemPosition + 1).toString())
                            .addFormDataPart("money", texts[3])
                            .addFormDataPart("individualResume", texts[4])
                            .addFormDataPart("education", texts[5])
                            .addFormDataPart("userId",AppClient.userInfo?.userId.toString())
                        Ok.post("/userinfo/resume",multipartBody.build()){
                            "添加成功".show()
                        }
                    }
                }
            }

        }
    }
}