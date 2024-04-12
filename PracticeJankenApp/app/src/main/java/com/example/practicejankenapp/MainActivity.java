package com.example.practicejankenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imgAppTe);
        Map<Integer, JankenHandItemEnum> handItemMap = new HashMap<>();
        handItemMap.put(R.id.imgbtnPa, JankenHandItemEnum.PAPER);
        handItemMap.put(R.id.imgbtnGu, JankenHandItemEnum.ROCK);
        handItemMap.put(R.id.imgbtnChoki, JankenHandItemEnum.SCISSORS);

        View.OnClickListener listener = v -> {
            JankenHandItemEnum userChoice = handItemMap.get(v.getId());
            // 0, 1, 2のいずれかをランダムで取得
            JankenHandItemEnum appChoice = JankenHandItemEnum.getByNumber((int) (Math.random() * 3));
            assert appChoice != null;
            imageView.setImageResource(appChoice.getImageId());
            assert userChoice != null;
            pushResultToSharedPreferences(userChoice.getNumber(), appChoice.getNumber());
            showResultDialog(getResultMessage(userChoice, appChoice));
        };

        findViewById(R.id.imgbtnPa).setOnClickListener(listener);
        findViewById(R.id.imgbtnGu).setOnClickListener(listener);
        findViewById(R.id.imgbtnChoki).setOnClickListener(listener);

        // 結果表示ボタン
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            int[] resultArray = getResultFromSharedPreferences();

            Intent intent = new Intent(this, SyoritsuActivity.class);

            intent.putExtra(SyoritsuIntentConstants.TOTAL_BATTLE_COUNT, resultArray[0] + resultArray[1] + resultArray[2]);
            intent.putExtra(SyoritsuIntentConstants.WIN_COUNT, resultArray[2]);
            intent.putExtra(SyoritsuIntentConstants.DRAW_COUNT, resultArray[0]);
            intent.putExtra(SyoritsuIntentConstants.LOSE_COUNT, resultArray[1]);

            startActivity(intent);
        });
    }

    /**
     * 結果メッセージを取得する
     * @param userChoice ユーザーの選択
     * @param appChoice アプリの選択
     * @return 結果メッセージ
     */
    private String getResultMessage(JankenHandItemEnum userChoice, JankenHandItemEnum appChoice) {
        int result = (userChoice.getNumber() - appChoice.getNumber() + 3) % 3;
        String resultMessage = "あなたが" + userChoice.getName() + "，アプリが" + appChoice.getName() + "で，";
        switch (result) {
            case 0:
                resultMessage += "引き分け";
                break;
            case 1:
                resultMessage += "あなたの負け";
                break;
            case 2:
                resultMessage += "あなたの勝ち";
                break;
        }
        return resultMessage;
    }

    /**
     * 結果ダイアログを表示する
     * @param resultMessage 結果メッセージ
     */
    private void showResultDialog(String resultMessage) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("結果")
                .setMessage(resultMessage)
                .setPositiveButton("了解", (dialog, which) -> dialog.dismiss())
                .show();
    }

    /**
     * 結果をSharedPreferencesにpushする
     * @param userChoice ユーザーの選択
     * @param appChoice アプリの選択
     */
    private void pushResultToSharedPreferences(int userChoice, int appChoice) {
        SharedPreferences pref = getSharedPreferences("result", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // 勝敗判定
        int result = (userChoice - appChoice + 3) % 3;
        // ,区切りで保存
        editor.putString("result", pref.getString("result", "") + result + ",");
        editor.apply();
    }

    /**
     * SharedPreferencesから結果を取得する
     * @return 結果[引き分け, 負け, 勝ち]
     */
    private int[] getResultFromSharedPreferences() {
        SharedPreferences pref = getSharedPreferences("result", MODE_PRIVATE);
        String[] results = pref.getString("result", "").split(",");
        Log.d("prefString", pref.getString("result", ""));
        int[] resultArray = new int[3];
        for (String result : results) {
            if (result.isEmpty()) {
                continue;
            }
            resultArray[Integer.parseInt(result)]++;
        }
        Log.d("result", Arrays.toString(resultArray));
        return resultArray;
    }
}