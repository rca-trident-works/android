package com.example.practicejankenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SyoritsuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syoritsu);

        Intent intent = getIntent();
        int totalBattleCount = intent.getIntExtra(SyoritsuIntentConstants.TOTAL_BATTLE_COUNT, 0);
        int winCount = intent.getIntExtra(SyoritsuIntentConstants.WIN_COUNT, 0);
        int drawCount = intent.getIntExtra(SyoritsuIntentConstants.DRAW_COUNT, 0);
        int loseCount = intent.getIntExtra(SyoritsuIntentConstants.LOSE_COUNT, 0);

        String message = getMessage(totalBattleCount, winCount, drawCount, loseCount);

        TextView textView = findViewById(R.id.resultTextView);
        textView.setText(message);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private String getMessage(int totalBattleCount, int winCount, int drawCount, int loseCount) {
        return "全" + totalBattleCount + "回戦, " +
                winCount + "勝, " +
                drawCount + "分, " +
                loseCount + "敗, " +
                // 小数点以下1桁で四捨五入
                "勝率:" + Math.round((double) winCount / totalBattleCount * 1000) / 10.0 + "%";
    }
}