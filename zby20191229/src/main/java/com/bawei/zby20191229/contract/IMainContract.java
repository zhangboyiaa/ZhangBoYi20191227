package com.bawei.zby20191229.contract;

import com.bawei.zby20191229.model.bean.BaseBean;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public interface IMainContract {

    interface IView{
        void onMainSuccess(BaseBean baseBean);

        void onMainFailure(Throwable throwable);
    }

    interface IPresenter{
        void getMainData();
    }

    interface IModel{
        void getMainData(IModelCallBack iModelCallBack);

        interface IModelCallBack{
            void onMainSuccess(BaseBean baseBean);

            void onMainFailure(Throwable throwable);
        }
    }
}
