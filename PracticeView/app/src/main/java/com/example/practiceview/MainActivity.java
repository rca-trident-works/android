package com.example.practiceview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

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

    public void updateUrlAndTitle() {
        WebView primaryWebView = getPrimaryWebView();
        EditText urlTextEdit = findViewById(R.id.editTextUrl);
        urlTextEdit.setText(primaryWebView.getUrl());
//        TextView titleTextView = findViewById(R.id.textViewTitle);
//        titleTextView.setText(primaryWebView.getTitle());
    }

    public void init() {
        WebView primaryWebView = getPrimaryWebView();

        primaryWebView.loadUrl("https://www.google.com/");
        primaryWebView.getSettings().setJavaScriptEnabled(true);
        updateUrlAndTitle();


        // == Listener ==

        // WebViewの内容が切り替わったときにURLを更新する
        primaryWebView.setWebViewClient(new WebViewClient() {

            // onPageFinishedだと
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                // Workaround: 500ms待機してから更新する
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateUrlAndTitle();
            }
        });



        // Go Button
        findViewById(R.id.buttonAccessUrl).setOnClickListener(v -> {
            EditText urlTextEdit = findViewById(R.id.editTextUrl);
            getPrimaryWebView().loadUrl(urlTextEdit.getText().toString());
        });

        // toggleJS
        findViewById(R.id.switchJavaScript).setOnClickListener(v -> {
            getPrimaryWebView().getSettings().setJavaScriptEnabled(((android.widget.Switch) v).isChecked());
        });

        findViewById(R.id.buttonBack).setOnClickListener(v -> {
            getPrimaryWebView().goBack();
        });

        findViewById(R.id.buttonForward).setOnClickListener(v -> {
            getPrimaryWebView().goForward();
        });
    }
}