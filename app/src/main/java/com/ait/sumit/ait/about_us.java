package com.ait.sumit.ait;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class about_us extends AppCompatActivity {

    String about="about.html";
    String asset = "file:///android_asset/";
    String getAbout=asset+about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        WebView mywebview = (WebView) this.findViewById(R.id.webView2);
        mywebview.getSettings() .setJavaScriptCanOpenWindowsAutomatically(true);
        mywebview.loadUrl(getAbout);
    }

}
