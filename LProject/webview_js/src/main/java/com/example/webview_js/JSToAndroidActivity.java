package com.example.webview_js;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JSToAndroidActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mWebView = findViewById(R.id.webview);
        mWebSettings = mWebView.getSettings();

        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        //---------------------------JS调用Android第二种方式------------------------------------
      /*  mWebView.loadUrl("file:///android_asset/demo3.html");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if ("js".equals(uri.getScheme())) {

                    if (uri.getAuthority().equals("webview")) {

                        //TODO 可以执行Android代码了
                        Log.e("TAG", "执行Android代码了");
                        //打开相册，获取图片，裁剪

                    }

                    return true;
                }

                return super.shouldOverrideUrlLoading(view, url);
            }

        });*/

//------------------------------------JS调用Android第三种方式-------------------
        mWebView.loadUrl("file:///android_asset/demo4.html");
        mWebView.setWebChromeClient(new WebChromeClient() {


            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {


                Uri uri = Uri.parse(message);
                    if (uri.getScheme().equals("js")) {
                        if (uri.getAuthority().equals("webview")) {

                            //TODO 可以执行Android的代码了
                            Log.e("TAG","HAHHHHHHHHHH,js调用Android了");


                            result.confirm("哈哈哈，这是要传递给JS的返回值");
                        }
                        return true;
                }

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("UWUEWUWE1111UUWEUWE", url+"======"+message+"========="+result.toString());
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
               // Log.e("UWUEWUWEUUWEUWE", url+"======"+message+"========="+result.toString());
                return super.onJsConfirm(view, url, message, result);
            }

        });

    }
}
