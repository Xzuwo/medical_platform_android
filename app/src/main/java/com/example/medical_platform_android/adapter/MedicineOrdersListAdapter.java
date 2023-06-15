package com.example.medical_platform_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.MedicineOrders;


import java.util.List;

public class MedicineOrdersListAdapter extends RecyclerView.Adapter<MedicineOrdersListAdapter.MyViewHolder>{

    private List<MedicineOrders> medicineOrdersListData;

    private MyOnItemClickListener myOnItemClickListener;
    private Context context;

    public  MedicineOrdersListAdapter(Context context){
        this.context = context;
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMedicineOrdersListData(List<MedicineOrders> medicineOrdersListData){
        this.medicineOrdersListData = medicineOrdersListData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_medicine_orders,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MedicineOrders medicineOrders = medicineOrdersListData.get(position);
        holder.tvOrderId.setText(medicineOrders.getId() + "");
        holder.tvOrderDate.setText(medicineOrders.getCreateTime());
        holder.tvOrderPerson.setText(medicineOrders.getUsername());
        holder.tvOrderStatus.setText(medicineOrders.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myOnItemClickListener != null){
                    myOnItemClickListener.onItemOnClick(holder.itemView,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(medicineOrdersListData == null){
            return 0;
        }else{
            return medicineOrdersListData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrderId;
        TextView tvOrderDate;
        TextView tvOrderPerson;
        TextView tvOrderStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderPerson = itemView.findViewById(R.id.tv_order_person);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
        }

    }
}
