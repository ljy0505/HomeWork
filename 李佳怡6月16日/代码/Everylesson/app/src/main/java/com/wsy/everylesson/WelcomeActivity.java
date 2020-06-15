package com.wsy.everylesson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_splash_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为无标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

    /*    //获取组件
        RelativeLayout r1_splash = (RelativeLayout)findViewById(R.id.r1_splash);
        tv_splash_version = (TextView)findViewById(R.id.tv_splash_version);

        tv_splash_version.setText("版本号：" + getVersion());*/

/*        //背景透明度变化3秒内从0.3变到1.0
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(3000);
        r1_splash.startAnimation(aa);*/
        ImageView welcome_img = findViewById(R.id.welcome_img);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.toumingdu_set);
        animation.setDuration(2000);
        welcome_img.startAnimation(animation);

        //创建Timer对象
        Timer timer = new Timer();
        //创建TimerTask对象
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        };
        //使用timer.schedule（）方法调用timerTask，定时3秒后执行run
        timer.schedule(timerTask, 3000);
    }

}
