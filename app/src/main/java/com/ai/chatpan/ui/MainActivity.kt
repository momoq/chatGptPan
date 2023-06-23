package com.ai.chatpan.ui

import android.os.Bundle
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.databinding.ActivityMainBinding
import com.btpj.lib_base.utils.LogUtil

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    var subjectId: String = "111"
    var outerId: String = "12345"
    var roomUUID: String = "64f8791f-c1de-427d-990b-9cc7eb8ff472"
    var assistantUUID: String = "18914f7a-9a0c-4314-9f3b-365481809b97"

    override fun initView(savedInstanceState: Bundle?) {

        mBinding.apply {
            ivAsk.setOnClickListener {
                mViewModel.askQuestion(
                    subjectId,
                    outerId,
                    roomUUID,
                    assistantUUID,
                    etInput.text.toString()
                ).apply {

                }

            }
        }
    }

    override fun createObserve() {
        super.createObserve()

        mViewModel.askAnswer.observeForever {

            LogUtil.e("askAnswer : "+ it.toString())
//            mBinding.tvAns.text = it.answer
        }
    }

}