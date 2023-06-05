package com.example.medical_platform_android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.LoginResponse;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class Register extends AppCompatActivity {
    private Button register;
    private TextView username,name,password;
    private DatePicker birthdate;
    private RadioGroup radioGroup;
    private RadioButton RadioButton ;
    private TextView toLogin;

    private ImageView headImage;
    private String selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        SetListener();
    }

    private void SetListener() {
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CheckRegister();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        headImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<String> data = new ArrayList<>();
                data.add("图片a");
                data.add("图片b");
                data.add("图片c");
                data.add("图片d");
                data.add("图片e");
                data.add("图片f");
// 创建AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);

// 创建下拉框控件
                Spinner spinner = new Spinner(Register.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Register.this, android.R.layout.simple_spinner_item, data);
                spinner.setAdapter(adapter);

// 设置弹出框的标题，消息和下拉框控件
                builder.setTitle("头像");
                builder.setMessage("选择一个喜欢的头像吧");
                builder.setView(spinner);

// 设置弹出框的确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 获取选中的下拉框项
                        int position = spinner.getSelectedItemPosition();
                        selectedValue = data.get(position);
                        int resourceId = getResources().getIdentifier(selectedValue.charAt(2) + "", "drawable", getPackageName());
                        headImage.setImageResource(resourceId);
                        // 执行操作
                        // ...
                    }
                });
// 设置弹出框的取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 执行操作
                        // ...
                    }
                });

// 创建AlertDialog对象并显示
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    private void CheckRegister() throws ParseException {
        String url = UrlConstants.register_url;
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String name = this.name.getText().toString();
        DatePicker birthdate = this.birthdate;
        String Month = judgeNumber(birthdate.getMonth());
        String date = judgeNumber(birthdate.getDayOfMonth());
        String time = birthdate.getYear()+"-"+Month+"-"+date;
        RadioButton  = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        String gender = RadioButton.getText().toString();
        if(username.equals("") || password.equals("") || name.equals("") || gender.equals("")) {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
        }else {
            Map<String,Object> params = new HashMap<>();
            params.put("username",username);
            params.put("password",password);
            params.put("name",name);
            params.put("birthdate",time);
            params.put("gender",gender);
            params.put("headImage",selectedValue.charAt(2));
            OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                @Override
                public void response(String res) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RegisterResponse registerResponse = new Gson().fromJson(res, RegisterResponse.class);
                            if(registerResponse != null && registerResponse.getCode() == 200) {
                                Toast.makeText(Register.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Register.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initView() {
        toLogin = this.findViewById(R.id.Register_Return_ToLogin);
        register = this.findViewById(R.id.Register);
        username = this.findViewById(R.id.Register_Username);
        password = this.findViewById(R.id.Register_Password);
        name = this.findViewById(R.id.Register_Username);
        birthdate = this.findViewById(R.id.Register_birthdate);
        radioGroup = this.findViewById(R.id.Register_gender_selected);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        Log.d("TAG", "initView: ______"+radioButtonID);
        RadioButton  = radioGroup.findViewById(radioButtonID);
        headImage = this.findViewById(R.id.Register_img_item_images);
    }

    private String judgeNumber(int x) {
        if(x < 10) {
            return "0" + x;
        }else{
            return x + "";
        }
    }
}