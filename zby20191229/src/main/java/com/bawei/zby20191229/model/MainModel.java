package com.bawei.zby20191229.model;

import com.bawei.zby20191229.contract.IMainContract;
import com.bawei.zby20191229.model.bean.BaseBean;
import com.bawei.zby20191229.utile.NetUtile;
import com.google.gson.Gson;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public class MainModel implements IMainContract.IModel {
    @Override
    public void getMainData(IModelCallBack iModelCallBack) {
        String url = "http://blog.zhaoliang5156.cn/api/shop/fulishe1.json";
        NetUtile.getInstance().getJsonGet(url, new NetUtile.MyCallBack() {
            @Override
            public void ongetJson(String json) {
                BaseBean baseBean = new Gson().fromJson(json, BaseBean.class);
                iModelCallBack.onMainSuccess(baseBean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallBack.onMainFailure(throwable);
            }
        });
    }
}
