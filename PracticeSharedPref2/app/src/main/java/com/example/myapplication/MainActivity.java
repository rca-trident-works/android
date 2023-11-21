package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        TextView tv1 = findViewById(R.id.textView1);

        // 共有プリファレンスから値を取り出す初期設定の処理
        String s1 = pref.getString("variable3", "-1");

        // textView1にの表示文字をs1にする
        tv1.setText(s1);

        findViewById(R.id.buttonPlus).setOnClickListener(
                (View view) -> {
                    // textView1の表示文字を変数val1へ代入
                    String val1 = tv1.getText().toString();

                    // 変数val1をint型に変換し、変数val2へ代入
                    int val2 = Integer.parseInt(val1);

                    // val2をインクリメント (1増やす)
                    val2++;

                    // val2の値をString型に型変換し、val3へ代入
                    String val3 = String.valueOf(val2);

                    // textView1の表示文字をval3にする
                    tv1.setText(val3);
                }
        );

        findViewById(R.id.buttonMinus).setOnClickListener(
                (View view) -> {
                    // textView1の表示文字を変数val1へ代入
                    String val1 = tv1.getText().toString();

                    // 変数val1をint型に変換し、変数val2へ代入
                    int val2 = Integer.parseInt(val1);

                    // val2をデクリメント (1減らす)
                    val2--;

                    // val2の値をString型に型変換し、val3へ代入
                    String val3 = String.valueOf(val2);

                    // textView1の表示文字をval3にする
                    tv1.setText(val3);
                }
        );

        findViewById(R.id.buttonSave).setOnClickListener(
                (View view) -> {
                    // 共有プリファレンス　書込みの準備(最初に1回だけ)
                    SharedPreferences.Editor e = pref.edit();

                    // 変数名variable3にtv1の表示文字を書込み（必要な変数の個数分）
                    e.putString("variable3", tv1.getText().toString());

                    // 確定処理（最後に1回だけ）。ここで初めて書込みが行われる。お忘れなく。
                    e.apply();
                }
        );


    }
}