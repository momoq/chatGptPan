package com.ai.chatpan.base;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.btpj.lib_base.BaseApp;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MyApp extends BaseApp {


    private static final String APP_ID = "wxc191178711f7a1ac";

    static IWXAPI mWxApi;

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        regToWx();
    }

    private void regToWx() {
        mWxApi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        mWxApi.registerApp(APP_ID);

    }

    public static IWXAPI getmWxApi() {
        return mWxApi;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        CrashReport.initCrashReport(getApplicationContext(), "83868d6604", true);
    }


}
