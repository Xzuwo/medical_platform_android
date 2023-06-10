package com.example.medical_platform_android.ui.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.MedicineOrdersListAdapter;
import com.example.medical_platform_android.entity.MedicineOrders;
import com.example.medical_platform_android.entity.MedicineOrdersListResponse;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineOrderFragment extends Fragment {

    private static final String TAG = "ContactsFragment";
    private RecyclerView rvMedicineOrder;
    private View rootView;
    private MedicineOrdersListAdapter medicineOrdersListAdapter;
    private List<MedicineOrders> medicineOrdersList;

    public MedicineOrderFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //rootView = inflater.inflate(R.layout.fragment_order, container, false);
        //initView();
        //getDataFromNet();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvMedicineOrder.setLayoutManager(linearLayoutManager);
        //适配器
        medicineOrdersListAdapter = new MedicineOrdersListAdapter(getActivity());
        rvMedicineOrder.setAdapter(medicineOrdersListAdapter);
    }
}
