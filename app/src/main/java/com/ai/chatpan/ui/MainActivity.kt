package com.ai.chatpan.ui

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
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.btpj.lib_base.utils.LogUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.tencent.bugly.crashreport.CrashReport
import pl.droidsonroids.gif.GifDrawable

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {
    var subjectId: String = "111"
    var roomUUID: String = "64f8791f-c1de-427d-990b-9cc7eb8ff472"
    var assistantUUID: String = "18914f7a-9a0c-4314-9f3b-365481809b97"
    var chatList = ArrayList<BaseChatBean>()
    private val mAdapter: ChatAdapter = ChatAdapter(chatList)
    var TAG ="MainActivity"
    var deviceId :String = DeviceUtils.getUniqueDeviceId()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        Bugly.init(applicationContext, "83868d6604", false);
        CrashReport.initCrashReport(applicationContext, "83868d6604", false)
    }
    override fun initView(savedInstanceState: Bundle?) {
        var context = this
        LogUtil.d(TAG,"devceid : $deviceId")


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

            if (AppUtils.isFirstTimeInstall()){
                mBinding.consFirst.visibility = View.VISIBLE

            }else{
                mBinding.consFirst.visibility = View.GONE
//                mBinding.rlMyAnswer.visibility =View.VISIBLE
                mViewModel.getOuterDialogHistory("64f8791f-c1de-427d-990b-9cc7eb8ff472", deviceId)
            }
/*
            Glide.with(context)
                .asGif()
                .load(R.drawable.chatpan_fn)
                .addListener(object : RequestListener<GifDrawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (target is ImageViewTarget<*>) {
                            val imageView = target.view as ImageView
                            val animationDrawable = imageView.drawable as? AnimationDrawable

                            animationDrawable?.apply {
                                isOneShot = true // 设置仅播放一次
                                // 添加动画播放结束的监听器
                                callback = object : GifDrawable.Callback {
                                    override fun invalidateDrawable(drawable: GifDrawable) {
                                        // 动画播放结束时的操作
                                    }

                                    override fun scheduleDrawable(drawable: GifDrawable, runnable: Runnable, l: Long) {

                                    }

                                    override fun unscheduleDrawable(drawable: GifDrawable, runnable: Runnable) {

                                    }
                                }
                            }
                        }
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(mBinding.ivLoading);*/

        }


        if(mViewModel.invitationCode.value == true){
            mViewModel.getOuterDialogHistory("64f8791f-c1de-427d-990b-9cc7eb8ff472", deviceId)
        }


    }


    override fun createObserve() {
        super.createObserve()

        mViewModel.askAnswer.observeForever {

            LogUtil.e(TAG,"askAnswer : " + it.toString())
            it.type = 1
            chatList.add(it)
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
//            mBinding.consFirst.visibility = View.GONE
        }

        mViewModel.askHistory.observeForever {
//            mBinding.consFirst.visibility = View.GONE
            for (chatBean: BaseChatBean in it) {
                chatBean.type = 2
                chatList.add(chatBean)
            }
            mAdapter.notifyDataSetChanged()
            val lastItemPosition: Int = mAdapter.getItemCount() - 1
            mBinding.rlMyAnswer.scrollToPosition(lastItemPosition)
            LogUtil.e(TAG,"askHistory : " + it.toString())
        }
    }

}