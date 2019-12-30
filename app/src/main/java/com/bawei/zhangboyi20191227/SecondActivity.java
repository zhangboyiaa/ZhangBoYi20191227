package com.bawei.zhangboyi20191227;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date:2019/12/28
 * author:张博一(zhangboyi)
 * function:
 */
public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.bu)
    Button bu;
    @BindView(R.id.buu)
    Button buu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bu, R.id.buu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu:

                break;
            case R.id.buu:
                EventBus.getDefault().post("asdadasdasd");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onGetString(String string){
        Toast.makeText(this, "接收成功"+string, Toast.LENGTH_SHORT).show();
    }
}
