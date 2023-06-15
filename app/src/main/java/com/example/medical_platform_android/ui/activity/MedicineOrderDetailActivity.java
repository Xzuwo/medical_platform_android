package com.example.medical_platform_android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.MedicineOrders;

public class MedicineOrderDetailActivity extends AppCompatActivity {

    private static final String TAG = "MedicineOrderDetailActivity";
    private TextView tvOrderId,tvOrderDate,tvOrderPerson,tvDrugsName,tvDrugsManu,tvDrugsTreat,tvDrugeCount,tvDrugsPrice,tvDrugsSum,tvOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_order_detail);

        initView();
    }

    private void initView() {
        tvOrderId = findViewById(R.id.tv_order_id);
        tvOrderDate = findViewById(R.id.tv_order_date);
        tvOrderPerson = findViewById(R.id.tv_order_person);
        tvDrugsName = findViewById(R.id.tv_drugs_name);
        tvDrugsManu = findViewById(R.id.tv_drugs_manu);
        tvDrugsTreat = findViewById(R.id.tv_drugs_treat);
        tvDrugeCount = findViewById(R.id.tv_drugs_count);
        tvDrugsPrice = findViewById(R.id.tv_drugs_price);
        tvDrugsSum = findViewById(R.id.tv_drugs_sum);
        tvOrderStatus = findViewById(R.id.tv_order_status);


        Intent intent = getIntent();
        MedicineOrders medicineOrders = (MedicineOrders) intent.getSerializableExtra("medicineOrders");

        tvOrderId.setText(medicineOrders.getId() + "");
        tvOrderDate.setText(medicineOrders.getCreateTime());
        tvOrderPerson.setText(medicineOrders.getUsername());
        tvDrugsName.setText(medicineOrders.getDrugsname());
        tvDrugsManu.setText(medicineOrders.getDrugsManufacturer());
        tvDrugsTreat.setText(medicineOrders.getDrugsDescription());
        tvDrugeCount.setText(medicineOrders.getQuantity() + "");
        tvDrugsPrice.setText((int) medicineOrders.getDrugsPrice() + "");
        tvDrugsSum.setText((int) (medicineOrders.getDrugsPrice() * medicineOrders.getQuantity()) + "");
        tvOrderStatus.setText(medicineOrders.getStatus());
    }
}