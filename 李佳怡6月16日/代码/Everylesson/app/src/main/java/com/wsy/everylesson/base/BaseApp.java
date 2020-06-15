package com.wsy.everylesson.base;

import android.app.Application;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.wsy.everylesson.util.SpUtil;


//app一上来会先走application是有条件的,要求app原来所在的进程被杀死才会走,
//如果仅仅是activity销毁了,不一定走
//Android 系统为了提高app启动的速度,在界面销毁之后,进程不会被杀死, 而是变成一个空进程
public class BaseApp extends Application {
    public static BaseApp sContext;
    public static boolean isLogin;
    public static String mToken;

    public static Resources getRes() {
        return sContext.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        mToken = (String) SpUtil.getParam(Constants.TOKEN, "");
        if (TextUtils.isEmpty(mToken)){
           isLogin=false;
        }else {
            isLogin=true;
        }
    }


}
