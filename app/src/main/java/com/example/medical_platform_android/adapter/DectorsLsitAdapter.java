package com.example.medical_platform_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.Doctors;
import com.example.medical_platform_android.entity.Users;

import java.util.List;

public class DectorsLsitAdapter extends RecyclerView.Adapter <DectorsLsitAdapter.MyViewHolder>{
    private List<Doctors> doctorsListData;
    private Context context;
    private MyOnItemClickListener myOnItemClickListener;

    public DectorsLsitAdapter(Context context){
        this.context = context;
    }
    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }
    public void setDoctorsListData(List<Doctors> doctorsListData){
        this.doctorsListData = doctorsListData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_contacts,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Doctors doctors = doctorsListData.get(position);
        holder.tvName.setText(doctors.getName());

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
        if(doctorsListData == null){
            return 0;
        }else{
            return doctorsListData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
