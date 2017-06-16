package tw.edu.fcu.postoffice.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;

import tw.edu.fcu.postoffice.Encryption.MD5;
import tw.edu.fcu.postoffice.R;
import tw.edu.fcu.postoffice.Server.HttpCall;
import tw.edu.fcu.postoffice.Server.HttpRequest;
import tw.edu.fcu.postoffice.ViewController.KeyboardLayout;

public class LoginActivity extends AppCompatActivity {
    EditText edtAccount;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegister;
    CheckBox chkRecordPassword;
    private LinearLayout linearLayout;
    HashMap<String,String> params = new HashMap<String,String>();
    MD5 md5 = new MD5();
    ConnectivityManager mConnectivityManager;
    NetworkInfo mNetworkInfo;
    HttpCall httpCallPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        initView();
        initNetwork();
//        if(mNetworkInfo == null){
//            Toast.makeText(LoginActivity.this,"沒有網絡",Toast.LENGTH_LONG).show();
//            return;
//        }
//        else {
//            initNetwork();
//        }
    }

    public void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.root);
        edtAccount = (EditText) findViewById(R.id.edt_account);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        chkRecordPassword = (CheckBox) findViewById(R.id.chk_record_password);

        KeyboardLayout.controlKeyboardLayout(linearLayout, btnRegister); //軟鍵盤彈出 調整位置
    }

    public void initNetwork() {
        httpCallPost = new HttpCall();
        httpCallPost.setMethodtype(HttpCall.POST);
    }

    public void loginButton(View view) {
        if (mNetworkInfo == null) {
            Toast.makeText(LoginActivity.this, "沒有網絡", Toast.LENGTH_LONG).show();
            return;
        }

        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();

        if (account.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(LoginActivity.this, "帳密不能為空", Toast.LENGTH_LONG).show();
            return;
        }
        params.put("Account", account);
        params.put("Password", md5.MD5SixteenBit(password));
        Log.v("Account", account);
        Log.v("Password", md5.MD5SixteenBit(edtPassword.getText().toString()));

        httpCallPost.setUrl("http://140.134.26.31/postoffice_pj/Login.php");
        httpCallPost.setParams(params);

        new HttpRequest() {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                if (response.contains("ok")) {
                    //要跟server 做判斷
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "帳號或密碼輸入有誤！！請重新輸入正確帳密", Toast.LENGTH_LONG).show();
                }
                params.clear();
            }
        }.execute(httpCallPost);
    }

    public void registerButton(View view) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void passwordRecovery(View view){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, PasswordRecoveryActivity.class);
        startActivity(intent);
    }
}
