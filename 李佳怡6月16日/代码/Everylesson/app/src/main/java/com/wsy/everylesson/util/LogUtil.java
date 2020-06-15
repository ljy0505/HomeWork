package com.wsy.everylesson.util;

import android.util.Log;

import com.wsy.everylesson.base.Constants;


public class LogUtil {
    public static void logd(String tag, String msg){
        if (Constants.isDebug){
            Log.d(tag, msg);
        }
    }

    public static void print(String msg){
        if (Constants.isDebug){
            System.out.println(msg);
        }
    }
}
