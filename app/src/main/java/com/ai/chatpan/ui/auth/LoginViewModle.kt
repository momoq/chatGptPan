package com.ai.chatpan.ui.auth

import androidx.lifecycle.MutableLiveData
import com.ai.chatpan.data.DataRepository
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.data.bean.BaseChatBean
import com.btpj.lib_base.base.BaseViewModel
import com.btpj.lib_base.ext.handleRequest
import com.btpj.lib_base.ext.launch
import com.btpj.lib_base.utils.LogUtil

class LoginViewModle : BaseViewModel() {

    private val _user = MutableLiveData<AuthBean>()
    val user = _user

    private val _checkCode =MutableLiveData<String>()
    val   checkCode =_checkCode


    override fun start() {


    }

    fun login(phoneNumber: String, code: String) {

    }

    fun getCode(phoneNumber: String) {
        launch({


            handleRequest(DataRepository.getCode(phoneNumber), {

                _checkCode.value = it.code.toString()
            })
        })
    }

}