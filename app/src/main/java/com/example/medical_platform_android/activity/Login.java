package com.example.medical_platform_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.databinding.NavHeaderNavigationBinding;
import com.example.medical_platform_android.entity.LoginResponse;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button login;
    private TextView toRegister;
    private TextView LUsername, LPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        SetListener();
        xzwtest();

    }

    private void xzwtest() {
        Button loginResponse =this.findViewById(R.id.xzw_login_test);
        loginResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,NavigationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void SetListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLogin();


            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void CheckLogin() {
        String url = UrlConstants.login_url;
        String username = LUsername.getText().toString();
        String password = LPassword.getText().toString();
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginResponse loginResponse = new Gson().fromJson(res,LoginResponse.class);
                        if(loginResponse != null && loginResponse.getCode() == 200){
                            String token = loginResponse.getToken();
                            String id = loginResponse.getId().toString();
                            String username = loginResponse.getUsername();
                            SPUtil.saveString(Login.this,"token",token);
                            SPUtil.saveString(Login.this,"userId",id);
                            SPUtil.saveString(Login.this,"username",username);
                            Intent intent = new Intent(Login.this, NavigationActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Login.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    private void initView() {
        login = this.findViewById(R.id.Login);
        LUsername = this.findViewById(R.id.Login_Username);
        LPassword = this.findViewById(R.id.Login_Password);
        toRegister = this.findViewById(R.id.Login_ToRegister);
    }
}