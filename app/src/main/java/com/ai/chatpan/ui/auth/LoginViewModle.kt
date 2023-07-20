package com.ai.chatpan.ui.auth

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ai.chatpan.base.App
import com.ai.chatpan.data.DataRepositoryV2
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.data.bean.AuthRequest
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.RequestBean
import com.ai.chatpan.data.bean.SendSMSCodeRequest
import com.blankj.utilcode.util.SPUtils
import com.btpj.lib_base.base.BaseViewModel
import com.btpj.lib_base.ext.handleRequest
import com.btpj.lib_base.ext.launch
import com.btpj.lib_base.ext.toJson
import com.btpj.lib_base.utils.LogUtil
import com.btpj.lib_base.utils.ToastUtil
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class LoginViewModle : BaseViewModel() {

    private val _user = MutableLiveData<AuthBean>()
    val user = _user

    private val _checkCode = MutableLiveData<Boolean>()
    val checkCode = _checkCode


    override fun start() {


    }

    fun getCode(context: Context, phoneNumber: String) {
        launch({

            var requestBean = SendSMSCodeRequest(phoneNumber, "", "", "", "")
            var json = Gson().toJson(requestBean)
            LogUtil.e("JSON :" + json)
            val mediaType: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody: RequestBody = RequestBody.create(mediaType, json)

            handleRequest(DataRepositoryV2.sendCmsCode(requestBody), {
                LogUtil.e("SMS发送成功 :" + json)
                ToastUtil.showShort(context, "验证码发送成功")
                _checkCode.value = true
            })
        })
    }

    fun auth(context: Context, userName: String, valismsCode: String) {

        launch({
            var requestBean = AuthRequest(userName, valismsCode, "")
            var json = Gson().toJson(requestBean)
            val mediaType: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody: RequestBody = RequestBody.create(mediaType, json)
            handleRequest(DataRepositoryV2.login(requestBody), {
                LogUtil.e("登录成功 :" + it.toJson())
                MMKV.defaultMMKV().encode("userInfo", it.toJson())
                _user.value = it.data!!
            })


            LogUtil.e("JSON :" + json)
        })

    }

}