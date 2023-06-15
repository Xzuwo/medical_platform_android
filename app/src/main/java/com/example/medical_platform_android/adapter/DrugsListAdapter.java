package com.example.medical_platform_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.Drugs;

import java.util.List;

public class DrugsListAdapter extends RecyclerView.Adapter<DrugsListAdapter.MyViewHolder>{

    private List<Drugs> drugsListData;

    private MyOnItemClickListener myOnItemClickListener;
    private Context context;

    public  DrugsListAdapter(Context context){
        this.context = context;
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setDrugsListData(List<Drugs> drugsListData){
        this.drugsListData = drugsListData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_drugs,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Drugs drugs = drugsListData.get(position);
        holder.tvDrugsName1.setText(drugs.getName());
        holder.tvDrugsPrice1.setText(drugs.getPrice() + "");
        holder.tvDrugsCount1.setText(drugs.getB() + "");

        String imagePath = drugs.getA();
        Glide.with(context).load(imagePath).into(holder.tvDrugsImage);

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
        if(drugsListData == null){
            return 0;
        }else{
            return drugsListData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView tvDrugsImage;
        TextView tvDrugsName1;
        TextView tvDrugsPrice1;
        TextView tvDrugsCount1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDrugsImage = itemView.findViewById(R.id.iv_drugs_image);
            tvDrugsName1 = itemView.findViewById(R.id.tv_drugs_name1);
            tvDrugsPrice1 = itemView.findViewById(R.id.tv_drugs_price1);
            tvDrugsCount1 = itemView.findViewById(R.id.tv_drugs_count1);

        }

    }
}
