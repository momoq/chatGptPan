package com.ai.chatpan.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.btpj.lib_base.BaseApp;
import com.pgyer.pgyersdk.PgyerSDKManager;
import com.pgyer.pgyersdk.pgyerenum.Features;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mmkv.MMKV;

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        CrashReport.initCrashReport(getApplicationContext(), "83868d6604", true);
//        new PgyerSDKManager.Init()
//                .setContext(base) //设置上下问对象
//                .enable(Features.CHECK_UPDATE)
//                .start();
    }
}
