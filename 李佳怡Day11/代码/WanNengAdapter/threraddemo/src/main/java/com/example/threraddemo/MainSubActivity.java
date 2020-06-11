package com.example.threraddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainSubActivity extends AppCompatActivity {
    private static final int DATA = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub);
    }

    public void startThred(View view) {
        //是Thread的子类
        HandlerThread thread = new HandlerThread("HandlerTread");
        thread.start();  //必然是一个子线程

        //在子线程里面的Handler
        Handler handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == DATA) {
                    String str = (String) msg.obj;
                    Log.e("TAG", "当前线程是：" + Thread.currentThread().getName() + "接收到的消息是：" + str);
                }
            }
        };



        //下面的是在主线程发送消息
        Message msg = Message.obtain();
        msg.what = DATA;
        msg.obj = "哈哈";
        handler.sendMessage(msg);


    }
}
