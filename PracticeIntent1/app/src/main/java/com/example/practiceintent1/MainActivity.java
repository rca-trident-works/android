package com.example.practiceintent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MyGlobals myGlobals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGlobals = (MyGlobals) getApplication();

        // Button1
        findViewById(R.id.button1).setOnClickListener(v -> {
            myGlobals.editText1Str = ((EditText) findViewById(R.id.editText1)).getText().toString();
            Intent intent = new Intent(MainActivity.this, NextActivity.class);
            startActivity(intent);
        });

        // ButtonFinish1
        findViewById(R.id.buttonFinish1).setOnClickListener(v -> {
            finish();
        });
    }
}