package com.example.medical_platform_android.ui.side_menu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.utils.SPUtil;


public class SidePersonalFragment extends Fragment {
    private TextView username,name,birthdate,gender;
    private ImageView headImage;
    private TextView medication_history;
    private View rootView;


    public SidePersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_side_personal, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        username = rootView.findViewById(R.id.personal_username);
        name = rootView.findViewById(R.id.personal_name);
        gender = rootView.findViewById(R.id.personal_gender);
        birthdate = rootView.findViewById(R.id.personal_birthdate);
        headImage = rootView.findViewById(R.id.personal_img_item_images);
        medication_history = rootView.findViewById(R.id.personal_medication_history);
        username.setText(SPUtil.getString(getContext(),"username"));
        name.setText(SPUtil.getString(getContext(),"name"));
        gender.setText(SPUtil.getString(getContext(),"gender"));
        birthdate.setText(SPUtil.getString(getContext(),"birthdate"));
        medication_history.setText(SPUtil.getString(getContext(),"medication_history"));
        Context context = getActivity().getApplicationContext();
        int resourceId = getResources().getIdentifier(SPUtil.getString(getContext(),"headImage") + "", "drawable", context.getPackageName());
        headImage.setImageResource(resourceId);
    }

    public String getPackageName() {
        throw new RuntimeException("Stub!");
    }
}