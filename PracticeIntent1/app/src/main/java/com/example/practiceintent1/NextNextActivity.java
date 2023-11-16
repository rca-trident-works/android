package com.example.practiceintent1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NextNextActivity extends AppCompatActivity {

    MyGlobals myGlobals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_next);

        myGlobals = (MyGlobals) getApplication();

        // ButtonShow3
        findViewById(R.id.buttonShow3).setOnClickListener(v -> {
            ((TextView) findViewById(R.id.textView3)).setText(myGlobals.editText1Str + "ですね");
        });

        // ButtonFinish3
        findViewById(R.id.buttonFinish3).setOnClickListener(v -> {
            finish();
        });
    }
}