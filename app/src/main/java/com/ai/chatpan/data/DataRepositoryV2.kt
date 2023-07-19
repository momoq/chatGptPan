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
object DataRepositoryV2 : ApiV2 {


    private val serviceUser by lazy { RetrofitManagerV2.getService(ApiV2::class.java) }




    override suspend fun sendCmsCode(requestBody: RequestBody): ApiResponse<String> {
        return serviceUser.sendCmsCode(requestBody)
    }

    override suspend fun login(requestBody: RequestBody): ApiResponse<AuthBean> {
        return serviceUser.login(requestBody)
    }


}