package com.ai.chatpan.data.http

import android.telecom.PhoneAccount
import com.ai.chatpan.data.bean.AnswerHistory
import com.ai.chatpan.data.bean.AuthBean
import com.ai.chatpan.data.bean.BaseChatBean
import com.ai.chatpan.data.bean.ChatPanBean
import com.ai.chatpan.data.bean.VerfiyCode
import com.btpj.lib_base.data.bean.ApiResponse
import com.btpj.lib_base.data.bean.PageResponse
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Http接口，Retrofit的请求Service
 *
 * @author LTP  2022/3/21
 */
interface ApiV2 {


    @POST("authorization/register/sendCmsCode")
    suspend fun sendCmsCode(@Body requestBody: RequestBody):ApiResponse<String>



    @POST("authorization/register/smsLogin")
    suspend fun login(@Body requestBody: RequestBody):ApiResponse<AuthBean>


}