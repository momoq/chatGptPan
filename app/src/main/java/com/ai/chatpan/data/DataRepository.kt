package com.ai.chatpan.data

import com.ai.chatpan.data.bean.ChatPanBean
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


    override suspend fun askQuestion(requestBody: RequestBody): ApiResponse<ChatPanBean> {
       return service.askQuestion(requestBody)
    }
}