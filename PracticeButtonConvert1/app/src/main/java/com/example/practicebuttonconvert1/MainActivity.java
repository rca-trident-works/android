package com.example.practicebuttonconvert1;

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
        findViewById(R.id.button1).setOnClickListener(
                view -> {
                    // ボタンのビューを取得する
                    Button btn = findViewById(R.id.button1);

                    // getTextメソッドにより，表示文字を取り出す。
                    // そのままだと異なる型のため，toStringメソッドによりString型へ型変換する。
                    String s = btn.getText().toString();

                    // トースト表示する。
                    Toast.makeText(MainActivity.this, s + "ですね", Toast.LENGTH_SHORT).show();
                }
        );

        // ボタン２がクリックされた時の処理
        findViewById(R.id.button2).setOnClickListener(
                view -> {
                    // ボタンのビューを取得する
                    Button btn = findViewById(R.id.button2);
                    String s = btn.getText().toString();
                    Toast.makeText(MainActivity.this, s + "ですね", Toast.LENGTH_SHORT).show();
                }
        );

        // ボタン３がクリックされた時の処理
        findViewById(R.id.button3).setOnClickListener(
                view -> {
                    // ボタンのビューを取得する
                    Button btn = findViewById(R.id.button3);
                    String s = btn.getText().toString();
                    Toast.makeText(MainActivity.this, s + "ですね", Toast.LENGTH_SHORT).show();
                }
        );

        // ボタン４がクリックされた時の処理
        findViewById(R.id.button4).setOnClickListener(
                view -> {
                    // ボタンのビューを取得する
                    Button btn = findViewById(R.id.button4);
                    String s = btn.getText().toString();
                    Toast.makeText(MainActivity.this, s + "ですね", Toast.LENGTH_SHORT).show();
                }
        );
    }
}
