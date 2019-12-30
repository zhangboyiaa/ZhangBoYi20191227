package com.bawei.myapplication.base;

/**
 * date:2019/12/30
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
