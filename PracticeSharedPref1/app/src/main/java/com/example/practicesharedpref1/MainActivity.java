package com.example.practicesharedpref1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shared Preferences
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);



        final EditText et1 = findViewById(R.id.editText1);
        final EditText et2 = findViewById(R.id.editText2);

        findViewById(R.id.buttonSave).setOnClickListener(
                (View view) -> {
                    SharedPreferences.Editor e = pref.edit();
                    e.putString("variable1", et1.getText().toString());
                    e.putString("variable2", et2.getText().toString());
                    e.apply();
                }
        );

        findViewById(R.id.buttonLoad).setOnClickListener(
                (View view) -> {
                    et1.setText(pref.getString("variable1", "値はありません"));
                    et2.setText(pref.getString("variable2", "値はありません"));
                }
        );

        findViewById(R.id.buttonReset).setOnClickListener(
                (View view) -> {
                    et1.setText("");
                    et2.setText("");
                }
        );

    }
}