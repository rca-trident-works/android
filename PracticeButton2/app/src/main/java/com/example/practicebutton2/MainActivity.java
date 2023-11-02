package com.example.practicebutton2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText1 = (EditText)findViewById(R.id.editText1);
                        TextView textView1 = (TextView)findViewById(R.id.textView1);

                        textView1.setText(textView1.getText() + editText1.getText().toString());
                    }
                }
        );
    }
}