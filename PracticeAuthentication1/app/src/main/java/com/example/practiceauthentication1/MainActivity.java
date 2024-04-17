package com.example.practiceauthentication1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    SignInButton signInButton;
    Button signOutButton;
    TextView statusTextView;
    GoogleApiClient mGoogleApiClient;
    /// Logcatで検索機能により，このアプリで出力したものだと判別するためのキーワード。
    private static final String TAG = "SignInActivity";
    /// サインイン画面へどのActivityからやって来たかを識別するための番号。番号は任意でよい。
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        statusTextView = (TextView) findViewById(R.id.status_textview);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this); /// 結び付け。押されるとonClick()メソッドを実行。

        // **************↓課題2****************
        // ここにログアウトボタンのOnClickListenerを実装する


        // **************↑課題2****************

    }

    @Override
    public void onClick(View v) {

        // sign_in_buttonが押されたら，signIn()メソッドを実行する。
        signIn();   // ※課題2では、この行をコメントアウトする※

        // **************↓課題2****************
        // 押されたボタンに応じて分岐する処理を実装する



        // **************↑課題2****************

    }

    private void signIn() {
        /// GoogleSignInApi（Firebase Authentication）のサインイン画面を開くための準備。
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        /// サインイン画面へどのActivityからやって来たかを識別するための番号を持って開く。
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /// startActivityForResult()により開いた画面を閉じた後に自動的に実行されるメソッド。
    /// 画面で利用者が行った状況に応じた後処理を行う。
    /// 手順　startActivityForResult()→サインイン画面→onActivityResult()
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /// 識別番号により，このActivityの上記のコードがスタートであり，そこからサインイン画面を開き，
        // さらにこのActivityのこのメソッドに戻ってきたことが識別できる。
        if (requestCode == RC_SIGN_IN) {
            /// 認証結果をresultに格納する。
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    /// 認証結果を利用した行う処理。
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSigninResult:" + result.isSuccess());
        if (result.isSuccess()) {  /// 認証が成功したら
            GoogleSignInAccount acct = result.getSignInAccount();
            /// 「Hello, 」と利用者の表示名を画面表示する。
            statusTextView.setText("Hello, " + acct.getDisplayName());
        } else {
            // 認証が失敗した場合
            // 今回は何もしない
        }
    }

    /// GoogleSignInApi（Firebase Authentication）への接続に失敗した場合に実行されるメソッド。
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFaild:" + connectionResult);
    }

    // **************↓課題2****************
    // signOutメソッドを実装する
    // 下記のコメントアウトを解除する

    /*
    private void signOut() {
        /// サインアウト（ログアウト）する。その後，onResultメソッドを実行する。
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                statusTextView.setText("Signed out"); /// 文字を表示する。
            }
        });
    }
    */

    // **************↑課題2****************

}