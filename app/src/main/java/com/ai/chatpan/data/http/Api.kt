package com.ai.chatpan.data.http

import com.ai.chatpan.data.bean.AnswerHistory
import com.ai.chatpan.data.bean.BaseChatBean
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

    /** 提问 */
    @POST("askByOuterId")
    suspend fun askQuestion(
        @Body  requestBody: RequestBody
    ): ApiResponse<BaseChatBean>



    /** 历史问题和答案 */
    @GET("room/outerDialogHistory")
    suspend fun getOuterDialogHistory(  @Query("roomUUID") roomUUID:String,  @Query("outerId") outerId:String): ApiResponse<List<BaseChatBean>>


}