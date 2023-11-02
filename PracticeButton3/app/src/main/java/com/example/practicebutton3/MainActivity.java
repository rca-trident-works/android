package com.example.practicebutton3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SeekBar seekBar = findViewById(R.id.seekBar1);
                    Toast.makeText(MainActivity.this, "体重: " + seekBar.getProgress() + "kg", Toast.LENGTH_SHORT).show();
                }
            }
        );

        findViewById(R.id.button2).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SeekBar seekBar = findViewById(R.id.seekBar1);
                    seekBar.setProgress(0);
                    Toast.makeText(MainActivity.this, "リセットしました。", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }
}