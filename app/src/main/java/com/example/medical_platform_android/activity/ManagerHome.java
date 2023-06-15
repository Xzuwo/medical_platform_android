package com.example.medical_platform_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.ui.Fragment.DrugsListFragment;
import com.example.medical_platform_android.ui.Fragment.UsersListFragment;

public class ManagerHome extends AppCompatActivity {

    private Button UsersListDetails,DrugsListDetails;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);
        initView();
        SetListeners();

    }

    private void SetListeners() {
        UsersListDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new UsersListFragment());
                findViewById(R.id.Users_List_details).setVisibility(View.GONE);
                findViewById(R.id.Drugs_List_Details).setVisibility(View.GONE);
                findViewById(R.id.Manager_title).setVisibility(View.GONE);
                findViewById(R.id.Manager_Back).setVisibility(View.GONE);
            }
        });

        DrugsListDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new DrugsListFragment());
                findViewById(R.id.Users_List_details).setVisibility(View.GONE);
                findViewById(R.id.Drugs_List_Details).setVisibility(View.GONE);
                findViewById(R.id.Manager_title).setVisibility(View.GONE);
                findViewById(R.id.Manager_Back).setVisibility(View.GONE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHome.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void changeFragment(Fragment current) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Manager_UserList, current);
        transaction.commit();
    }

    private void initView() {
        UsersListDetails = this.findViewById(R.id.Users_List_details);
        DrugsListDetails = this.findViewById(R.id.Drugs_List_Details);
        back = this.findViewById(R.id.Manager_Back);
    }
}