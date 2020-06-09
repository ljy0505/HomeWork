package com.example.recycleradapterhelper.engine;

import com.example.recycleradapterhelper.callback.CallBack;
import com.example.recycleradapterhelper.entity.DataInfo;
import com.example.recycleradapterhelper.util.GsonUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataServer {
    //网路请求的方法
    public static <T> void getData(String url, final CallBack<Object> callBack) {
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(20, TimeUnit.SECONDS).
                readTimeout(15, TimeUnit.SECONDS).build();
        final Request rquest = new Request.Builder().url(url).build();
        client.newCall(rquest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFile(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                DataInfo dataInfo = GsonUtil.str2Bean(string, DataInfo.class);
                callBack.onScuess(dataInfo);
            }
        });
    }
}
