package com.example.medical_platform_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.ChatMessages;
import com.example.medical_platform_android.utils.SPUtil;

import java.text.BreakIterator;
import java.util.List;

public class ChatMessagesListAdapter extends RecyclerView.Adapter<ChatMessagesListAdapter.MyViewHolder>{

    private List<ChatMessages> chatMessagesListData;
    private Context context;

    public ChatMessagesListAdapter(Context context){
        this.context = context;
    }

    public void setChatMessagesListData(List<ChatMessages> chatMessagesListData){
        this.chatMessagesListData = chatMessagesListData;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_chat,parent,false);
        ChatMessagesListAdapter.MyViewHolder myViewHolder = new ChatMessagesListAdapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatMessages chatMessages = chatMessagesListData.get(position);
        int senderId = Integer.parseInt(SPUtil.getString(context,"userId"));
        int reveiverId = Integer.parseInt(SPUtil.getString(context,"position_users"));;
        Log.d("ChatMessagesListAdapter","senderId:" + senderId);
        Log.d("ChatMessagesListAdapter","reveiverId:" + reveiverId);

        if(chatMessages.getSenderId() == senderId && chatMessages.getReveiverId() == reveiverId){
            holder.tvRightMsg.setText(chatMessages.getMessage());
            holder.tvSenderName.setText(SPUtil.getString(context,"name"));
            return;
        }else if(chatMessages.getSenderId() == reveiverId && chatMessages.getReveiverId() == senderId){
            holder.tvLeftMsg.setText(chatMessages.getMessage());
            holder.tvReveiverName.setText(SPUtil.getString(context,"ReveiverName"));
            return;
        }


    }

    @Override
    public int getItemCount() {
        if(chatMessagesListData == null){
            return 0;
        }else{
            return chatMessagesListData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvRightMsg;
        public TextView tvLeftMsg;
        public TextView tvReveiverName;
        public TextView tvSenderName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRightMsg = itemView.findViewById(R.id.right_msg);
            tvLeftMsg = itemView.findViewById(R.id.left_msg);
            tvReveiverName = itemView.findViewById(R.id.tv_reveiver_name);
            tvSenderName = itemView.findViewById(R.id.tv_sender_name);
        }

    }
}
