package com.ai.chatpan.data

import com.ai.chatpan.data.bean.AnswerHistory
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.data.bean.VerfiyCode
import com.ai.chatpan.data.http.Api
import com.ai.chatpan.data.http.ApiV2
import com.btpj.lib_base.data.bean.ApiResponse
import com.btpj.lib_base.http.RetrofitManager
import com.btpj.lib_base.http.RetrofitManagerV2
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



}