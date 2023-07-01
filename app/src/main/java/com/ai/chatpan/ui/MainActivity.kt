package com.ai.chatpan.ui

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.databinding.ActivityMainBinding
import com.ai.chatpan.ui.main.ChatAdapter
import com.airbnb.lottie.LottieAnimationView
import com.blankj.utilcode.util.DeviceUtils
import com.btpj.lib_base.utils.LogUtil
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV
import java.util.regex.Pattern


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    var subjectId: String = "111"
    var roomUUID: String = "64f8791f-c1de-427d-990b-9cc7eb8ff472"
    var assistantUUID: String = "18914f7a-9a0c-4314-9f3b-365481809b97"
    var chatList = ArrayList<BaseChatBean>()
    private val mAdapter: ChatAdapter = ChatAdapter(chatList)
    var TAG = "MainActivity"
    var deviceId: String = DeviceUtils.getUniqueDeviceId()
    var isAsked = MMKV.defaultMMKV().decodeBool("ChatPanInstall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CrashReport.initCrashReport(applicationContext, "83868d6604", false)

    }

    override fun onPause() {
        super.onPause()
    }

    val pattern = Pattern.compile("[\\u4E00-\\u9FA5A-Za-z`~!@#$%^&*()\\-_+=<>?/.,;:\"'|]")
    val filter = InputFilter { source, _, _, _, _, _ ->
        if (pattern.matcher(source).find()) source else ""
    }

    override fun onResume() {
        super.onResume()
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
                    mBinding.etInput!!.isEnabled =true
                    if (!isAsked) {
                        mBinding!!.consFirst!!.visibility = View.VISIBLE
                    } else {
                        mBinding.consFirst.visibility = View.GONE
                        // 动画播放完成时的回调
                        mViewModel.
                        getOuterDialogHistory("64f8791f-c1de-427d-990b-9cc7eb8ff472",
                            deviceId)
                    }
                }, 1000)


            }

            override fun onAnimationCancel(animation: Animator) {
                // 动画被取消时的回调
                mBinding.etInput.isEnabled =true
            }

            override fun onAnimationRepeat(animation: Animator) {
                // 动画重复播放时的回调
            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        var context = this
        LogUtil.d(TAG, "devceid : $deviceId")

        findViewById<RecyclerView>(R.id.rl_my_answer).layoutManager = LinearLayoutManager(context)
        mBinding.apply {
            etInput.filters = arrayOf(filter)
            etInput.isEnabled =false
//            rlMyAnswer.layoutManager = LinearLayoutManager(context)
            rlMyAnswer.adapter = mAdapter
            mAdapter.submitList(chatList)

            ivAsk.setOnClickListener {
                mViewModel.apply {
                    if ( etInput.text.toString().isEmpty()){
                        return@apply
                    }
                    askQuestion( subjectId,
                        deviceId,
                        roomUUID,
                        assistantUUID,
                        etInput.text.toString().trim())
                }
                if (!isAsked){
                    MMKV.defaultMMKV().encode("ChatPanInstall",true)
                }


                if ( !etInput.text.toString().isEmpty()){
                    var chatBean = ChatPanBean()
                    chatBean.type = 0
                    chatBean.question = etInput.text.toString().trim()
                    chatList.add(chatBean)

                    mAdapter.notifyDataSetChanged()
                    val lastItemPosition: Int = mAdapter.getItemCount() - 1
                    mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
                    etInput.text.clear()
                    val mInputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                     mInputMethodManager.hideSoftInputFromWindow(context.currentFocus!!.windowToken, 0)
                }

            }

            etInput.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    mBinding.consFirst.visibility = View.GONE
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })


        }



    }


    /**     * 点击空白位置 隐藏软键盘      */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (null != this.currentFocus) {
            val mInputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            return mInputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        return super.onTouchEvent(event)
    }

    override fun createObserve() {
        super.createObserve()

        mViewModel.askAnswer.observeForever {
            mBinding!!.consFirst!!.visibility = View.GONE
            LogUtil.e(TAG, "askAnswer : " + it.toString())
            it!!.type = 1
            chatList.add(it)
            mAdapter.notifyDataSetChanged()

            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding!!.rlMyAnswer!!.scrollBy(0,500)

        }

        mViewModel.askHistory.observeForever {
            mBinding!!.consFirst!!.visibility = View.GONE
            mBinding!!.rlMyAnswer!!.visibility = View.VISIBLE
            for (chatBean: BaseChatBean in it) {
                chatBean.type = 2
                chatList.add(chatBean)
            }
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding!!.rlMyAnswer.scrollToPosition(lastItemPosition)
            LogUtil.e(TAG, "askHistory : " + it.toString())
        }
    }

    override fun requestError(msg: String?) {
        super.requestError(msg)
        LogUtil.e(TAG, "requestError : " + msg)

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding!!.lottieAnimationView.cancelAnimation()
        mBinding!!.lottieAnimationView.clearAnimation()
    }
}