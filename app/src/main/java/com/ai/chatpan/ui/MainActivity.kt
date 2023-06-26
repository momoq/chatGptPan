package com.ai.chatpan.ui

import android.animation.Animator
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.databinding.ActivityMainBinding
import com.ai.chatpan.ui.main.ChatAdapter
import com.airbnb.lottie.LottieAnimationView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.btpj.lib_base.utils.LogUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.delay
import pl.droidsonroids.gif.GifDrawable

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    var subjectId: String = "111"
    var roomUUID: String = "64f8791f-c1de-427d-990b-9cc7eb8ff472"
    var assistantUUID: String = "18914f7a-9a0c-4314-9f3b-365481809b97"
    var chatList = ArrayList<BaseChatBean>()
    private val mAdapter: ChatAdapter = ChatAdapter(chatList)
    var TAG = "MainActivity"
    var deviceId: String = DeviceUtils.getUniqueDeviceId()

    var chatInstall:Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        Bugly.init(applicationContext, "83868d6604", false);
//        CrashReport.initCrashReport(applicationContext, "83868d6604", false)
        chatInstall = MMKV.defaultMMKV().decodeBool("chatPanInstall")
        if (!chatInstall) {
            chatInstall=true
            LogUtil.d(TAG, "chatInstall : $chatInstall")
            MMKV.defaultMMKV().encode("chatPanInstall", true)
        }

    }

    override fun initView(savedInstanceState: Bundle?) {
        var context = this
        LogUtil.d(TAG, "devceid : $deviceId")


        mBinding.apply {


            rlMyAnswer.layoutManager = LinearLayoutManager(context)
            rlMyAnswer.adapter = mAdapter
            mAdapter.submitList(chatList)

            ivAsk.setOnClickListener {

                mViewModel.askQuestion(
                    subjectId,
                    deviceId,
                    roomUUID,
                    assistantUUID,
                    etInput.text.toString().trim()
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


            val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottie_animation_view)
            lottieAnimationView.setAnimation(R.raw.chan_pan)
            lottieAnimationView.playAnimation()

            lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    // 动画开始播放时的回调
                }

                override fun onAnimationEnd(animation: Animator) {

                    lottieAnimationView.postDelayed(Runnable {
                        lottieAnimationView.visibility = View.GONE

                        if (!chatInstall) {
                            LogUtil.d(TAG, "chatInstall 2: $chatInstall")
                            mBinding.consFirst.visibility = View.VISIBLE
                        } else {
                            LogUtil.d(TAG, "chatInstall 3: $chatInstall")
                            mBinding.consFirst.visibility = View.GONE
                            // 动画播放完成时的回调
                            mViewModel.getOuterDialogHistory(
                                "64f8791f-c1de-427d-990b-9cc7eb8ff472",
                                deviceId
                            )
                        }
                    }, 1000)


                }

                override fun onAnimationCancel(animation: Animator) {
                    // 动画被取消时的回调
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // 动画重复播放时的回调
                }
            })
        }


        if (mViewModel.invitationCode.value == true) {
            mViewModel.getOuterDialogHistory("64f8791f-c1de-427d-990b-9cc7eb8ff472", deviceId)
        }


    }


    override fun createObserve() {
        super.createObserve()

        mViewModel.askAnswer.observeForever {
            mBinding.consFirst.visibility = View.GONE
            LogUtil.e(TAG, "askAnswer : " + it.toString())
            it.type = 1
            chatList.add(it)
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
        }

        mViewModel.askHistory.observeForever {
            mBinding.consFirst.visibility = View.GONE
            mBinding.rlMyAnswer.visibility = View.VISIBLE
            for (chatBean: BaseChatBean in it) {
                chatBean.type = 2
                chatList.add(chatBean)
            }
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
            LogUtil.e(TAG, "askHistory : " + it.toString())
        }
    }

    override fun requestError(msg: String?) {
        super.requestError(msg)
        LogUtil.e(TAG, "requestError : " + msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.lottieAnimationView.cancelAnimation()
        mBinding.lottieAnimationView.clearAnimation()
    }
}