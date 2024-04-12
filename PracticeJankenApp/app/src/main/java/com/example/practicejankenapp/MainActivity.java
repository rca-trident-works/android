package com.example.practicejankenapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
            showResultDialog(getResultMessage(userChoice, appChoice));
        };

        findViewById(R.id.imgbtnPa).setOnClickListener(listener);
        findViewById(R.id.imgbtnGu).setOnClickListener(listener);
        findViewById(R.id.imgbtnChoki).setOnClickListener(listener);
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
}