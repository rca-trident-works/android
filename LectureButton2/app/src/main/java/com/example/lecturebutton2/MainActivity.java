package com.example.lecturebutton2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "無名クラス: ボタン1が押されました", Toast.LENGTH_LONG).show();
                    }
                }
        );

        findViewById(R.id.button2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "無名クラス: ボタン2が押されました", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}