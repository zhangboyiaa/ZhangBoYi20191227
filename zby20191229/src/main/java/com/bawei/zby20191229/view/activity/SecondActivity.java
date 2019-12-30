package com.bawei.zby20191229.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.zby20191229.R;
import com.bawei.zby20191229.base.BaseActivity;
import com.bawei.zby20191229.base.BasePresenter;
import com.bawei.zby20191229.model.bean.BaseBean;
import com.bawei.zby20191229.model.bean.Bean;
import com.bawei.zby20191229.presenter.MainPresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {


    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.buta)
    Button buta;
    @BindView(R.id.butb)
    Button butb;
    @BindView(R.id.butc)
    Button butc;
    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter providerPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        CodeUtils.init(this);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imageView, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "出错", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @OnClick({R.id.buta, R.id.butb, R.id.butc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buta:
                String s = editName.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    imageView.setImageBitmap(image);
                }
                break;
            case R.id.butb:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.butc:
                CodeUtils.analyzeByPhotos(this);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(SecondActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(SecondActivity.this, "报错", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getBaseBean(Bean bean ){
        Toast.makeText(SecondActivity.this, "接收成功"+bean.getName(), Toast.LENGTH_SHORT).show();
    }
}
