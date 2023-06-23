package com.ai.chatpan.data.http

import com.ai.chatpan.data.bean.ChatPanBean
import com.btpj.lib_base.data.bean.ApiResponse
import com.btpj.lib_base.data.bean.PageResponse
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Http接口，Retrofit的请求Service
 *
 * @author LTP  2022/3/21
 */
interface Api {

    /** 登录 */
    @POST("askByOuterId")
    suspend fun askQuestion(
        @Body  requestBody: RequestBody
    ): ApiResponse<ChatPanBean>


}