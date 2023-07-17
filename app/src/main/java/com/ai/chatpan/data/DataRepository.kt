package com.ai.chatpan.data

import com.ai.chatpan.data.bean.AnswerHistory
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.data.bean.VerfiyCode
import com.ai.chatpan.data.http.Api
import com.btpj.lib_base.data.bean.ApiResponse
import com.btpj.lib_base.http.RetrofitManager
import okhttp3.RequestBody

/**
 * 数据仓库
 *
 * @author LTP  2022/3/23
 */
object DataRepository : Api {

    private val service by lazy { RetrofitManager.getService(Api::class.java) }


    override suspend fun askQuestion(requestBody: RequestBody): ApiResponse<BaseChatBean> {
       return service.askQuestion(requestBody)
    }

    override suspend fun getOuterDialogHistory(roomUUID:String,outerId:String): ApiResponse<List<BaseChatBean>> {
        return service.getOuterDialogHistory(roomUUID,outerId)
    }

    override suspend fun login(requestBody: RequestBody): ApiResponse<AuthBean> {
        return service.login(requestBody)
    }

    override suspend fun getCode(phoneAccount: String): ApiResponse<VerfiyCode> {
        return service.getCode(phoneAccount)
    }


}