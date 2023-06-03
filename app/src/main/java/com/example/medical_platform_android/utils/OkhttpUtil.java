package com.example.medical_platform_android.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {
    private static OkHttpClient okHttpClient;
    static {
        okHttpClient  = new OkHttpClient.Builder()
                .build(); //产生一个客户端对象
    }
    public static void postRequestWithToken(Context context, String url1, Map<String,Object> params, final ResponseCallback responseCallback) {
        String token = SPUtil.getString(context, "token");
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null) {
            Set<String> keys = params.keySet();
            for(String key: keys) {
                Object values  = params.get(key);
                builder.add(key,values.toString());
            }
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url1)
                .post(formBody)
                .addHeader("token",token)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                responseCallback.failure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonString = response.body().string();
                responseCallback.response(jsonString);
            }
        });
    }

    public static void getRequest(String url1,ResponseCallback responseCallback){

        Request request = new Request.Builder().get().url(url1).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {//异步网络请求，开启一个子线程，调用（callback）对象的方法 //传入接口对象，实现两个方法
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                responseCallback.failure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseString = response.body().string();
                responseCallback.response(responseString);
                //json格式的字符串responseCallback.response(responseString);
            }
        });
    }

    public static void postRequest(String url1, Map<String,Object> params,final ResponseCallback responseCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        if(params != null) {
            Set<String> keys = params.keySet();
            for(String key: keys) {
                Object values  = params.get(key);
                builder.add(key,values.toString());
            }
        }

        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url1)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                responseCallback.failure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonString = response.body().string();
                responseCallback.response(jsonString);
            }
        });
    }
}
