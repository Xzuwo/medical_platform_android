package com.example.medical_platform_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.FindRoleNameResponse;
import com.example.medical_platform_android.entity.LoginResponse;

import com.example.medical_platform_android.entity.ShowHeadImageResponse;
import com.example.medical_platform_android.ui.Fragment.UsersListFragment;
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
                finish();
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
                finish();
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
                            String user_Password = loginResponse.getPassword();
                            String medication_history = loginResponse.getMedication_history();
                            SPUtil.saveString(Login.this,"token",token);
                            SPUtil.saveString(Login.this,"userId",id);
                            SPUtil.saveString(Login.this,"username",username);
                            SPUtil.saveString(Login.this,"password",user_Password);
                            SPUtil.saveString(Login.this,"name",name);
                            SPUtil.saveString(Login.this,"birthdate",birthdate);
                            SPUtil.saveString(Login.this,"gender",gender);
                            SPUtil.saveString(Login.this,"headImage",headImage);
                            SPUtil.saveString(Login.this,"medication_history",medication_history);
                            System.out.println(medication_history);

                            String url = UrlConstants.findRoleName;
                            Map<String ,Object> params1 = new HashMap<>();
                            params1.put("userId",id);
                            OkhttpUtil.postRequest(url, params1, new ResponseCallback() {
                                @Override
                                public void response(String res) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            FindRoleNameResponse findRoleNameResponse = new Gson().fromJson(res,FindRoleNameResponse.class);
                                            if(findRoleNameResponse != null) {
                                                SPUtil.saveString(Login.this,"CurrentRole",findRoleNameResponse.getRoleName());
                                                Intent intent;
                                                if(SPUtil.getString(Login.this,"CurrentRole").equals("管理员")) {
                                                    intent = new Intent(Login.this, ManagerHome.class);
                                                }else{
                                                    intent = new Intent(Login.this, NavigationActivity.class);
                                                }
                                                startActivity(intent);
                                                finish();
                                                LUsername.removeTextChangedListener(mTextWatcher);
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void failure(Exception e) {

                                }
                            });

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
                                //System.out.println(showHeadImageResponse.getCode());
                                if(showHeadImageResponse != null && showHeadImageResponse.getCode() == 200) {
                                    Glide.with(Login.this).load(showHeadImageResponse.getUser().getHeadImage()).into(headImage);
//                                    int resourceId = getResources().getIdentifier(showHeadImageResponse.getUser().getHeadImage() + "", "drawable", getPackageName());
//                                    headImage.setImageResource(resourceId);
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