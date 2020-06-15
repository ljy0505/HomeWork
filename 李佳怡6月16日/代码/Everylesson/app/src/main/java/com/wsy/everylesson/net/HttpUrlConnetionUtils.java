package com.wsy.everylesson.net;

import java.util.HashMap;

/**
 * httpurl   请求方式---   切换使用  不需要更改代码，做到网络请求的切换
 * 工厂设计模式
 * 工厂设计类
 */
public class HttpUrlConnetionUtils implements INetWork{

    private static  HttpUrlConnetionUtils httpUrlConnetionUtils;

    private HttpUrlConnetionUtils() {
    }

    public static HttpUrlConnetionUtils getInstance() {
        if(httpUrlConnetionUtils == null){
            synchronized (RetrofitUtils.class){
                if (httpUrlConnetionUtils == null){
                    httpUrlConnetionUtils = new HttpUrlConnetionUtils();
                }
            }
        }
        return httpUrlConnetionUtils;
    }

    @Override
    public <T> void get(String url,ResultCallBack<T> netCallBack) {
//        也做网络请求
//将请求到的结果通过咱们的   INetCallBack   返回的
    }

    @Override
    public <T> void get(String url, HashMap<String, String> s,ResultCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url,ResultCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> s,ResultCallBack<T> netCallBack) {

    }
}
