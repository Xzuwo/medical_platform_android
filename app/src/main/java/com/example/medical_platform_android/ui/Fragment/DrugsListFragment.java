package com.example.medical_platform_android.ui.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.ManagerHome;
import com.example.medical_platform_android.adapter.DrugsListAdapter;
import com.example.medical_platform_android.entity.Drugs;
import com.example.medical_platform_android.entity.FindAllDrugsResponse;
import com.example.medical_platform_android.entity.RegisterResponse;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DrugsListFragment extends Fragment {

    private static final String TAG = "DrugsListFragment";
    private View rootView,AddDrugView;
    private RecyclerView rvDrugsList;
    private DrugsListAdapter drugsListAdapter;
    private ImageView back,SearchDrugsList,AddDrug;
    private TextView DrugName,DrugManufacturer,DrugPrice,DrugDescription;
    private TextView AddDrugsName,AddDrugsPrice,AddDrugsManufacturer,AddDescription;

    public DrugsListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_drugs_list, container, false);
        initView();
        getData();
        SetListeners();
        return rootView;
    }

    private void SetListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ManagerHome.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        SearchDrugsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = UrlConstants.FindAllDrugs;
                Map<String,Object> params = new HashMap<>();
                params.put("name",DrugName.getText().toString());
                params.put("manufacturer",DrugManufacturer.getText().toString());
                params.put("price",DrugPrice.getText());
                params.put("description",DrugDescription.getText().toString());
                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                FindAllDrugsResponse findAllDrugsResponse = new Gson().fromJson(res,FindAllDrugsResponse.class);
                                if (findAllDrugsResponse != null && findAllDrugsResponse.getCode() == 200) {
                                    List<Drugs> drugsList = findAllDrugsResponse.getDrugList();
                                    drugsListAdapter.setDrugsListData(drugsList);
                                    Toast.makeText(getActivity(), "搜索成功", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(getActivity(), findAllDrugsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });
            }
        });
        AddDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                AddDrugView = LayoutInflater.from(getContext()).inflate(R.layout.add_drugs,null);
                AddDrugsName = AddDrugView.findViewById(R.id.AddDrug_Name);
                AddDrugsManufacturer = AddDrugView.findViewById(R.id.AddDrug_Manufacturer);
                AddDrugsPrice = AddDrugView.findViewById(R.id.AddDrug_Price);
                AddDescription = AddDrugView.findViewById(R.id.AddDrug_Description);
                builder.setView(AddDrugView);
                builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(AddDrugsName.getText().toString().equals("") || AddDrugsManufacturer.getText().toString().equals("") || AddDrugsPrice.getText().toString().equals("") || AddDescription.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "请完善信息", Toast.LENGTH_SHORT).show();
                        }else{
                            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                            if(pattern.matcher(AddDrugsPrice.getText()).matches()) {
                                String url = UrlConstants.AddDrugs;
                                Map<String,Object> params = new HashMap<>();
                                params.put("name",AddDrugsName.getText());
                                params.put("manufacturer",AddDrugsManufacturer.getText());
                                params.put("price",AddDrugsPrice.getText());
                                params.put("description",AddDescription.getText());
                                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                                    @Override
                                    public void response(String res) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                RegisterResponse registerResponse = new Gson().fromJson(res,RegisterResponse.class);
                                                if(registerResponse != null & registerResponse.getCode() == 200) {
                                                    Toast.makeText(getActivity(), registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                                    getData();
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void failure(Exception e) {

                                    }
                                });
                            }else{
                                Toast.makeText(getContext(), "非法输入", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void getData() {
        String url = UrlConstants.FindAllDrugs;
        Map<String,Object> params = new HashMap<>();
        OkhttpUtil.postRequest(url, params, new ResponseCallback() {
            @Override
            public void response(String res) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FindAllDrugsResponse findAllDrugsResponse = new Gson().fromJson(res,FindAllDrugsResponse.class);
                        if (findAllDrugsResponse != null && findAllDrugsResponse.getCode() == 200) {
                            List<Drugs> drugsList = findAllDrugsResponse.getDrugList();
                            drugsListAdapter.setDrugsListData(drugsList);
                        } else{
                            Toast.makeText(getActivity(), findAllDrugsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }


    private void initView() {
        rvDrugsList = rootView.findViewById(R.id.DrugsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvDrugsList.setLayoutManager(linearLayoutManager);
        drugsListAdapter = new DrugsListAdapter(getActivity());
        rvDrugsList.setAdapter(drugsListAdapter);

        back = rootView.findViewById(R.id.DrugsList_back);
        SearchDrugsList = rootView.findViewById(R.id.DrugsList_Search);
        DrugName = rootView.findViewById(R.id.DrugsList_name);
        DrugManufacturer = rootView.findViewById(R.id.DrugsList_manufacturer);
        DrugPrice = rootView.findViewById(R.id.DrugsList_price);
        DrugDescription = rootView.findViewById(R.id.DrugsList_description);
        SearchDrugsList = rootView.findViewById(R.id.DrugsList_Search);
        AddDrug = rootView.findViewById(R.id.DrugsList_addDrugs);
    }
}