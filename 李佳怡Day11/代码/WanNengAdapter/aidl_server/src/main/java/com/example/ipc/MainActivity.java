package com.example.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        MyCallable callable = new MyCallable();
        Thread thread = new Thread(new FutureTask(callable));
        thread.start();*/
    }

/*    class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            Log.e("TAG", "当前线程名字是：" + Thread.currentThread().getName());
            return "哈哈哈";
        }
    }*/
}
