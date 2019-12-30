package com.bawei.zhangboyi20191227;

import android.os.Handler;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2019/12/27
 * author:张博一(zhangboyi)
 * function:
 */
public class NetUtile {

    private static NetUtile netUtile;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtile() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }

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

    public void getJsonGet(String getUrl, final MyCallBack myCallBack){
        final Request request = new Request.Builder()
                .get()
                .url(getUrl)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
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
                    final String string = response.body().string();
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
                            myCallBack.onError(new Exception("爆粗"));
                        }
                    });
                }
            }
        });
    }

    public void getJsonPost(String postUrl, final Map<String,String> map, final MyCallBack myCallBack){

        FormBody.Builder builder = new FormBody.Builder();

        for (String key : map.keySet()){
            String value = map.get(key);

            builder.add(key,value);
        }

        FormBody formBody = builder.build();

        final Request request = new Request.Builder()
                .post(formBody)
                .url(postUrl)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
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
                    final String string = response.body().string();
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
                            myCallBack.onError(new Exception("爆粗"));
                        }
                    });
                }
            }
        });
    }

    public interface MyCallBack{
        void ongetJson(String json);

        void onError(Throwable throwable);
    }
}
