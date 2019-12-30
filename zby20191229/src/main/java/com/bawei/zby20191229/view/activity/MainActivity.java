package com.bawei.zby20191229.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.zby20191229.R;
import com.bawei.zby20191229.base.BaseActivity;
import com.bawei.zby20191229.contract.IMainContract;
import com.bawei.zby20191229.model.bean.BaseBean;
import com.bawei.zby20191229.model.bean.Bean;
import com.bawei.zby20191229.presenter.MainPresenter;
import com.bawei.zby20191229.view.adapter.MyAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IView {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
        presenter.getMainData();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter providerPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMainSuccess(BaseBean baseBean) {
        List<BaseBean.DataBean> data = baseBean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recycler.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(data);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                EventBus.getDefault().postSticky(new Bean("zby","20"));
                startActivity(intent);
            }
        });
        recycler.setAdapter(myAdapter);

    }

    @Override
    public void onMainFailure(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
