package com.ai.chatpan.ui.auth

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.ai.chatpan.R
import com.ai.chatpan.base.MyApp
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.data.bean.WXEventBean
import com.ai.chatpan.databinding.ActivityLoginBinding
import com.ai.chatpan.ui.MainActivity
import com.ai.chatpan.ui.privacy.AboutUsActivity
import com.ai.chatpan.ui.privacy.UserGuideActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import com.btpj.lib_base.utils.LogUtil
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mmkv.MMKV
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity : BaseActivity<LoginViewModle, ActivityLoginBinding>(R.layout.activity_login) {

    private var countdownTime = 60
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private var TAG = "LoginActivity ： "

    private var isSendSMS: Boolean = false

    var phoneNumber: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        regToWx()

        var decodeString = MMKV.defaultMMKV().decodeString("userID")
        if (!TextUtils.isEmpty(decodeString)) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userID", decodeString)
            startActivity(intent)
            finish()

        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun regToWx() {

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // 将该app注册到微信
//                MyApp.getmWxApi().registerApp()
            }
        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))


    }


    private fun wxLogin() {
        if (!MyApp.getmWxApi()!!.isWXAppInstalled()) {
            ToastUtils.showShort("您的设备未安装微信客户端")
        } else {
            var req = SendAuth.Req();
            req.scope = "snsapi_userinfo"; // 只能填 snsapi_userinfo
            req.state = "wechat_sdk_demo_test";
            MyApp.getmWxApi()!!.sendReq(req);
        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(wxEvent: WXEventBean) {
        var code = wxEvent.wxCode
        mViewModel.wxAuth(code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
            // 处理微信登录授权回调结果
            if (resultCode == ConstantsAPI.COMMAND_SENDAUTH) {
                // 授权成功，获取授权码
                LogUtils.e("微信授权成功：" + data.toString())
                // 将授权码传递给服务端，换取access_token和用户信息
            } else if (resultCode == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
                // 授权失败或取消
                LogUtils.d("授权失败：" + resultCode)
            }
        }

    }

    override fun initView(savedInstanceState: Bundle?) {

        initObserver()


        var checkBox = findViewById<CheckBox>(R.id.checkbox_agree)
        findViewById<TextView>(R.id.tv_agree2).setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.tv_agree4).setOnClickListener {

            val intent = Intent(this, UserGuideActivity::class.java)
            startActivity(intent)
        }

        var etPhone = findViewById<EditText>(R.id.et_phone)
        var etCode = findViewById<EditText>(R.id.et_code)
        var btLogin = findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener {
            if (checkBox.isChecked) {
                mViewModel.auth(this, phoneNumber, etCode.text.toString())
            } else {
                ToastUtils.make()
                    .setBgColor(getColor(R.color.toast_bg_color))
                    .setTextColor(getColor(R.color.toast_color))
                    .setGravity(Gravity.CENTER, 0, 0)
                    .show("请先勾选同意用户和隐私协议")
            }

        }

        findViewById<ImageView>(R.id.iv_wxchat).setOnClickListener {

            if (checkBox.isChecked) {
                wxLogin()
            } else {
                ToastUtils.make()
                    .setBgColor(getColor(R.color.toast_bg_color))
                    .setTextColor(getColor(R.color.toast_color))
                    .setGravity(Gravity.CENTER, 0, 0)
                    .show("请先勾选同意用户和隐私协议")
            }

        }

        var countdownTextView = findViewById<TextView>(R.id.tv_code)
        etPhone.addTextChangedListener {
            countdownTextView.isEnabled = true
            countdownTextView.isSelected = true
            phoneNumber = etPhone.text.toString()
        }

        etCode.addTextChangedListener {
//            if (isSendSMS) {
            btLogin.isEnabled = true
            btLogin.isSelected = true
//            }
        }

        countdownTextView!!.setOnClickListener {

            phoneNumber = etPhone.text.toString()
            if (RegexUtils.isMobileExact(phoneNumber)) {
                mViewModel.getCode(this, phoneNumber)
                startCountdown(countdownTextView)

            } else {
                LogUtil.e(TAG + "请输入正确的手机号码")
                ToastUtils.make().setBgColor(getColor(R.color.toast_bg_color))
                    .setTextColor(getColor(R.color.toast_color)).show("请输入正确的手机号码")
            }


        }

        handler = Handler()

        runnable = object : Runnable {
            override fun run() {
                countdownTime--
                countdownTextView!!.text = countdownTime.toString()
                if (countdownTime > 0) {
                    handler.postDelayed(this, 1000) // 每隔一秒更新一次
                } else {
                    countdownTextView!!.text = "重新获取验证码"
                    countdownTextView.isEnabled = true
                    countdownTextView.isSelected = true
                }
            }
        }


    }


    private fun initObserver() {

        mViewModel.checkCode.observeForever {
            isSendSMS = true
        }
        mViewModel.user.observeForever {
            LogUtils.d("登录成功-------跳转到欢迎页。")
            MMKV.defaultMMKV().encode("userID", it.userInfo.userId)
            val intent = Intent(this, SplashActivity::class.java)
            intent.putExtra("userID", it.userInfo.userId)
            startActivity(intent)
            finish()
        }

    }

    private fun startCountdown(countdownTextView: TextView) {
        countdownTime = 60
        countdownTextView.text = countdownTime.toString() + "  S"
        countdownTextView.isEnabled = false // 防止重复点击
        handler.postDelayed(runnable, 1000)
    }
}