package com.wsy.everylesson.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.mmkv.MMKV;


public class MyApplication extends Application {

   public static MyApplication myApplication;

//    初始化
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
//        初始化内存泄漏检测工具
        //初始化mmkv
        MMKV.initialize(this);
        //todo 跑马灯用到的工具类，失败

//        RxTool.init(this);
        initLeakCanary();
//        注册监听每个acitivyt的生命周期，便于栈管理
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

    }

//    Lifecycle---
    ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };

    private void initLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }

}
