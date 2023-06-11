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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.DrugsListAdapter;
import com.example.medical_platform_android.adapter.MedicineOrdersListAdapter;
import com.example.medical_platform_android.entity.Drugs;
import com.example.medical_platform_android.entity.DrugsListResponse;
import com.example.medical_platform_android.entity.MedicineOrders;
import com.example.medical_platform_android.entity.MedicineOrdersListResponse;
import com.example.medical_platform_android.ui.activity.MedicineOrderDetailActivity;
import com.example.medical_platform_android.ui.activity.PurchaseActivity;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrugsFragment extends Fragment {

    private static final String TAG = "ContactsFragment";
    private RecyclerView rvDrugs;
    private View rootView;
    private DrugsListAdapter drugsListAdapter;
    private List<Drugs> drugsListData;
    private String drugsname = "";
    private EditText editText;
    private ImageButton ibDrgusSearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrugsFragment() {
        // Required empty public constructor
    }

    public static DrugsFragment newInstance(String param1, String param2) {
        DrugsFragment fragment = new DrugsFragment();
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
        rootView = inflater.inflate(R.layout.fragment_drugs, container, false);

        initView();
        getDataFromNet(drugsname);
        setListeners();
        return rootView;
    }

    private void setListeners() {
        ibDrgusSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drugsname = editText.getText().toString();
                Log.d(TAG,"drugsname: " + drugsname);
                getDataFromNet(drugsname);
            }
        });
    }

    private void getDataFromNet(String drugsname) {
        String url = UrlConstants.FINDALLBYMAPPER_URL_DRUGS;
        Map<String,Object> params = new HashMap<>();
        params.put("drugsname",drugsname);
        Log.d(TAG,"drugsname: " + drugsname);
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"response : res=" + res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DrugsListResponse drugsListResponse = new Gson().fromJson(res,DrugsListResponse.class);
                        drugsListData = drugsListResponse.getDrugsList();
                        Log.d(TAG,"drugsListData: " + drugsListData);
                        drugsListAdapter.setDrugsListData(drugsListData);
                        if(drugsListResponse !=null && drugsListResponse.getCode() == 200){

                        }else{
                            Toast.makeText(getActivity(), "没有该药品", Toast.LENGTH_SHORT).show();
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
        rvDrugs = rootView.findViewById(R.id.rv_drugs);
        editText = rootView.findViewById(R.id.select01);
        ibDrgusSearch = rootView.findViewById(R.id.ib_drugs_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rvDrugs.setLayoutManager(linearLayoutManager);
        //适配器
        drugsListAdapter = new DrugsListAdapter(getActivity());
        drugsListAdapter.setMyOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onItemOnClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PurchaseActivity.class);
                Drugs drugs = drugsListData.get(position);
                intent.putExtra("drugs",drugs);
                Log.d(TAG,"drugs: " + drugs);
                startActivity(intent);
            }
        });
        rvDrugs.setAdapter(drugsListAdapter);
    }

}