package com.bawei.zby20191229;

import android.util.Log;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public class Throwble implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i("zby", "uncaughtException: "+e.getMessage());
    }
}
