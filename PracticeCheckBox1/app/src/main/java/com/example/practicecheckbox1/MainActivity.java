package com.example.practicecheckbox1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkBoxA～checkBoxCを取り出し，cbA～cbCへ代入。
        final CheckBox cbA = findViewById(R.id.checkBoxA);
        final CheckBox cbB = findViewById(R.id.checkBoxB);
        final CheckBox cbC = findViewById(R.id.checkBoxC);

        //cbA～cbCのチェック状態が変化したときに呼び出されるリスナーを登録。
        cbA.setOnCheckedChangeListener(this);
        cbB.setOnCheckedChangeListener(this);
        cbC.setOnCheckedChangeListener(this);


        findViewById(R.id.buttonAll0).setOnClickListener(
                view -> {
                    cbA.setChecked(false); //checkBoxAのチェック状態をfalse(OFF)にする。
                    cbB.setChecked(false);
                    cbC.setChecked(false);
                }
        );

        findViewById(R.id.buttonAll1).setOnClickListener(
                view -> {
                    cbA.setChecked(true); //checkBoxAのチェック状態をtrue(ON)にする。
                    cbB.setChecked(true);
                    cbC.setChecked(true);
                }
        );

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        final CheckBox cbA = findViewById(R.id.checkBoxA);
        final CheckBox cbB = findViewById(R.id.checkBoxB);
        final CheckBox cbC = findViewById(R.id.checkBoxC);

        final TextView tv1 = findViewById(R.id.textView1);

        int value = 0;  //初期値は0

        //CheckBoxCがCheckされていたら  isChecked()はtrueならcheckされている
        if (cbC.isChecked() == true) { //	      falseならcheckされていない
            value += 1; //2の0乗である1を加算する
        }

        //CheckBoxBがCheckされていたら　※「== true」は省略されることが多い
        if (cbB.isChecked()) {
            value += 2; //2の1乗である2を加算する
        }

        if (cbA.isChecked()) {
            value += 4; //2の2乗である4を加算する
        }

        //valueの値をString型に型変換し，textView1の表示文字にする。
        tv1.setText(String.valueOf(value));
    }
}
