package com.example.medical_platform_android.ui.side_menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.ui.Fragment.UsersListFragment;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SidePasswordChangeFragment extends Fragment {

    private TextView originalPassword,presentPassword;
    private Button changePasswordSure, changePasswordReturn;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_side_password_change, container, false);
        initView();
        SetListeners();
        return rootView;
    }

    private void SetListeners() {
        changePasswordSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(originalPassword.getText().toString().trim().length() != 0 && presentPassword.getText().toString().trim().length() != 0) {
                    String Password = originalPassword.getText().toString();
                    if (Password.equals(SPUtil.getString(getContext(),"password"))) {
                        String present = presentPassword.getText().toString();
                        //System.out.println(present);
                        String url = UrlConstants.Change_Password;
                        SPUtil.saveString(getContext(),"password",present);
                        Map<String,Object> params = new HashMap<>();
                        params.put("id",SPUtil.getString(getContext(),"userId"));
                        params.put("password",present);
                        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                            @Override
                            public void response(String res) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                        if(registerResponse != null && registerResponse.getCode() == 200) {
                                            Toast.makeText(getActivity(), "修改密码成功", Toast.LENGTH_SHORT).show();
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
                    }else{
                        Toast.makeText(getContext(), "原密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                }

            }
        });
        changePasswordReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }



    private void initView() {
        originalPassword = rootView.findViewById(R.id.Original_Password);
        presentPassword = rootView.findViewById(R.id.Present_Password);
        changePasswordSure = rootView.findViewById(R.id.change_password_sure);
        changePasswordReturn = rootView.findViewById(R.id.change_password_return);
    }
}