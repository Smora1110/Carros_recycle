package com.example.carros_recycle.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.carros_recycle.R;

public class ShowWebViewActivity extends AppCompatActivity {

    private WebView wvpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_view);

        //referenciamos
        wvpage = findViewById(R.id.swb);

        //atrapar info
        String URL = getIntent().getStringExtra("url");

        //webview
        wvpage.setWebViewClient(new WebViewClient());

        wvpage.loadUrl(URL);

    }
}