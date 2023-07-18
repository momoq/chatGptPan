package com.ai.chatpan.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ai.chatpan.R
import com.ai.chatpan.base.BaseActivity
import com.ai.chatpan.databinding.ActivityLoginBinding
import com.ai.chatpan.ui.MainActivity
import com.ai.chatpan.ui.privacy.PrivacyActivity
import com.ai.chatpan.ui.privacy.UserGuideActivity

class LoginActivity : BaseActivity<LoginViewModle, ActivityLoginBinding>(R.layout.activity_login) {
    
    override fun initView(savedInstanceState: Bundle?) {

        findViewById<Button>(R.id.bt_login).setOnClickListener {

        }

        findViewById<TextView>(R.id.tv_agree2).setOnClickListener{
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)

        }

        findViewById<TextView>(R.id.tv_agree4).setOnClickListener{
            val intent = Intent(this, UserGuideActivity::class.java)
            startActivity(intent)

        }

    }
}