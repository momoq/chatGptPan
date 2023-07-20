package com.ai.chatpan.ui.privacy

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ai.chatpan.R


class SettingActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeting_layout)


        findViewById<ConstraintLayout>(R.id.cons_1).setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {

           finish()
        }

        findViewById<ConstraintLayout>(R.id.cons_2).setOnClickListener {

            val intent = Intent(this, UserGuideActivity::class.java)
            startActivity(intent)
        }

        var tvVersion =findViewById<TextView>(R.id.tv_version)

        try {
            val manager = packageManager
            val info = manager.getPackageInfo(packageName, 0)
            val versionName = info.versionName
            val versionCode = info.versionCode

            // 这里的versionName是应用的版本名称，versionCode是应用的版本号
            // 可以根据需要使用其中之一或两者都使用
            tvVersion.text ="V"+versionName
            // 示例输出+
            Log.d("AppVersion", "Version Name: $versionName")
            Log.d("AppVersion", "Version Code: $versionCode")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }
}