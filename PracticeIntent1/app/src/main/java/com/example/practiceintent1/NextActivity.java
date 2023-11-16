package com.example.practiceintent1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class NextActivity extends AppCompatActivity {

    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        myGlobals = (MyGlobals) getApplication();

        // ButtonShow2
        findViewById(R.id.buttonShow2).setOnClickListener(v -> {
            ((EditText) findViewById(R.id.editText2)).setText(myGlobals.editText1Str + "ですね");
        });

        // Button2
        findViewById(R.id.button2).setOnClickListener(v -> {
            myGlobals.editText1Str = ((EditText) findViewById(R.id.editText2)).getText().toString();
            Intent intent = new Intent(NextActivity.this, NextNextActivity.class);
            startActivity(intent);
        });

        // ButtonFinish2
        findViewById(R.id.buttonFinish2).setOnClickListener(v -> {
            finish();
        });
    }
}