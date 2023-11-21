package com.example.practiceradiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((RadioGroup) findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(
                (RadioGroup radioGroup, int i) -> {
                    if (i == R.id.radioButtonBlue) {
                        radioGroup.setBackgroundColor(Color.BLUE);
                    } else if (i == R.id.radioButtonRed) {
                        radioGroup.setBackgroundColor(Color.RED);
                    } else if (i == R.id.radioButtonYellow) {
                        radioGroup.setBackgroundColor(Color.YELLOW);
                    }
                }
        );

        findViewById(R.id.buttonNext).setOnClickListener((View view) -> {
            int i = ((RadioGroup) findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();

            if (i == R.id.radioButtonBlue) {
                ((RadioButton) findViewById(R.id.radioButtonYellow)).setChecked(true);
            } else if (i == R.id.radioButtonYellow) {
                ((RadioButton) findViewById(R.id.radioButtonRed)).setChecked(true);
            } else if (i == R.id.radioButtonRed) {
                ((RadioButton) findViewById(R.id.radioButtonBlue)).setChecked(true);
            }
        });
    }
}