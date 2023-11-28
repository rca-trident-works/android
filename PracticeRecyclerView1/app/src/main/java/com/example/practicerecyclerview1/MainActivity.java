package com.example.practicerecyclerview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        //RecyclerViewのサイズを固定
        recyclerView.setHasFixedSize(true);

        //RecyclerViewに区切り線を入れる
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        //レイアウトマネージャを設定
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //①リスト構造(String型の可変長の配列)を宣言
        ArrayList<String> arrayList = new ArrayList<>();

        //②リスト構造に値を追加
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        //③Adapterとリスト構造を結び付け
        //RecyclerAdapterクラスを呼び出す
        RecyclerAdapter adapter = new RecyclerAdapter(arrayList);

        //④RecyclerViewとAdapterの結び付け
        recyclerView.setAdapter(adapter);

        //追加ボタンのクリックイベントを登録
        findViewById(R.id.btnAdd).setOnClickListener(
                v -> {
                    //editAddを取り出してeditに代入
                    EditText edit = findViewById(R.id.editAdd);

                    //editの入力文字をgetTextメソッドで取り出して
                    //String型に変換してstrに代入
                    String str = edit.getText().toString();

                    //ArrayListの一番下に値を追加
                    arrayList.add(str);

                    //一番下に値が追加されたことをAdapterが画面に通知
                    adapter.notifyItemInserted(arrayList.size());
                }
        );
    }
}