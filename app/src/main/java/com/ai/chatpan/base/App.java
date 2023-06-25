package com.ai.chatpan.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.btpj.lib_base.BaseApp;
import com.tencent.bugly.crashreport.CrashReport;

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
//        Bugly.init(applicationContext, "83868d6604", false);

    }
}
