package com.btpj.lib_base.http

import com.btpj.lib_base.BaseApp.Companion.appContext
import com.btpj.lib_base.data.local.IpManager
import com.btpj.lib_base.http.interceptor.CacheInterceptor
import com.btpj.lib_base.http.interceptor.HeaderInterceptor
import com.btpj.lib_base.http.interceptor.logInterceptor
import com.btpj.lib_base.utils.LogUtil
//import com.franmontiel.persistentcookiejar.cache.SetCookieCache
//import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
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
object RetrofitManager {
    /** 请求超时时间 */
    private const val TIME_OUT_SECONDS = 10

    /** 请求cookie */
//    val cookieJar: CookieJar by lazy {
//        okhttp3.CookieJar(
//            SetCookieCache(),
//            SharedPrefsCookiePersistor(appContext)
//        )
//    }

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
    val BASE_URL = IpManager.getDefaultIP()

    /** OkHttpClient相关配置 */
    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            // 请求过滤器
            .addInterceptor(HeaderInterceptor("0648a94fc1f58091248eb43dad68a99b54b95e62b4f4f703a2368133ecd4b83a"))
            .addInterceptor(logInterceptor)
            //设置缓存配置,缓存最大10M,设置了缓存之后可缓存请求的数据到data/data/包名/cache/net_cache目录中
            .cache(Cache(File(appContext.cacheDir, "net_cache"), 10 * 1024 * 1024))
            //添加缓存拦截器 可传入缓存天数
            .addInterceptor(CacheInterceptor(30))
            // 请求超时时间
            .connectTimeout(TIME_OUT_SECONDS.toLong(), TimeUnit.SECONDS)
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
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl ?: BASE_URL)
            .build()
            .create(serviceClass)
    }
}