package com.example.medical_platform_android.ui.side_menu;

import android.app.NativeActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.utils.SPUtil;

public class SideLogOutFragment extends Fragment {

    private View rootView;
    private Context context;

    public SideLogOutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_side_log_out, container, false);
        
        initView();
        setListeners();
        return rootView;
    }

    private void setListeners() {
        context = getActivity();
        Intent intent = new Intent(context, Login.class);
        SPUtil.removeString(context,"token");
        SPUtil.removeString(context,"userId");
        SPUtil.removeString(context,"username");
        SPUtil.removeString(context,"name");
        SPUtil.removeString(context,"birthdate");
        SPUtil.removeString(context,"gender");
        SPUtil.removeString(context,"headImage");
        SPUtil.removeString(context,"medication_history");
        context.startActivity(intent);

    }

    private void initView() {
    }
}