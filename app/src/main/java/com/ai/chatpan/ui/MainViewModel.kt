package com.ai.chatpan.ui

import androidx.lifecycle.MutableLiveData
import com.ai.chatpan.data.DataRepository
import com.ai.chatpan.data.bean.AnswerHistory
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.data.bean.RequestBean
import com.btpj.lib_base.base.BaseViewModel
import com.btpj.lib_base.ext.handleRequest
import com.btpj.lib_base.ext.launch
import com.btpj.lib_base.utils.LogUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody


class MainViewModel : BaseViewModel() {

    private val _askAnswer = MutableLiveData<BaseChatBean>()
    val askAnswer = _askAnswer

    private val _askHistory = MutableLiveData<List<BaseChatBean>>(emptyList())
    val askHistory = _askHistory

    override fun start() {

    }

    fun askQuestion(
        subjectId: String,
        outerId: String,
        roomUUID: String,
        assistantUUID: String,
        question: String
    ) {
        launch({
            var requestBean = RequestBean(subjectId, outerId, roomUUID, assistantUUID, question)
            var json = Gson().toJson(requestBean)
            LogUtil.e("JSON :" + json)
            val mediaType: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody: RequestBody = RequestBody.create(mediaType, json)


            handleRequest(DataRepository.askQuestion(requestBody), {
                LogUtil.e("result  :" + it.toString())

                _askAnswer.value = it.data!!
            })
        })

    }

    fun getOuterDialogHistory(roomUUID: String, outerId: String) {
        launch({
            handleRequest(DataRepository.getOuterDialogHistory(roomUUID, outerId), {
                LogUtil.e("result  :" + it.toString())

                _askHistory.value = it.data!!
            })
        })
    }
}