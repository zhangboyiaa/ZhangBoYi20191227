package com.bawei.myapplication;

import android.app.Application;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Thread.setDefaultUncaughtExceptionHandler(new Throwble());
    }
}
