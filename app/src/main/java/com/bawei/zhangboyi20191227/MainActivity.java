package com.bawei.zhangboyi20191227;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_cc)
    Button buttonCc;
    @BindView(R.id.button_aa)
    Button buttonAa;
    @BindView(R.id.button_bb)
    Button buttonBb;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.edit_name)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化
        CodeUtils.init(this);

        //图片长按点击
        imageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imageview, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(MainActivity.this, "报错", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @OnClick({R.id.button_cc, R.id.button_aa, R.id.button_bb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_cc:
                //点击生成二维码
                String s = editText.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    imageview.setImageBitmap(image);
                }
                break;
            case R.id.button_aa:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.button_bb:
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
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this, "报错", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
