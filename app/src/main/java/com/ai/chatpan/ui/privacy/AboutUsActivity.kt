package com.ai.chatpan.ui.privacy

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ai.chatpan.R


class AboutUsActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        findViewById<TextView>(R.id.tv_title).setText(getString(R.string.about_us))


        var tvPrivacy =findViewById<TextView>(R.id.tv_privacy)

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        val text = "<p>欢迎来到AI丰年！我们是一家致力于为用户提供智能化助手的公司。AI丰年是我们的旗舰产品，它是一款基于人工智能的应用程序，旨在提供智能对话、任务管理、日程管理和搜索等功能，以帮助用户更高效地管理日常生活和工作。</p>" +
                "<p>我们的使命是通过结合自然语言处理和机器学习技术，为用户提供个性化、智能化的服务。无论是回答常见问题、提供实时信息，还是帮助用户组织和管理任务、日程安排，AI丰年都可以成为您的得力助手。</b></p>"+
         "<p>在AI丰年中，我们注重用户体验和简便性。您可以与应用进行自然对话，就像与一个朋友交流一样。无论是在学习中遇到困惑、需要管理工作任务，还是想要组织自己的日程安排，AI丰年都将提供精准的回答和实用的功能。 </font></p>"+
         "<p>我们深知用户的隐私和数据安全的重要性。因此，我们采取了严格的安全措施来保护您的个人信息和数据。您可以放心地使用AI丰年，享受智能化助手带来的便利和效率，同时保持数据的安全和隐私。 </font></p>"+
         "<p>无论您是学生、专业人士、创业家还是创意工作者，AI丰年都将成为您的得力助手。我们不断迭代和改进产品，以满足用户不断变化的需求。我们相信，AI丰年将为您带来更高效、更智能的生活体验。 </font></p>"+
         "<p>感谢您选择AI丰年，我们期待能够与您一同探索智能化的未来！</font></p>"
        tvPrivacy.setText(Html.fromHtml(text))

    }
}