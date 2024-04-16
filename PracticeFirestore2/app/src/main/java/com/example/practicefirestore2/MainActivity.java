package com.example.practicefirestore2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textShow = findViewById(R.id.textShow);

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            textShow.setText("");
        });

        db.collection("users").addSnapshotListener((snapshot, exception) -> {
            if (exception != null) {
                Log.w("TAG", "Listen failed.", exception);
                return;
            }
            if (snapshot != null && !snapshot.isEmpty()) {
                for (DocumentChange document : snapshot.getDocumentChanges()) {
                    switch (document.getType()) {
                        case ADDED:
                        case MODIFIED:
                        case REMOVED:
                            textShow.append(document.getDocument().getData().toString() + "\n");
                        default:
                            break;
                    }
                }
            } else {
                Log.d("TAG", "Current data: null");
            }
        });
    }
}