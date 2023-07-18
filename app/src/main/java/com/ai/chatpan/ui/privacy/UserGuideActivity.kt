package com.ai.chatpan.ui.privacy

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ai.chatpan.R


class UserGuideActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        var tvPrivacy =findViewById<TextView>(R.id.tv_privacy)

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        val text = "<p>感谢您选择使用AI丰年应用程序（以下简称\"应用\"）。我们非常重视您的隐私和个人信息的保护，并致力于确保您在使用我们的应用时的安全和信任。本隐私政策旨在向您说明我们如何收集、使用、存储和保护您的个人信息。请在使用我们的应用之前仔细阅读本隐私政策。</p>" +
                "<p>信息收集和使用</font></p><p><b>1.1 用户提供的信息：当您注册账户、使用应用的特定功能时，我们可能会收集您提供的个人信息，例如您的姓名、电子邮件地址、手机号码等。这些信息将用于提供和改进我们的服务，并为您提供个性化的体验。</b></p>"+
         "<p>1.2 自动收集的信息：为了改善用户体验和优化应用功能，我们可能会自动收集某些信息，例如设备信息、操作记录、使用习惯等。这些信息将被用于统计分析和用户行为研究，以进一步提升应用的性能和服务质量。 </font></p><p><b>信息存储和保护</b></p>"+
         "<p>2.1 信息存储：我们将采取必要的安全措施，将您的个人信息存储在安全的服务器上，并采取合理的措施保护您的个人信息免受未经授权的访问、使用或泄露。</font></p>"+
         "<p>2.2 信息保留：我们将在符合法律法规要求的情况下，保留您的个人信息。一旦达到信息保留的目的，我们将采取措施安全地销毁或匿名化您的个人信息。</font></p>"+
         "<p>信息共享与披露 </font></p>"+
         "<p>我们将严格保护您的个人信息，除非获得您的明确授权或法律要求，否则不会与任何第三方共享或披露您的个人信息。 ！</font></p>"+
         "<p>广告与分析服务  ！</font></p>"+
         "<p>我们可能会使用第三方分析服务和广告服务提供商来分析应用的使用情况和提供个性化广告。这些服务提供商可能会收集和使用某些信息，如设备标识、IP地址等，用于提供相关服务。但这些信息不会用于直接识别您的个人身份。 </font></p>"+
         "<p>隐私权保护措施 我们将采取合理的技术和组织措施，保护您的个人信息免受未经授权的访问、使用或泄露。我们会定期审查和改进我们的安全措施，以确保您的个人信息得到充分的保护。</font></p>"+
         "<p>未成年人隐私保护</font></p>"+
         "<p>我们鼓励父母或监护人指导未满18岁的未成年人使用我们的应用。我们不会故意收集未成年人的个人信息，如果您是父母或监护人，并认为您的孩子向我们提供了他们的个人信息，请联系我们，我们将采取适当的措施删除相关信息。</font></p>"+
         "<p>隐私政策的更新</font></p>"+
         "<p>我们可能会不时更新本隐私政策，以反映我们的实践和法律要求。在更新隐私政策时，我们将通过适当的方式通知您。请定期查阅本隐私政策，以了解我们如何保护和处理您的个人信息。 如果您对我们的隐私政策有任何疑问或意见，请联系我们。感谢您信任并使用AI丰年应用！</font></p>"
        tvPrivacy.setText(Html.fromHtml(text))

    }
}