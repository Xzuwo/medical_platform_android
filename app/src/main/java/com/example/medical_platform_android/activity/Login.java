package com.example.medical_platform_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.LoginResponse;

import com.example.medical_platform_android.entity.ShowHeadImageResponse;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Button login;
    private ImageView headImage;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 移除文本变化监听器
        LUsername.removeTextChangedListener(mTextWatcher);
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
                            String name = loginResponse.getName();
                            String birthdate = loginResponse.getBirthdate();
                            String gender = loginResponse.getGender();
                            String headImage = loginResponse.getHeadImage();
                            String medication_history = loginResponse.getMedication_history();
                            SPUtil.saveString(Login.this,"token",token);
                            SPUtil.saveString(Login.this,"userId",id);
                            SPUtil.saveString(Login.this,"username",username);
                            SPUtil.saveString(Login.this,"name",name);
                            SPUtil.saveString(Login.this,"birthdate",birthdate);
                            SPUtil.saveString(Login.this,"gender",gender);
                            SPUtil.saveString(Login.this,"headImage",headImage);
                            SPUtil.saveString(Login.this,"medication_history",medication_history);
                            System.out.println(medication_history);
                            Intent intent = new Intent(Login.this, NavigationActivity.class);
                            startActivity(intent);
                            finish();
                            LUsername.removeTextChangedListener(mTextWatcher);
                        }else{
                            Toast.makeText(Login.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this, "连接错误", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() > 0) {
                String url = UrlConstants.users_IfExit;
                String username = LUsername.getText().toString();
                Map<String,Object> params = new HashMap<>();
                params.put("username",username);
                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ShowHeadImageResponse showHeadImageResponse = new Gson().fromJson(res,ShowHeadImageResponse.class);
                                System.out.println(showHeadImageResponse.getCode());
                                if(showHeadImageResponse != null && showHeadImageResponse.getCode() == 200) {
                                    int resourceId = getResources().getIdentifier(showHeadImageResponse.getUser().getHeadImage() + "", "drawable", getPackageName());
                                    headImage.setImageResource(resourceId);
                                }else{
                                    headImage.setImageResource(R.drawable.main_user);
                                }
                            }
                        });
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void initView() {
        login = this.findViewById(R.id.Login);
        LUsername = this.findViewById(R.id.Login_Username);
        LPassword = this.findViewById(R.id.Login_Password);
        toRegister = this.findViewById(R.id.Login_ToRegister);
        LUsername.addTextChangedListener(mTextWatcher);
        headImage = this.findViewById(R.id.Login_img_item_images);
    }
}