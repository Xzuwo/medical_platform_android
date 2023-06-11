package com.example.medical_platform_android.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.UsersLsitAdapter;
import com.example.medical_platform_android.entity.Doctors;
import com.example.medical_platform_android.entity.DoctorsListResponse;
import com.example.medical_platform_android.entity.UserListResponse;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.ui.activity.ChatActivity;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.List;

public class ContactsFragment extends Fragment {
    private static final String TAG = "ContactsFragment";
    private RecyclerView rvContacts;
    private View rootView;
    private UsersLsitAdapter usersLsitAdapter;
    private List<Users> usersListData;
    private ImageView ivReturn;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        initView();
        getDataFromNet();
        //setListeners();
        return rootView;
    }

    private void setListeners() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void getDataFromNet() {
        String url = UrlConstants.FINDALL_URL;
        OkhttpUtil.postRequest(url, null, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"response : res=" + res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserListResponse userListResponse = new Gson().fromJson(res,UserListResponse.class);
                        if(userListResponse != null && userListResponse.getCode() == 200){
                            usersListData = userListResponse.getUsersList();
                            Log.d(TAG,"usersListData : " + usersListData);
                            usersLsitAdapter.setUsersListData(usersListData);
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {
                Log.d(TAG,"网络访问出现问题，错误原因：" + e.getMessage());
            }
        });
    }

    private void initView() {
        ivReturn = rootView.findViewById(R.id.return_icons);
        rvContacts = rootView.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvContacts.setLayoutManager(linearLayoutManager);
        //适配器
        usersLsitAdapter = new UsersLsitAdapter(getActivity());
        usersLsitAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                Toast.makeText(rootView.getContext(), "单击了第" + position + "行", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Users users = usersListData.get(position);
                SPUtil.saveString(getActivity(),"ReveiverName",users.getName());
                intent.putExtra("users",users);
                intent.putExtra("position:",position);
                startActivity(intent);
            }
        });
        rvContacts.setAdapter(usersLsitAdapter);

    }
}