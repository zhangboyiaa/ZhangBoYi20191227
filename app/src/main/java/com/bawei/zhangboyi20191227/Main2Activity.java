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
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.edit_aa)
    EditText editAa;
    @BindView(R.id.butt_bb)
    Button buttBb;
    @BindView(R.id.butt_cc)
    Button buttCc;
    @BindView(R.id.butt_dd)
    Button buttDd;
    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        CodeUtils.init(this);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imageView, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "报错", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });

        NetUtile.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/student/clazzstudent.json", new NetUtile.MyCallBack() {
            @Override
            public void ongetJson(String json) {
                Toast.makeText(Main2Activity.this, json, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(Main2Activity.this, "报错", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.butt_bb, R.id.butt_cc, R.id.butt_dd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.butt_bb:
                String s = editAa.getText().toString();
                if (!TextUtils.isEmpty(s)){
                    Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                    imageView.setImageBitmap(image);
                }
                break;
            case R.id.butt_cc:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.butt_dd:
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
                Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(Main2Activity.this, "报错", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
