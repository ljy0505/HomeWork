package com.example.webview_js;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsToAndroid {
    private MainActivity mContext;

    public JsToAndroid(MainActivity context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void hello(String msg) {
        Log.e("TAG---", msg);
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivityForResult(intent,0);
    }
}
