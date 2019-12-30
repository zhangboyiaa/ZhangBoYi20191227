package com.bawei.myapplication;

import android.util.Log;

/**
 * date:2019/12/30
 * author:张博一(zhangboyi)
 * function:
 */
public class Throwble implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("zby","e"+e.getMessage());
    }
}
