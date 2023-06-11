package com.example.medical_platform_android.ui.bottom_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.IpConfiguration;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
//import com.example.medical_platform_android.adapter.DectorsLsitAdapter;
import com.example.medical_platform_android.adapter.PostsListAdapter;

import com.example.medical_platform_android.entity.Doctors;
import com.example.medical_platform_android.entity.Posts;
import com.example.medical_platform_android.entity.PostsAndUser;
import com.example.medical_platform_android.entity.PostsAndUserResponse;
import com.example.medical_platform_android.entity.Tags;
import com.example.medical_platform_android.entity.TagsResponse;
import com.example.medical_platform_android.ui.activity.ChatActivity;
import com.example.medical_platform_android.utils.DividerItemDecoration;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    public String TAG="___Debug.d:";
    private  View rootView;
    private  int page=0;
    private SmartRefreshLayout fragment_refresh_load;

    private  List<PostsAndUser> postsList;

    private DrawerLayout home_drawer_layout;
    private RecyclerView fragment_home_content_list;
    private Button home_testa;
//    private  String [] list_community_classification=new String[]{"tab11","tab22","tab33","tab4","tab5"};


    private ImageView navigation_button;
    private TabLayout tabLayout;
    private List<PostsAndUser> postsAndUserList=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_home ,container, false);

        initView();
        SetTag();
        initAinm();
        //填充类别内容
        setAdapter(page);


        setOnClickListener();


        return rootView;
    }

    private void initAinm() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.my_anim);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layoutAnimationController.setDelay(0.2f);
        fragment_home_content_list.setLayoutAnimation(layoutAnimationController);
    }
    private void setAdapter(int tagId) {
        //获取数据
        HashMap<String,Object> t=new HashMap<>();
        t.put("userid", SPUtil.getString(getActivity(),"userid"));
        t.put("page",page);
        t.put("tagId",tagId);


        OkhttpUtil.postRequest(UrlConstants.xzw_url + "posts/getPostByUser", t, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG, "response: "+res);
                PostsAndUserResponse da=new Gson().fromJson(res, PostsAndUserResponse.class);
                if(da.getCode()==200){

                    //适配器
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);

                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            fragment_home_content_list.setLayoutManager(linearLayoutManager);
                            postsAndUserList.addAll(da.getDataobject());
                            PostsListAdapter postsListAdapter = new PostsListAdapter(postsAndUserList,getActivity());
                            fragment_home_content_list.setAdapter(postsListAdapter);
//                            fragment_home_content_list.setLayoutManager(linearLayoutManager);
//                            PostsListAdapter postsListAdapter = new PostsListAdapter(da.getDataobject(),getActivity());
//                            fragment_home_content_list.setAdapter(postsListAdapter);

//                          添加下划线

                            fragment_home_content_list.addItemDecoration(new DividerItemDecoration(Color.GRAY, 5));


                        }
                    });


                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "获取内容失败！请重试。", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }

            @Override
            public void failure(Exception e) {
                Log.d(TAG, "failure: 报错——————"+e);
            }
        });


    }

    public void setOnClickListener(){

        fragment_refresh_load.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG, "onRefresh: 下拉刷新——————");
                page=0;
                postsAndUserList=new ArrayList<>();
                setAdapter(page);
                fragment_refresh_load.finishRefresh();
            }
        });
        fragment_refresh_load.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG, "onLoadMore: 上拉刷新——————");
                page++;
                setAdapter(page);
                fragment_refresh_load.finishLoadMore();

            }
        });
        navigation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer_layout = getActivity().findViewById(R.id.drawer_layout);
                drawer_layout.setOnClickListener(this);
                drawer_layout.openDrawer(GravityCompat.START);//设置左边菜单栏显示出来
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 处理 Tab 选中事件
                // 可以通过 tab.getPosition() 方法获取选中的 Tab 的位置
                Log.d(TAG, "onTabSelected: "+"点击"+tab.getText()+",id为:"+tab.getId());
//                Toast.makeText(getActivity(), "点击"+tab.getText()+",id为:"+tab.getId(), Toast.LENGTH_SHORT).show();
                setAdapter(tab.getId());
//                setAdapter(1);
                initAinm();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // 处理 Tab 取消选中事件
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // 处理 Tab 再次选中事件
            }
        });


        home_testa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: 00000000000000000");

            }
        });
    }

    public void initView() {
        fragment_refresh_load=rootView.findViewById(R.id.fragment_refresh_load);
        tabLayout = rootView.findViewById(R.id.fragment_home_tab_layout);
        fragment_home_content_list=rootView.findViewById(R.id.fragment_home_content_list);
        home_testa = rootView.findViewById(R.id.home_test);
        navigation_button=rootView.findViewById(R.id.navigation_button);

    }
//获取全部标签
    private void SetTag() {
        OkhttpUtil.getRequest(UrlConstants.xzw_url + "tags/GetTagsAll", new ResponseCallback() {
            @Override
            public void response(String res) {
                TagsResponse tagsList= new Gson().fromJson(res,TagsResponse.class);
                List<Tags> da=tagsList.getTags();
                for (Tags p : da) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //                    Log.d("TAG", "initView: +====添加");
                            TabLayout.Tab tab=tabLayout.newTab();
                            tab.setText(p.getName());
                            tab.setId(p.getId());
                            tabLayout.addTab(tab);
                        }
                    });

                }
            }

            @Override
            public void failure(Exception e) {
                Log.d(TAG, "failure: "+e);
            }
        });

    }




}