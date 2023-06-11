package com.example.medical_platform_android.ui.side_menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.activity.Register;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.ui.slideshow.SlideshowFragment;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SidePersonalFragment extends Fragment {
    private static final String TAG = "SidePersonalFragment";
    private TextView username,name,birthdate,gender,job;
    private ImageView headImage,back;
    private TextView medication_history,clickChangeMedication;
    private View rootView;
    private String selectedValue;


    public SidePersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_side_personal, container, false);
        initView();
        SetListener();
        return rootView;
    }

    private void SetListener() {
        clickChangeMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
// 设置对话框标题
                builder.setTitle("病历");
// 设置对话框消息
                builder.setMessage("请输入要修改的病历");
                final EditText editText = new EditText(getContext());
                builder.setView(editText);
// 设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 取消按钮的点击事件
                    }
                });
// 设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 确定按钮的点击事件
                        if(editText.getText().toString().trim().length() != 0) {
                            String url = UrlConstants.Change_Password;
                            Map<String,Object> params = new HashMap<>();
                            params.put("id",SPUtil.getString(getContext(),"userId") );
                            params.put("medicationHistory",editText.getText().toString());
                            OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                                @Override
                                public void response(String res) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                            if(registerResponse != null && registerResponse.getCode() == 200) {
                                                medication_history.setText(editText.getText().toString());
                                                Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getActivity(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
                });
// 创建对话框并显示
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

// 创建下拉框控件
                Spinner spinner = new Spinner(getContext());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, data);
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
                        Glide.with(getContext()).load("http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg").into(headImage);
//                        int resourceId = getResources().getIdentifier(selectedValue.charAt(2) + "", "drawable", getPackageName());
//                        headImage.setImageResource(resourceId);
                        // 执行操作
                        //
                        String url = UrlConstants.Change_Password;
                        Map<String,Object> params = new HashMap<>();
                        params.put("id",SPUtil.getString(getContext(),"userId") );
                        params.put("headImage","http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg");
                        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                            @Override
                            public void response(String res) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                        if(registerResponse != null && registerResponse.getCode() == 200) {
                                            SPUtil.saveString(getContext(),"headImage","http://192.168.246.1:8081/upload/images/" + selectedValue.charAt(2) + ".jpg");
                                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getActivity(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void initView() {
        username = rootView.findViewById(R.id.personal_username);
        name = rootView.findViewById(R.id.personal_name);
        gender = rootView.findViewById(R.id.personal_gender);
        birthdate = rootView.findViewById(R.id.personal_birthdate);
        headImage = rootView.findViewById(R.id.personal_img_item_images);
        job = rootView.findViewById(R.id.personal_job);
        clickChangeMedication = rootView.findViewById(R.id.click_Change_medication);
        medication_history = rootView.findViewById(R.id.personal_medication_history);
        back = rootView.findViewById(R.id.Personal_back);
        username.setText(SPUtil.getString(getContext(),"username"));
        name.setText(SPUtil.getString(getContext(),"name"));
        gender.setText(SPUtil.getString(getContext(),"gender"));
        birthdate.setText(SPUtil.getString(getContext(),"birthdate"));
        medication_history.setText(SPUtil.getString(getContext(),"medication_history"));
        job.setText(SPUtil.getString(getContext(),"CurrentRole"));
        Context context = getActivity().getApplicationContext();
        Glide.with(getContext()).load(SPUtil.getString(getContext(),"headImage")).into(headImage);
//        int resourceId = getResources().getIdentifier(SPUtil.getString(getContext(),"headImage") + "", "drawable", context.getPackageName());
//        headImage.setImageResource(resourceId);
    }

    public String getPackageName() {
        throw new RuntimeException("Stub!");
    }
}