package com.example.practicebutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1 || v.getId() == R.id.button2) {
            Button button1 = (Button)findViewById(R.id.button1);
            Button button2 = (Button)findViewById(R.id.button2);
            if (v.getId() == R.id.button1) {
                button1.setText("クリックされました");
                button2.setText("クリックして");
            } else {
                button1.setText("クリックして");
                button2.setText("クリックされました");
            }
        }
    }
}