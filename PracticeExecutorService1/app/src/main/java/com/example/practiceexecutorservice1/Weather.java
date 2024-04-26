package com.example.practiceexecutorservice1;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/// 天気予報を取得するクラス
public class Weather {
    /// 表示をするActivityをここに格納し，これ経由でActivityを参照する
    private Activity activity;
    /// そのためにコンストラクタ（new Weather()により自動実行されるメソッド）で
    /// その格納する処理を行う
    public Weather(Activity activity) {
        /// クラス名と同じメソッド名のため，コンストラクタ
        this.activity = activity;
    }
    /// ExecutorServiceの定義
    /// ExecutorServiceにより，非同期処理を容易に管理できる
    /// newSingleThreadExecutor：単一のワーカースレッドを作成
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    /// ■①実行
    public void execute(String cityID) {
        /// 非同期処理の前処理があれば、ここに記入する
        preExecute();       /// ②前処理へ
        /// ■③非同期処理開始
        executorService.submit(new GetDataThread(cityID));  /// ④ネットワークからデータを取得を開始する
        /// executorServiceをシャットダウン(shutdown)する
        /// これ以降にタスクを新規登録（submit）しようとすると
        /// RejectedExecutionExceptionが発生する
        executorService.shutdown();
    }
    /// ■②前処理
    /// 非同期処理の準備
    private void preExecute() {
        // テキストビューの内容をクリアする
        TextView titleTextView = this.activity.findViewById(R.id.titleTextView);

        titleTextView.setText("");
    }
    /// ■⑥後処理
    /// 後処理で取得したJSONデータを表示する
    private void postExecute(String data) {
        /// https://weather.tsukumijima.net/api/forecast?city=230010
        /// を Chromeの拡張機能「JSONView」を用いて見て参考にする
        /// または下記のWebサイトでWeb APIの仕様から参考にする
        /// https://weather.tsukumijima.net/
        JSONObject parsedData = null;

        try {
            parsedData = new JSONObject(data);
        } catch (JSONException e) {
            Log.e("AST", "入出力処理の失敗", e);    /// JSON関連の処理失敗
        }
        /// Activityに加工した文字列sを表示する
        TextView titleTextView = this.activity.findViewById(R.id.titleTextView);
        TextView publishingOfficeTextView = this.activity.findViewById(R.id.publishingOfficeTextView);
        TextView publicTimeTextView = this.activity.findViewById(R.id.publicTimeTextView);
        TextView todayTelopTextView = this.activity.findViewById(R.id.todayTelopTextView);
        TextView headlineTextView = this.activity.findViewById(R.id.headlineTextView);
        TextView featureWeatherTextView = this.activity.findViewById(R.id.featureWeatherTextView);

        WebView iconWebView = this.activity.findViewById(R.id.webView);

        try {
            assert parsedData != null; // TODO: ハンドリング
            titleTextView.setText(parsedData.getString("title"));
            publishingOfficeTextView.setText(parsedData.getString("publishingOffice"));
            publicTimeTextView.setText(parsedData.getString("publicTimeFormatted"));
            todayTelopTextView.setText(parsedData.getJSONArray("forecasts").getJSONObject(0).getString("telop"));
            headlineTextView.setText(parsedData.getJSONObject("description").getString("headlineText").isEmpty() ?
                    parsedData.getJSONObject("description").getString("text") : parsedData.getJSONObject("description").getString("headlineText"));

            // 明日以降の天気を組み立て
            StringBuilder featureWeather = new StringBuilder();
            for (int i = 1; i < parsedData.getJSONArray("forecasts").length(); i++) {
                featureWeather.append(parsedData.getJSONArray("forecasts").getJSONObject(i).getString("dateLabel")).append("：")
                        .append(parsedData.getJSONArray("forecasts").getJSONObject(i).getString("telop")).append("(")
                        .append(parsedData.getJSONArray("forecasts").getJSONObject(i).getJSONObject("temperature")
                                .getJSONObject("min").getString("celsius")).append("℃/")
                        .append(parsedData.getJSONArray("forecasts").getJSONObject(i).getJSONObject("temperature")
                                .getJSONObject("max").getString("celsius")).append("℃)").append("\n");
            }

            featureWeatherTextView.setText(featureWeather.toString());
            iconWebView.loadUrl(parsedData.getJSONArray("forecasts").getJSONObject(0).getJSONObject("image").getString("url"));
            iconWebView.setBackgroundColor(0x00000000);
            // Change WebView Size
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /// 【プラスアルファ】
    }

    /// ネットワークから天気予報を取得するクラス
    private class GetDataThread implements Runnable {
        String cityId = "";
        /// Web APIのURLを格納する変数urlStr
        String urlStr = "";
        /// con：Http接続用オブジェクト
        HttpURLConnection con = null;
        /// inputStream：ネットワークからの入力データ
        InputStream inputStream = null;
        /// result：Webサーバから返されたレスポンス内の天気情報（JSON形式）
        String result = "";
        /// コンストラクタ
        /// 引数でcityIdを受け取り、メンバに格納する
        /// WebAPIのURLをここでセットする
        GetDataThread(String cityId) {
            this.cityId = cityId;
            urlStr = "https://weather.tsukumijima.net/api/forecast?city=" + cityId;
        }
        /// ■④ネットワークからデータを取得
        @Override
        public void run() {
            try {
                /// Http接続を行う手順
                /// AndroidManifest.xmlでパーミッションの設定が必要
                /// <uses-permission android:name="android.permission.INTERNET" />
                /// 1:String型の変数urlStrの文字列をURL型のURLオブジェクトへと変換する
                URL url = new URL(urlStr);
                /// 2:Http接続するためにURLオブジェクトからHttpURLConnectionオブジェクトを取得する
                con = (HttpURLConnection) url.openConnection();
                /// 3:接続するURLを要求するため、Http接続メソッドを呼び出す
                con.setRequestMethod("GET");
                /// 4:接続する
                con.connect();
                /// 5:レスポンスのデータを取得
                inputStream = con.getInputStream();
                /// 6:レスポンスのデータを文字列に変換
                /// レスポンスのデータ→InputStreamReader→BufferedReaderへと変換する
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                /// 7:文字列をString型へ1行ずつ変換する＝JSON形式の文字列データが格納される
                String a;
                while ((a = reader.readLine()) != null) {
                    result += a;
                }
            } catch (MalformedURLException e) {
                Log.e("AST", "URLの表記誤り", e);    /// URLの表記誤りによる例外時の処理
            } catch (IOException e) {
                Log.e("AST", "入出力処理の失敗", e);    /// 入出力処理の失敗による例外時の処理
            } finally {
                /// HttpURLConnection接続の切断を行う https://itsakura.com/java-httpurlconnection
                if (con != null) {
                    con.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            /// Handler：メッセージを指定されたスレッドに届ける役割を持つ郵便局員
            ///         「handler.post()」
            /// Looper：メッセージが届いた順に処理を行うクラス
            ///        「Looper.getMainLooper()」によって，メインスレッドを指定することができる
            /// メインスレッドにメッセージを送るためにHandlerオブジェクトを作成
            Handler handler = new Handler(Looper.getMainLooper());
            /// ■⑤メインスレッドにメッセージを送信
            /// メインスレッドにpostExecuteメソッドを実行するようにメッセージを送信する
            handler.post(new Runnable(){
                @Override
                public void run(){
                    postExecute(result);	/// ⑥後処理へ
                }
            });
        }
    }
}