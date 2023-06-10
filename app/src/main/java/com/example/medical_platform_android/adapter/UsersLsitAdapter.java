package com.example.medical_platform_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.medical_platform_android.databinding.ItemContactsBinding;
import com.example.medical_platform_android.entity.Doctors;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.listeners.UserListener;
import com.example.medical_platform_android.utils.SPUtil;

import java.util.List;

public class UsersLsitAdapter extends RecyclerView.Adapter <UsersLsitAdapter.MyViewHolder>{
    private List<Users> usersListData;
    private Context context;
    private MyOnItemClickListener myOnItemClickListener;

    public UsersLsitAdapter(Context context){
        this.context = context;
    }
    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }
    public void setUsersListData(List<Users> usersListData){
        this.usersListData = usersListData;
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
        Users users = usersListData.get(position);
        holder.tvName.setText(users.getName());

        String imagePath = users.getHeadImage();
        Glide.with(context).load(imagePath).into(holder.ivImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myOnItemClickListener != null){
                    myOnItemClickListener.onItemOnClick(holder.itemView,position);
                    SPUtil.saveString(context,"position_users", String.valueOf(position + 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(usersListData == null){
            return 0;
        }else{
            return usersListData.size();
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
