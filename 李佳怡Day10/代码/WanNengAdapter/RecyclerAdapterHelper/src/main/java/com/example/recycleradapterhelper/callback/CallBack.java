package com.example.recycleradapterhelper.callback;

public interface CallBack<T> {
    public <T> void onScuess(T t);
    public void onFile(String error);
}
