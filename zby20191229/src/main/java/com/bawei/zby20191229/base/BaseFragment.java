package com.bawei.zby20191229.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(layoutId(), container, false);
        presenter = providerPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }
        initView(inflate);
        return inflate;
    }

    protected abstract void initView(View inflate);

    protected abstract P providerPresenter();

    protected abstract int layoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
