package com.example.practicebuttonconvert2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタン１がクリックされた時の処理
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ボタンのビューを取得する
                Button btn = findViewById(R.id.button1);
                // ボタンからテキストを取得し、String型に変換して変数に代入する
                String s = btn.getText().toString();
                Toast.makeText(MainActivity.this, s + "の合格を目指します。", Toast.LENGTH_SHORT).show();
            }
        });

        // ボタン２がクリックされた時の処理
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ボタンのビューを取得する
                Button btn = findViewById(R.id.button2);
                // ボタンからテキストを取得し、String型に変換して変数に代入する
                String s = btn.getText().toString();
                Toast.makeText(MainActivity.this, s + "の合格を目指します。", Toast.LENGTH_SHORT).show();
            }
        });

        // ボタン３がクリックされた時の処理
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ボタンのビューを取得する
                Button btn = findViewById(R.id.button3);
                // ボタンからテキストを取得し、String型に変換して変数に代入する
                String s = btn.getText().toString();
                Toast.makeText(MainActivity.this, s + "の合格を目指します。", Toast.LENGTH_SHORT).show();
            }
        });

        // ボタン４がクリックされた時の処理
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ボタンのビューを取得する
                Button btn = findViewById(R.id.button4);
                // ボタンからテキストを取得し、String型に変換して変数に代入する
                String s = btn.getText().toString();
                Toast.makeText(MainActivity.this, s + "の合格を目指します。", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
