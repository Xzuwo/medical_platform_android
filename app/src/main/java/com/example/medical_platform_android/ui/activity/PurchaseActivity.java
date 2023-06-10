package com.example.medical_platform_android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.Drugs;
import com.example.medical_platform_android.ui.bottom_fragment.DrugsFragment;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;

import java.util.HashMap;
import java.util.Map;

public class PurchaseActivity extends AppCompatActivity {

    private static final String TAG = "PurchaseActivity";
    private ImageView ivDrugsIcon,ivReturn;
    private ImageButton btnMinus,btnAdd;
    private TextView tvDrugsDetail;
    private TextView tvDrugsPrice;
    private EditText edtQuantity;
    private Button btnPurchase;
    private Drugs drugs;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        initView();
        setLinisteners();
    }

    private void setLinisteners() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = UrlConstants.GENERATEORDER_URL_ORDER;
                Map<String,Object> params = new HashMap<>();
                params.put("userId", SPUtil.getString(PurchaseActivity.this,"userId"));
                Log.d("TAG","userId: " + SPUtil.getString(PurchaseActivity.this,"userId"));
                params.put("drugsId",drugs.getId());
                Log.d("TAG","drugsId: " + drugs.getId());
                params.put("quantity",num);
                if(num > 0){
                    OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                        @Override
                        public void response(String res) {
                            Log.d(TAG,"response : res=" + res);
                        }

                        @Override
                        public void failure(Exception e) {
                            Log.d(TAG,"网络访问出现问题，错误原因：" + e.getMessage());
                        }
                    });
                    Toast.makeText(PurchaseActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PurchaseActivity.this, "至少需要购买一件药品", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtQuantity.getText().toString();
                num = Integer.parseInt(text);
                if(num < Integer.parseInt(drugs.getB()))
                    num ++;
                edtQuantity.setText(num + "");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtQuantity.getText().toString();
                num = Integer.parseInt(text);
                if(num >= 1)
                    num --;
                edtQuantity.setText(num + "");
            }
        });
    }

    private void initView() {
        ivReturn = findViewById(R.id.back_icons);
        ivDrugsIcon = findViewById(R.id.shop_icons_xiangqing);
        tvDrugsDetail = findViewById(R.id.tit_desc_xiangqing);
        tvDrugsPrice = findViewById(R.id.price_xiangqing);
        btnPurchase = findViewById(R.id.add_cars);
        btnMinus = findViewById(R.id.btn_minus);
        btnAdd = findViewById(R.id.btn_add);
        edtQuantity = findViewById(R.id.edt_quantity);

        Intent intent = getIntent();
        drugs = (Drugs) intent.getSerializableExtra("drugs");

        String imagePath = drugs.getA();
        Log.d("TAG","imagePath: " + imagePath);
        Glide.with(PurchaseActivity.this).load(imagePath).into(ivDrugsIcon);
        tvDrugsDetail.setText("制造商：" + drugs.getManufacturer() + "  " + "有治疗" + drugs.getDescription() + "等效果");
        tvDrugsPrice.setText(drugs.getPrice() + "");
    }
}