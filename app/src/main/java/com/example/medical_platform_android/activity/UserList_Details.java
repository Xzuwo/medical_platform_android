package com.example.medical_platform_android.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.HealthStatus;
import com.example.medical_platform_android.entity.Health_Status_Response;
import com.example.medical_platform_android.entity.If_ExitResponse;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.ui.Fragment.UsersListFragment;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList_Details extends AppCompatActivity {
    private Integer CurrentUserId;
    private Users users;
    private ImageView headImage,back;
    private TextView detailUsername,detailName,detailGender,detailBirthdate,detailHistory,Health_Status_detail;
    private TextView Health_Status_UserId,Health_Status_HeartRate,Health_Status_bloodOxygen,Health_Status_SleepStatus,Health_Status_indicator;
    private View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_datails);
        initView();
        setListener();
    }

    private void setListener() {
        detailUsername.setText(users.getUsername());
        detailGender.setText(users.getGender());
        detailName.setText(users.getName());
        Calendar cal = Calendar.getInstance();
        cal.setTime(users.getBirthdate());
        if(cal.get(Calendar.YEAR) >= 2000) {
            detailBirthdate.setText(" 20" + (users.getBirthdate().getYear()-100) + "年" + (users.getBirthdate().getMonth()+1) + "月"  +  users.getBirthdate().getDate() + "日");
        }else{
            detailBirthdate.setText("19" + users.getBirthdate().getYear() + "年" + (users.getBirthdate().getMonth()+1)  + "月"  +  users.getBirthdate().getDate() + "日");
        }

        detailHistory.setText(users.getMedicationHistory());
        Glide.with(UserList_Details.this).load(users.getHeadImage()).into(headImage);

        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> data = new ArrayList<>();
                data.add("图片1");
                data.add("图片2");
                data.add("图片3");
                data.add("图片4");
                data.add("图片5");
                data.add("图片6");
                data.add("图片7");
                data.add("图片8");
                data.add("图片x");
                data.add("图片y");
                data.add("图片z");
// 创建AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(UserList_Details.this);

// 创建下拉框控件
                Spinner spinner = new Spinner(UserList_Details.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(UserList_Details.this, android.R.layout.simple_spinner_item, data);
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
                        String selectedValue = data.get(position);
                        Glide.with(UserList_Details.this).load("http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg").into(headImage);
                        Map<String,Object> params = new HashMap<>();
                        String url = UrlConstants.Change_Password;
                        params.put("id",users.getId());
                        params.put("headImage","http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg");
                        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                            @Override
                            public void response(String res) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                        if(registerResponse != null && registerResponse.getCode() == 200) {
                                            Toast.makeText(UserList_Details.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(UserList_Details.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void failure(Exception e) {

                            }
                        });
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new UsersListFragment());
                findViewById(R.id.detail_head).setVisibility(View.GONE);
            }
        });

        Health_Status_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserList_Details.this);
                rootView = LayoutInflater.from(UserList_Details.this).inflate(R.layout.user_health_status,null);
                builder.setView(rootView);
                Health_Status_UserId = rootView.findViewById(R.id.Health_Status_UserId);
                Health_Status_HeartRate = rootView.findViewById(R.id.Health_Status_HeartRate);
                Health_Status_bloodOxygen = rootView.findViewById(R.id.Health_Status_Blood_Oxygen);
                Health_Status_SleepStatus = rootView.findViewById(R.id.Health_Status_SleepStatus);
                Health_Status_indicator = rootView.findViewById(R.id.Health_Status_indicator);
                String url = UrlConstants.users_IfExit;
                Map<String,Object> params = new HashMap<>();
                params.put("username",users.getUsername());
                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                If_ExitResponse if_exitResponse = new Gson().fromJson(res,If_ExitResponse.class);
                                if(if_exitResponse != null & if_exitResponse.getCode() == 200) {
                                    Users users1 = if_exitResponse.getUser();
                                    CurrentUserId = users1.getId();
                                    GetHealthStatus();
                                }
                            }
                        });
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });

                builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void changeFragment(Fragment current) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.detail, current);
        transaction.commit();
    }

    private void GetHealthStatus() {
        String url = UrlConstants.Get_User_HealthStatus;
        Map<String,Object> params = new HashMap<>();
        params.put("userId",CurrentUserId);
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Health_Status_Response health_status_response = new Gson().fromJson(res,Health_Status_Response.class);
                        if(health_status_response != null && health_status_response.getCode() == 200) {
                            HealthStatus healthStatus = health_status_response.getHealthStatus();
                            Health_Status_UserId.setText(healthStatus.getUserId() + "");
                            Health_Status_HeartRate.setText(healthStatus.getHeartRate()  + "");
                            Health_Status_bloodOxygen.setText(healthStatus.getBloodOxygen() + "");
                            Health_Status_SleepStatus.setText(healthStatus.getSleepStatus());
                            Health_Status_indicator.setText(healthStatus.getAbnormalIndicator());
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
        headImage = this.findViewById(R.id.detail_img_item_images);
        detailUsername = this.findViewById(R.id.detail_username);
        detailName = this.findViewById(R.id.detail_name);
        detailBirthdate = this.findViewById(R.id.detail_birthdate);
        detailGender = this.findViewById(R.id.detail_gender);
        detailHistory = this.findViewById(R.id.detail_medication_history);
        back = this.findViewById(R.id.Detail_back);
        Health_Status_detail = this.findViewById(R.id.Personal_Health_detail);
        Intent intent = getIntent();
        users = (Users)intent.getSerializableExtra("users");
        System.out.println(users);
    }
}