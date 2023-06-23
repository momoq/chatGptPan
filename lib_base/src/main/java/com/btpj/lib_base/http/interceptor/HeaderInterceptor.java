package com.btpj.lib_base.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private final String authToken;

    public HeaderInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        //向 Request 中添加 Header
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization","Bearer"+ authToken);

        Request request = requestBuilder.build();
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
