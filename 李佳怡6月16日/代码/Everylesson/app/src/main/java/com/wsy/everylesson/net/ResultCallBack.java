package com.wsy.everylesson.net;

public interface ResultCallBack<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
