package com.example.practicejankenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            int[] resultArray = getResultFromSharedPreferences();

            Intent intent = new Intent(this, SyoritsuActivity.class);

            intent.putExtra(SyoritsuIntentConstants.TOTAL_BATTLE_COUNT, resultArray.length);
            intent.putExtra(SyoritsuIntentConstants.DRAW_COUNT, countSpecificValue(resultArray, 0));
            intent.putExtra(SyoritsuIntentConstants.LOSE_COUNT, countSpecificValue(resultArray, 1));
            intent.putExtra(SyoritsuIntentConstants.WIN_COUNT, countSpecificValue(resultArray, 2));

            startActivity(intent);
        });
    }

    private int countSpecificValue(int[] array, int value) {
        return (int) Arrays.stream(array)
                .filter(i -> i == value)
                .count();
    }

    private String getResultMessage(JankenHandItemEnum userChoice, JankenHandItemEnum appChoice) {
        String resultMessage = "あなたが" + userChoice.getName() + "，アプリが" + appChoice.getName() + "で，";
        resultMessage += userChoice == appChoice ? "あいこ" : "あなたの" + (userChoice.getNumber() > appChoice.getNumber() ? "勝ち" : "負け");
        return resultMessage;
    }

    private void showResultDialog(String resultMessage) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("結果")
                .setMessage(resultMessage)
                .setPositiveButton("了解", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void pushResultToSharedPreferences(int userChoice, int appChoice) {
        SharedPreferences pref = getSharedPreferences("result", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // 勝敗判定
        int result = (userChoice - appChoice + 3) % 3;
        // ,区切りで保存
        editor.putString("result", pref.getString("result", "") + result + ",");
        editor.apply();
    }

    private int[] getResultFromSharedPreferences() {
        SharedPreferences pref = getSharedPreferences("result", MODE_PRIVATE);
        String[] results = pref.getString("result", "").split(",");
        int[] resultArray = new int[3];
        for (String result : results) {
            if (result.isEmpty()) {
                continue;
            }
            resultArray[Integer.parseInt(result)]++;
        }
        return resultArray;
    }
}