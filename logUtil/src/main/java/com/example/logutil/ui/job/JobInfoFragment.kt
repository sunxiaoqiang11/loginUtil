package com.example.smartcity_1.ui.job

import android.widget.SimpleAdapter
import com.example.smartcity_1.R
import com.example.smartcity_1.bean.Post
import com.example.smartcity_1.ui.BaseFragment
import com.example.smartcity_1.utils.AppClient
import com.example.smartcity_1.utils.Ok
import com.example.smartcity_1.utils.show
import kotlinx.android.synthetic.main.activity_search_job.*
import kotlinx.android.synthetic.main.jobinfofragment.*

class JobInfoFragment : BaseFragment(R.layout.jobinfofragment) {
    override fun initViews() {
        getA<Post>().let {
            val arr = mutableListOf(
                mapOf("a" to "职位名称", "b" to it.name),
                mapOf("a" to "公司地点", "b" to it.address),
                mapOf("a" to "岗位职责", "b" to "爱岗敬业，不迟到，不早退"),
                mapOf("a" to "薪资待遇", "b" to it.salary),
                mapOf("a" to "联系人", "b" to it.contacts),
                mapOf("a" to "职位描述", "b" to it.obligation),
                mapOf("a" to "工作年限", "b" to it.need)
            )
            Ok.get("/userinfo/company/${it.companyId}") { j ->
                val companyName = j.optJSONObject("data").optString("companyName")
                val introductory = j.optJSONObject("data").optString("introductory")
                arr.add(mapOf("a" to "公司简介", "b" to introductory))
                listView.adapter = SimpleAdapter(
                    mContext, arr, R.layout.two_textview_item,
                    arrayOf("a", "b"), intArrayOf(R.id.text1, R.id.text2)
                )
                button.setOnClickListener { _ ->
                    Ok.get("/userinfo/resume/${AppClient.userInfo?.userId}") {q->
                        if (q.has("data")) {
                            Ok.post(
                                "/userinfo/deliver", mapOf(
                                    "userId" to AppClient.userInfo?.userId,
                                    "postName" to it.name,
                                    "companyName" to companyName,
                                    "money" to it.salary
                                )
                            ) {
                                it.optString("msg").show()
                                parentFragmentManager.popBackStack()
                            }

                        }else {
                            "请完善信息后进行投简历".show()
                            requireActivity().tabLayout.getTabAt(2)?.select()
                        }

                    }

                }

            }
            /**
             * {
            "userId":"3",
            "postName":"医生助理五险一金双休",
            "companyName":"牙大夫",
            "money":"5000"
            }
             */
        }
    }
}