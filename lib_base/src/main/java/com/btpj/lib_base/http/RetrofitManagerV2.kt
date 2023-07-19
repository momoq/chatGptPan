package com.btpj.lib_base.http

import com.btpj.lib_base.BaseApp.Companion.appContext
import com.btpj.lib_base.data.local.IpManager
import com.btpj.lib_base.http.interceptor.CacheInterceptor
import com.btpj.lib_base.http.interceptor.HeaderInterceptor
import com.btpj.lib_base.http.interceptor.logInterceptor
import com.btpj.lib_base.utils.LogUtil
import okhttp3.Cache
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Retrofit管理类
 *
 * @author LTP  2022/3/21
 */
object RetrofitManagerV2 {
    /** 请求超时时间 */
    private const val TIME_OUT_SECONDS = 30

    /** 请求cookie */

    class MyCookieJar : CookieJar {
        private val cookies: MutableList<Cookie> = ArrayList()
        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            this.cookies.addAll(cookies)
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            val result: MutableList<Cookie> = ArrayList()
            for (cookie in cookies) {
                if (cookie.matches(url)) {
                    result.add(cookie)
                }
            }
            return result
        }
    }

    /** 请求根地址 */
    val BASE_URL = IpManager.getUserIp()

    /** OkHttpClient相关配置 */
    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            // 请求过滤器
            .addInterceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                    .header("Connection", "Keep-Alive")
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .retryOnConnectionFailure(true)
            .addInterceptor(logInterceptor)
            //设置缓存配置,缓存最大10M,设置了缓存之后可缓存请求的数据到data/data/包名/cache/net_cache目录中
            .cache(Cache(File(appContext.cacheDir, "net_cache"), 10 * 1024 * 1024))
            //添加缓存拦截器 可传入缓存天数
            .addInterceptor(CacheInterceptor(30))
            .readTimeout(30, TimeUnit.SECONDS)
            // 请求超时时间
            .connectTimeout(30, TimeUnit.SECONDS)
            .cookieJar(MyCookieJar())
            .build()

    /**
     * Retrofit相关配置
     */
    fun <T> getService(serviceClass: Class<T>, baseUrl: String? = null): T {
        LogUtil.d(BASE_URL)
        return Retrofit.Builder()
            .client(client)
            // 使用Moshi更适合Kotlin
            .baseUrl(baseUrl ?: BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}