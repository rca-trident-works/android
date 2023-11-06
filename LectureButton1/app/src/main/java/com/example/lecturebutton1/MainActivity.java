package com.example.lecturebutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Toast.makeText(this, "リスナー: ボタン1が押されました", Toast.LENGTH_SHORT).show();
        }
        if (v.getId() == R.id.button2) {
            Toast.makeText(this, "リスナー: ボタン2が押されました", Toast.LENGTH_SHORT).show();
        }
    }
}