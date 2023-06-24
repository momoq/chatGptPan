package com.ai.chatpan.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.databinding.ActivityMainBinding
import com.ai.chatpan.ui.main.ChatAdapter
import com.btpj.lib_base.utils.LogUtil

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    var subjectId: String = "111"
    var outerId: String = "12345"
    var roomUUID: String = "64f8791f-c1de-427d-990b-9cc7eb8ff472"
    var assistantUUID: String = "18914f7a-9a0c-4314-9f3b-365481809b97"

    var chatList = ArrayList<BaseChatBean>()
    private val mAdapter: ChatAdapter = ChatAdapter(chatList)

    override fun initView(savedInstanceState: Bundle?) {
        var context = this
        mBinding.apply {

            rlMyAnswer.layoutManager = LinearLayoutManager(context)
            rlMyAnswer.adapter = mAdapter
            mAdapter.submitList(chatList)

            ivAsk.setOnClickListener {
                mViewModel.askQuestion(
                    subjectId,
                    outerId,
                    roomUUID,
                    assistantUUID,
                    etInput.text.toString()
                )

                var chatBean = ChatPanBean()
                chatBean.type = 0
                chatBean.question = etInput.text.toString()
                chatList.add(chatBean)

                mAdapter.notifyDataSetChanged()
                val lastItemPosition: Int = mAdapter.getItemCount() - 1
                mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
                etInput.text.clear()
            }

        }


        mViewModel.getOuterDialogHistory("64f8791f-c1de-427d-990b-9cc7eb8ff472", "12345")
    }

    override fun onResume() {
        super.onResume()

    }

    override fun createObserve() {
        super.createObserve()

        mViewModel.askAnswer.observeForever {

            LogUtil.e("askAnswer : " + it.toString())
            it.type = 1
            chatList.add(it)
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)

        }

        mViewModel.askHistory.observeForever {
            for (chatBean: BaseChatBean in it) {
                chatBean.type = 2
                chatList.add(chatBean)
            }
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
            LogUtil.e("askHistory : " + it.toString())
        }
    }

}