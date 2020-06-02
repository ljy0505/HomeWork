package com.example.webview_js;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String APP_CACAHE_DIRNAME = "/abc";
    private WebView mWebview;
    private ProgressBar mProcessBar;
    private WebSettings settings;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebview = findViewById(R.id.webview);
        mProcessBar = findViewById(R.id.processbar);
        //想让网页适配webview
        settings = mWebview.getSettings();
        //支持js代码
        settings.setJavaScriptEnabled(true);
        //允许js弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //设置网页支持缩放
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //网页无图模式
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片

        //设置编码格式 为gbk  当网页出现乱码 就可以设置网页编码格式
        settings.setDefaultTextEncodingName("GBK");//设置编码格式


        mWebview.loadUrl("file:///android_asset/demo2.html");


        //---------------------------------------以下是JS(BUtton和图片都是再网页里面的)调用Android------------------------------------
        //1.要映射的那个类对象  2.类对象引用
        mWebview.addJavascriptInterface(new JsToAndroid(this), "test");

    }

    public void load_webview(View view) {
        // mWebview.loadUrl("https://www.wanandroid.com/");
        //Android调用JS代码的第一种方法
        //mWebview.loadUrl("javascript:androidCallJs(\"哈哈,我是Android里面的数据\")");
/*        //Android 4.4之后出现的api
        mWebview.evaluateJavascript("javascript:androidCallJs(\"哈哈,我是Android里面的数据\")", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e("TAG", value + "========");
            }
        });
        //两种区别： 前者不能获取js函数的返回值   后者可以获取js函数的返回值*/


        //在自己的页面里面打开网页
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("TAG", "网页开始加载了....");
                mProcessBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("TAG", "网页加载完成了");
                mProcessBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });


        //自己测
        //webview的缓存   如果有网 根据cache-ctral 是否从网络上或者缓存中加载  如果没网就从缓存中获取网页(离线加载)
        if (NetWorkUtl.isConnected(getApplicationContext())) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
        settings.setAppCacheEnabled(true);//开启 Application Caches 功能
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        settings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录


        //获取网页里面的内容(标题，logo,内容..) 获取网页加载进度
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("TAG", "网页的标题是：" + title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                Log.e("TAG", "点击的图片的url地址：" + url);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("TAG", "当前加载网页的进度是：" + newProgress);
            }
        });
    }

    /*   @Override
       public boolean onKeyDown(int keyCode, KeyEvent event) {
           if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
               Log.e("TAG","返回键被点点击了.....");
               return true;
           }
           return super.onKeyDown(keyCode, event);
       }

   */
    @Override
    public void onBackPressed() {
        //Log.e("TAG", "返回键被点点击了.....");
        if (mWebview != null && mWebview.canGoBack()) {
            String webviewUrl = mWebview.getUrl();
            //如果当前网页是这些tab(导航，体系, 项目分类，工具)的话  再点击返回键就退出程序
            if (webviewUrl.equals("https://www.wanandroid.com/index") ||
                    webviewUrl.equals("https://www.wanandroid.com/user_article") ||
                    webviewUrl.equals("https://www.wanandroid.com/navi") ||
                    webviewUrl.equals("https://www.wanandroid.com/wenda") ||
                    webviewUrl.equals("https://www.wanandroid.com/projectindex") ||
                    webviewUrl.equals("https://www.wanandroid.com/wxarticle/list/408/1") ||
                    webviewUrl.equals("https://www.wanandroid.com/project") ||
                    webviewUrl.equals("https://www.wanandroid.com/tools"))
                super.onBackPressed();
            else {
                //当前网页不是这些，有可能是其他的网页  比如：二级网页
                mWebview.goBack();
            }
        } else//如果webview不能返回了，说明现当前网页就是首页tab 对应的网页
            super.onBackPressed();
    }


/*    class JsToAndroid {
        @JavascriptInterface
        public void hello(String msg) {
            Log.e("TAG---", msg);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivityForResult(intent, 0);
        }
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
}
}

