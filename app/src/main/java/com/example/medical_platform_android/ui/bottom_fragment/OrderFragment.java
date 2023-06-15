package com.example.medical_platform_android.ui.bottom_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.MedicineOrdersListAdapter;
import com.example.medical_platform_android.entity.MedicineOrders;
import com.example.medical_platform_android.entity.MedicineOrdersListResponse;
import com.example.medical_platform_android.ui.activity.MedicineOrderDetailActivity;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFragment extends Fragment {

    private static final String TAG = "ContactsFragment";
    private RecyclerView rvMedicineOrder;
    private View rootView;
    private MedicineOrdersListAdapter medicineOrdersListAdapter;
    private List<MedicineOrders> medicineOrdersList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_order, container, false);
        initView();
        getDataFromNet();
        return rootView;
    }

    private void getDataFromNet() {
        String url = UrlConstants.FINDMEDICINEORDERSBYID_URL_Order;
        Map<String,Object> params = new HashMap<>();
        params.put("userId", SPUtil.getString(getActivity(),"userId"));
        Log.d(TAG,"userId: " + SPUtil.getString(getActivity(),"userId"));
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"response : res=" + res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MedicineOrdersListResponse medicineOrdersListResponse = new Gson().fromJson(res,MedicineOrdersListResponse.class);
                        if(medicineOrdersListResponse !=null && medicineOrdersListResponse.getCode() == 200){
                            medicineOrdersList = medicineOrdersListResponse.getMedicineOrdersList();
                            Log.d(TAG,"medicineOrdersList: " + medicineOrdersList);
                            medicineOrdersListAdapter.setMedicineOrdersListData(medicineOrdersList);
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
        rvMedicineOrder = rootView.findViewById(R.id.rv_medicine_order);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rvMedicineOrder.setLayoutManager(linearLayoutManager);
        //适配器
        medicineOrdersListAdapter = new MedicineOrdersListAdapter(getActivity());
        medicineOrdersListAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MedicineOrderDetailActivity.class);
                MedicineOrders medicineOrders = medicineOrdersList.get(position);
                intent.putExtra("medicineOrders",medicineOrders);
                Log.d(TAG,"medicineOrders: " + medicineOrders);
                startActivity(intent);
            }
        });
        rvMedicineOrder.setAdapter(medicineOrdersListAdapter);
    }
}