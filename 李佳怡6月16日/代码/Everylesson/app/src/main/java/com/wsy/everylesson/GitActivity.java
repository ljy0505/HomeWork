package com.wsy.everylesson;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class GitActivity extends AppCompatActivity {

    private WebView mWebGit;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git);
        Intent intent = getIntent();
        webUrl = intent.getStringExtra("web");
        initView();
    }

    private void initView() {
        mWebGit = (WebView) findViewById(R.id.git_web);
        mWebGit.setWebViewClient(new WebViewClient());
        mWebGit.loadUrl(webUrl);
    }
}
