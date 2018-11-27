package com.bwei.zhouzitao.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ShowActivity extends AppCompatActivity {
    private WebView web1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        web1 = findViewById(R.id.web1);
        Intent intent = getIntent();
        String url = intent.getStringExtra("urx");
        Toast.makeText(ShowActivity.this,url, Toast.LENGTH_SHORT).show();
        web1.loadUrl(url);
        web1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }
}
