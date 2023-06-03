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
import android.widget.Toast;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.DectorsLsitAdapter;
import com.example.medical_platform_android.entity.Doctors;
import com.example.medical_platform_android.entity.DoctorsListResponse;
import com.example.medical_platform_android.entity.UserListResponse;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.ui.activity.ChatActivity;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.List;

public class ContactsFragment extends Fragment {
    private static final String TAG = "ContactsFragment";
    private RecyclerView rvContacts;
    private View rootView;
    private DectorsLsitAdapter doctorsLsitAdapter;
    private List<Doctors> doctorsListData;

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
        return rootView;
    }

    private void getDataFromNet() {
        String url = UrlConstants.FINDALL_URL_DOCTORS;
        OkhttpUtil.postRequest(url, null, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"response : res=" + res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DoctorsListResponse doctorsListResponse = new Gson().fromJson(res,DoctorsListResponse.class);
                        if(doctorsListResponse != null && doctorsListResponse.getCode() == 200){
                            doctorsListData = doctorsListResponse.getDoctorsList();
                            Log.d(TAG,"doctorsListData : " + doctorsListData);
                            doctorsLsitAdapter.setDoctorsListData(doctorsListData);
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
        rvContacts = rootView.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvContacts.setLayoutManager(linearLayoutManager);
        //适配器
        doctorsLsitAdapter = new DectorsLsitAdapter(getActivity());
        doctorsLsitAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                Toast.makeText(rootView.getContext(), "aaaaaaaaa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Doctors doctors = doctorsListData.get(position);
                intent.putExtra("doctors",doctors);
                startActivity(intent);
            }
        });
        rvContacts.setAdapter(doctorsLsitAdapter);

    }
}