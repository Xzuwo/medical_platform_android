package com.example.medical_platform_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.utils.SPUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical_platform_android.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity {
    private ImageView headImage;
    private TextView username,name,medication_history;
    private final  String TAG="Tag____:";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;

    private BottomNavigationView navView;
    private NavController navController;
    private Button Logout;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.bottom_home, R.id.bottom_drugs, R.id.bottom_order)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navView = findViewById(R.id.main_bottom);

        View fragment = this.findViewById(R.id.nav_host_fragment_content_navigation);


        //侧边滑块

//        binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);




//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        initView();
        setOnClickListener();
    }

    public void setOnClickListener(){
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                Log.d(TAG, "onDestinationChanged: --------修改页面");


//                changeFragment(new NavGraphEmptyFragment());
            }
        });

        navView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                NavController navController = Navigation.findNavController(NavigationActivity.this, R.id.nav_host_fragment_content_navigation);
                Log.d("TAG","点击bottomNavigationView"+item.getItemId());


                switch (item.getItemId()){
                    case R.id.bottom_home:
                        navController.navigate(R.id.bottom_home);
                        break;
                    case R.id.bottom_drugs:
                        navController.navigate(R.id.bottom_drugs);
                        break;
                    case R.id.bottom_order:
                        navController.navigate(R.id.bottom_order);

                }

            }
        });
    }

    private void initView(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        headImage = headerView.findViewById(R.id.imageView);
        username = headerView.findViewById(R.id.nav_header_username);
        name = headerView.findViewById(R.id.nav_header_name);
        Logout = headerView.findViewById(R.id.side_login_out);
        name.setText(SPUtil.getString(NavigationActivity.this,"name"));
        username.setText(SPUtil.getString(NavigationActivity.this,"username"));
        int resourceId = getResources().getIdentifier(SPUtil.getString(NavigationActivity.this,"headImage") + "", "drawable", getPackageName());
        headImage.setImageResource(resourceId);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void changeFragment(Fragment fragment){

// 获取NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);

// 跳转到指定的Fragment
        navController.navigate(R.id.side_login_out);

    }

}