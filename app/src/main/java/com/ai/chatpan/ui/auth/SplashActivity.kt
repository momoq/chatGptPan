package com.ai.chatpan.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ai.chatpan.R
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.ui.MainActivity
import com.blankj.utilcode.util.GsonUtils
import com.tencent.mmkv.MMKV

class SplashActivity : AppCompatActivity() {

    var userInfoJson = MMKV.defaultMMKV().decodeString("userInfo")
    var authBean = GsonUtils.fromJson(userInfoJson, AuthBean::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userID = intent.getStringExtra("userID")

        setContentView(R.layout.activity_splash)
        findViewById<Button>(R.id.bt_login).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userID", userID)
            startActivity(intent)

        }
    }
}