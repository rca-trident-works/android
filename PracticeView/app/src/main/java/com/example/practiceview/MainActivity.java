package com.example.practiceview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public WebView getPrimaryWebView() {
        return findViewById(R.id.primaryWebView);
    }

    public void updateUrlTextEdit() {
        EditText urlTextEdit = findViewById(R.id.editTextUrl);
        urlTextEdit.setText(getPrimaryWebView().getUrl());
    }

    public void init() {
        getPrimaryWebView().loadUrl("https://www.google.com");
        updateUrlTextEdit();

        // URLの自動変更
        getPrimaryWebView().setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                updateUrlTextEdit();
            }
        });

        // Go Button
        findViewById(R.id.buttonAccessUrl).setOnClickListener(v -> {
            EditText urlTextEdit = findViewById(R.id.editTextUrl);
            getPrimaryWebView().loadUrl(urlTextEdit.getText().toString());
        });
    }
}