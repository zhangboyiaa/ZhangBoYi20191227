package com.bawei.zby20191229.utile;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.zby20191229.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.sql.Time;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * date:2019/12/29
 * author:张博一(zhangboyi)
 * function:
 */
public class NetUtile {

    private static NetUtile netUtile;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    public static NetUtile getInstance() {
        if (netUtile == null){
            synchronized (NetUtile.class){
                if (netUtile == null){
                    netUtile = new NetUtile();
                }
            }
        }
        return netUtile;
    }

    private NetUtile() {
        handler = new Handler();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public void getJsonGet(String getUrl,MyCallBack myCallBack){
        Request request = new Request.Builder()
                .get()
                .url(getUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.ongetJson(string);
                        }
                    });
                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("报错"));
                        }
                    });
                }
            }
        });
    }

    public void getJsonPost(String postUrl, Map<String,String> map,MyCallBack myCallBack){
        FormBody.Builder builder = new FormBody.Builder();

        for (String key : map.keySet()){
            String value = map.get(key);

            builder.add(key,value);
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(postUrl)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        myCallBack.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.ongetJson(string);
                        }
                    });

                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myCallBack.onError(new Exception("报错"));
                        }
                    });
                }
            }
        });
    }

    public void getPhoto(String photoUrl, ImageView imageView){
        Glide.with(imageView)
                .load(photoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(25)))
                .into(imageView);
    }

    public interface MyCallBack{
        void ongetJson(String json);

        void onError(Throwable throwable);
    }
}
