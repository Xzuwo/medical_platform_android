package com.example.medical_platform_android.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.Comments;
import com.example.medical_platform_android.utils.DividerItemDecoration;
import com.example.medical_platform_android.utils.UrlConstants;

import java.util.List;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.MyCommentsViewHolder> {

    private List<Comments> commentsList;
    private Context context;

    public CommentsListAdapter(List<Comments> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_comments_view,parent,false);
        MyCommentsViewHolder myCommentsViewHolder=new MyCommentsViewHolder(view);

        return myCommentsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommentsViewHolder holder, int position) {
        Comments comments=commentsList.get(position);
        String imagePath = comments.getB();
        String imageUrl=imagePath.replace(UrlConstants.xzw_old_path_image, UrlConstants.xzw_url+"upload/images");
        Glide.with(context).load(imageUrl).into(holder.comment_user_avatar);

        holder.comment_user_name.setText(comments.getA());
        holder.comment_content.setText(comments.getContent());
        holder.comment_time.setText(comments.getCreateTime());

        //        设置分割线

//        holder.comments_recycler_view.addItemDecoration(new DividerItemDecoration(Color.GRAY, 5));
    }

    @Override
    public int getItemCount() {
        if(commentsList==null||commentsList.size()==0){
            return 0;
        }
        return commentsList.size();
    }

    class MyCommentsViewHolder extends RecyclerView.ViewHolder{
        private ImageView comment_user_avatar;

//        private RecyclerView comments_recycler_view;
        private View layout_comment_list;
        private TextView comment_user_name,comment_content,comment_time;

        public MyCommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_user_avatar = itemView.findViewById(R.id.comment_user_avatar);
            comment_user_name = itemView.findViewById(R.id.comment_user_name);
            comment_content = itemView.findViewById(R.id.comment_content);
            comment_time = itemView.findViewById(R.id.comment_time);

            layout_comment_list=itemView.findViewById(R.id.layout_comment_list);
//            comments_recycler_view=itemView.findViewById(R.id.comments_recycler_view);
        }


    }
}
