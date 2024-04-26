package com.example.practiceexecutorservice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // showButton：押されると天気情報を表示する
        findViewById(R.id.showButton).setOnClickListener(v -> {

            /// Weatherをインスタンス化
            Weather receiver = new Weather(MainActivity.this);

            String cityID = ((EditText)findViewById(R.id.inputMapCode)).getText().toString();

            /// Weatherを実行する
            receiver.execute(cityID);   /// □①実行へ
        });
    }
}
