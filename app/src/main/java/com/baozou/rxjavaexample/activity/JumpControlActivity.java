package com.baozou.rxjavaexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baozou.rxjavaexample.R;

/**
 * Created by lenovo on 2016/4/19.
 * 统一的跳转控制器，控制items跳转到哪个activity
 */
public class JumpControlActivity extends Activity {

    private WebView webView;
    private MyWebViewClient myWebViewClient;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        url = getIntent().getStringExtra("url");
        webView = (WebView)findViewById(R.id.control);
        myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);
        webView.loadUrl(url);
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            if(url.startsWith("aixue://")){
                String activity = url.split("=")[1];
                if(activity.equals("maths")){
                    Intent intent = new Intent(JumpControlActivity.this,MathsActivity.class);
                    startActivity(intent);
                }
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if(url.startsWith("aixue://")){
//                String activity = url.split("=")[1];
//                if(activity.equals("maths")){
//                    Intent intent = new Intent(JumpControlActivity.this,MathsActivity.class);
//                    startActivity(intent);
//                }
//            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }


}
