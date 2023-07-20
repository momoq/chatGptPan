package com.ai.chatpan.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.databinding.ActivityLoginBinding
import com.ai.chatpan.ui.MainActivity
import com.ai.chatpan.ui.privacy.AboutUsActivity
import com.ai.chatpan.ui.privacy.PrivacyActivity
import com.ai.chatpan.ui.privacy.UserGuideActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.RegexUtils
import com.btpj.lib_base.utils.LogUtil
import com.btpj.lib_base.utils.ToastUtil
import com.tencent.mmkv.MMKV

class LoginActivity : BaseActivity<LoginViewModle, ActivityLoginBinding>(R.layout.activity_login) {

    private var countdownTime = 60
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private var TAG = "LoginActivity ： "

    private var isSendSMS: Boolean = false

    var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var decodeString = MMKV.defaultMMKV().decodeString("userID")
        if (!TextUtils.isEmpty(decodeString)){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userID",decodeString)
            startActivity(intent)
            finish()
        }
    }
    override fun initView(savedInstanceState: Bundle?) {

        initObserver()
        var checkBox =findViewById<CheckBox>(R.id.checkbox_agree)
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
            if (checkBox.isChecked){
                mViewModel.auth(this, phoneNumber, etCode.text.toString())
            }else{
                ToastUtil.showShort(this, "请先勾选同意用户和隐私协议")
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
                ToastUtil.showShort(this, "请输入正确的手机号码")
            }


        }

        handler = Handler()

        runnable = object : Runnable {
            override fun run() {
                countdownTime--
                countdownTextView!!.text = countdownTime.toString()
                if (countdownTime > 0) {
                    handler.postDelayed(this, 1000) // 每隔一秒更新一次
                }else{
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
            MMKV.defaultMMKV().encode("userID",it.userInfo.userId)
            val intent = Intent(this, SplashActivity::class.java)
            intent.putExtra("userID",it.userInfo.userId)
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