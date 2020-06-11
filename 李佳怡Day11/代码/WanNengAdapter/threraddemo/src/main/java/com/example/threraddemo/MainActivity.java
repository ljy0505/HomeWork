package com.example.threraddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.Date;

/**
 * 第一个线程向第二个线程发送消息
 */
public class MainActivity extends AppCompatActivity {
    private static final int DATA = 0;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThred(View view) {
        switch (view.getId()) {
            case R.id.btn_start_one:
                startOnThread();
                break;
            case R.id.btn_start_two:
                startTwoThread();
                break;
        }
    }

    //第一个线程  发送
    private void startOnThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String strData = "哈哈哈，我是第一个子线程里面的数据";
                Message msg = Message.obtain();
                msg.obj = strData;
                msg.what = DATA;
                if (mHandler != null)
                    mHandler.sendMessage(msg);
            }
        }.start();

    }

    //第二个线程  接收
    private void startTwoThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                mHandler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);

                        if (msg.what == DATA) {
                            String str = (String) msg.obj;
                            Log.e("TAG", "从第一个线程获取到的数据是：" + str);
                        }

                    }
                };
                Looper.loop();
            }
        }.start();


    }

}
