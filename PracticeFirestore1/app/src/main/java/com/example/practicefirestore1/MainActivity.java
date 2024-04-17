package com.example.practicefirestore1;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //③initialize cloud firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 画面項目の取得
        EditText fast = findViewById(R.id.editFirst);// 苗字
        EditText last = findViewById(R.id.editLast); // 名前
        EditText born = findViewById(R.id.editBorn); // 生まれ年
        TextView show = findViewById(R.id.textShow); // 下部表示エリア

        // 「保存」ボタンの処理。Firestoreにデータを保存する
        findViewById(R.id.btnSave).setOnClickListener(v -> {
            // ④Add Data - Create a new user with a first and last name
//            Map<String, Object> user = new HashMap<>();
//            user.put("first", fast.getText().toString());
//            user.put("last", last.getText().toString());
//            user.put("born", born.getText().toString());

            User user = new User();
            user.first = fast.getText().toString();
            user.last = last.getText().toString();
            user.born = Integer.parseInt(born.getText().toString());

            // Add a new document with a generated ID
            // Firestoreのコレクション「users」に新しいドキュメントを追加する
            // 非同期で更新処理が動作する。結果を受け取るために処理完了時のリスナーをセットする
            db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("@FB1", "DocumentSnapshot added with ID: " + documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("@FB1", "Error adding document", e);
                }
            });
        });

        // 「表示」ボタンが押されたら実行される。Firestoreからデータを取得
        findViewById(R.id.btnShow).setOnClickListener(v -> {
            // ⑤Read Data
            // Firestoreのコレクション「users」のドキュメント一覧を取得する
            // 非同期で取得処理が動作する。結果を受け取るために処理完了時のリスナーをセットする

            db.collection("users").get().addOnCompleteListener(task -> {
                String data = "";
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        User result = document.toObject(User.class);
                        Log.d("@FB1", document.getId() + " => " + document.getData());
//                        data += document.getData().get("first") + " "
//                                + document.getData().get("last") + " "
//                                + document.getData().get("born") + "\n";
                        data += result.first + " " + result.last + " " + result.born + "\n";
                    }
                    show.setText(data);
                } else {
                    data = "Error getting documents: " + task.getException();
                }
                show.setText(data);
            });
        });
    }

    class User {
        public String first;
        public String last;
        public int born;
    }
}