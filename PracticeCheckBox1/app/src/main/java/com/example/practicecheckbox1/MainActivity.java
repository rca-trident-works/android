package com.example.practicecheckbox1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checkBoxA～checkBoxCを取り出し，cbA～cbCへ代入。
        final CheckBox cbA = findViewById(R.id.checkBoxA);
        final CheckBox cbB = findViewById(R.id.checkBoxB);
        final CheckBox cbC = findViewById(R.id.checkBoxC);

        //textView1を取り出し，tv1へ代入。
        final TextView tv1 = findViewById(R.id.textView1);

        cbA.setOnCheckedChangeListener(                            //【結び付け】
                new CompoundButton.OnCheckedChangeListener() {  //【準備】
                    @Override                                          //【処理】
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        //重み　：  4   2   1
                        //         □   □   □
                        //ビュー：cbA cbB cbC

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
        );

        cbB.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            //重み　：  4   2   1
                            //         □   □   □
                            //ビュー：cbA cbB cbC

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

        );

        cbC.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                //重み　：  4   2   1
                                //         □   □   □
                                //ビュー：cbA cbB cbC

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
                });

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

}
