package com.bawei.zby20191229.base;

import android.view.View;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public abstract class BasePresenter<V> {

    protected V view;

    public void attach(V view){
        this.view = view;
    }

    public void detach(){
        view = null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
