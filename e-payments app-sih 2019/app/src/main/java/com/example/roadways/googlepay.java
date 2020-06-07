package com.example.roadways;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.common.api.GoogleApiClient;

public class googlepay extends AppCompatActivity {
    WebView myWebView;
    private GoogleApiClient mGoogleApiClient;
    //private Masked
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlepay);
         myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        //myWebView.loadUrl("https://paytm.com/challan-bill-payment/mumbai");
        myWebView.loadUrl("https://securegw.paytm.in/link/20062/LL_3020447");
        //WebView myWebView = new WebView(this);
        //setContentView(myWebView);
        //myWebView.loadUrl("https://www.google.com/");
        WebSettings webSettings=myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}
