package com.example.everyclass;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.everyclass.R.*;

public class MainActivity extends AppCompatActivity {

    int count = 3;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            count--;
            if (count == 0) {
                timer.cancel();
                Intent intent = new Intent(MainActivity.this, PageActivity.class);
                startActivity(intent);
            }
            return false;
        }
    });
    private Timer timer;
    private ImageView mImgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        initView();
    }

    private void initView() {
        mImgMain = (ImageView) findViewById(id.img_main);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000, 1000);

    }
}
