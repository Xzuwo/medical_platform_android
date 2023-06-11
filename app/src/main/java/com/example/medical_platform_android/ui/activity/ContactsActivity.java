package com.example.medical_platform_android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.medical_platform_android.NavigationActivity;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.ui.Fragment.ContactsFragment;

public class ContactsActivity extends AppCompatActivity {

    private ContactsFragment contactsFragment;
    private ImageView ivReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initView();
        setListeners();
    }

    private void setListeners() {

        changeFragment(contactsFragment);
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void changeFragment(Fragment currentFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.ll_main_content,currentFragment);
        transaction.commit();
    }

    private void initView() {
        ivReturn = findViewById(R.id.return_icons);
        contactsFragment = new ContactsFragment();
    }
}