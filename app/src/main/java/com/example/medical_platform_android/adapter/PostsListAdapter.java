package com.example.medical_platform_android.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.dialog.DialogShowComments;
import com.example.medical_platform_android.dialog.DialogShowUser;
import com.example.medical_platform_android.entity.CommentResponse;
import com.example.medical_platform_android.entity.Comments;
import com.example.medical_platform_android.entity.Posts;
import com.example.medical_platform_android.entity.PostsAndUser;
import com.example.medical_platform_android.ui.view.HeartView;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.MyPostsViewHolder> {

    private List<PostsAndUser> postsAndUsersList;
    private Context context;

    boolean contentStare=false;

    public void AddPosts(List<PostsAndUser> addDa){
        for (PostsAndUser p:
             addDa) {
            postsAndUsersList.add(p);
        }

    }

    public PostsListAdapter(List<PostsAndUser> postsList, Context context) {
        this.postsAndUsersList = postsList;
        this.context = context;
    }

    public PostsListAdapter() {
    }

    @NonNull
    @Override
    public MyPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        MyPostsViewHolder myPostsViewHolder =new MyPostsViewHolder(itemView);




        return myPostsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostsViewHolder holder, int position) {
        PostsAndUser posts=postsAndUsersList.get(position);

        String imagePath = posts.getHeadImage();
        String imageUrl=imagePath.replace(UrlConstants.xzw_old_path_image, UrlConstants.xzw_url+"upload/images");
        Glide.with(context).load(imageUrl).into(holder.posts_item_avatar);

        holder.posts_item_username.setText(posts.getUsername()+"");
        holder.posts_item_publish_time.setText(posts.getPublishTime().toString()+"");
        holder.posts_item_a.setText(posts.getA()+"");
        holder.posts_item_like_count.setText(posts.getLikeCount()+"");
        holder.posts_item_comment_count.setText(posts.getCommentCount()+"");
//        设置分割线
        Drawable drawable = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(1);
//                canvas.drawLine(0, 0, canvas.getWidth(), 0, paint);
                canvas.drawLine(0, canvas.getHeight() - 2, canvas.getWidth(), canvas.getHeight() - 2, paint);
            }

            @Override
            public void setAlpha(int alpha) {}

            @Override
            public void setColorFilter(ColorFilter colorFilter) {}

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        holder.layout_item_posts_a.setBackground(drawable);

        holder.posts_item_context.loadDataWithBaseURL(null,"","text/html",
                "utf-8",null);

//        设置与用户的关系

        if(posts.isLikeState()){
            holder.posts_item_like.setImageResource(R.drawable.button_like);
        }else{
            holder.posts_item_like.setImageResource(R.drawable.button_like_no);
        }

//     点赞
        holder.posts_item_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: ________________________");
//                判断是否登录
                if(SPUtil.getString(context,"userid")!=null || SPUtil.getString(context,"userid")==""){
//                    未登录
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "请先登录，不能点赞", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                Map<String,Object> t=new HashMap<>();
                t.put("userid",SPUtil.getString(context,"userid"));
                t.put("postId",posts.getPostsId());
                OkhttpUtil.postRequest(UrlConstants.xzw_url + "like/addLike", t, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        if(res.startsWith("330")){
//                            点赞失败
                            ((Activity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "点赞失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            if(posts.isLikeState()){
        //                    一点赞
        //                    取消点赞
                                holder.posts_item_like.setImageResource(R.drawable.button_like_no);
                                holder.posts_item_like_count.setText((Integer.parseInt(holder.posts_item_like_count.getText().toString())-1)+"");

                            }else{
                                holder.posts_item_like.setImageResource(R.drawable.button_like);
                                holder.posts_item_like_count.setText((Integer.parseInt(holder.posts_item_like_count.getText().toString())+1)+"");
                            }
                        }
                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });



            }

        });

        //点头像出个人信息
        holder.posts_item_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogShowUser dialogShowUser=new DialogShowUser(context,posts);

                Window window =dialogShowUser.getWindow();


                if (window != null) {
                    window.setGravity(Gravity.CENTER);
                    WindowManager.LayoutParams layoutParams=window.getAttributes();
                    layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
                    window.setWindowAnimations(R.style.DialogAnimation);
                }

                dialogShowUser.show();
            }
        });

//        评论
        holder.posts_item_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //数据请求
                OkhttpUtil.getRequest(UrlConstants.xzw_url + "comments/getCommentsByPostId?postId=" + posts.getPostsId(), new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        Log.d("TAG", "response: 1111111111111111111111111111111"+res);
                        CommentResponse commentResponse = new Gson().fromJson(res, CommentResponse.class);
                        List<Comments> commentsList=commentResponse.getDataobject();
                        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        DialogShowComments dialog = new DialogShowComments(context, commentsList,posts.getPostsId());
                        dialog.show(fragmentManager, "CommentsDialog");

                    }

                    @Override
                    public void failure(Exception e) {
                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "获取评论失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
//
//                Log.d("TAG", "onClick: 11111111111111111111111111111111111111111111");

            }
        });
//      内容显示
        holder.posts_item_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contentStare){
                    holder.posts_item_context.loadDataWithBaseURL(null,"","text/html","utf-8",null);
                    holder.posts_item_context.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                }else{
                    holder.posts_item_context.loadDataWithBaseURL(null,posts.getContent(),"text/html","utf-8",null);
                    holder.posts_item_context.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.posts_item_context.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                            holder.posts_item_context.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        }
                    });
                }
                contentStare=!contentStare;
            }
        });


//        更多
        holder.posts_item_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.posts_item_not_interested.setEnabled(!holder.posts_item_not_interested.isEnabled());
            }
        });
//不感兴趣
        holder.posts_item_not_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsAndUsersList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,postsAndUsersList.size()-position);
            }
        });

    }




    @Override
    public int getItemCount() {
        return postsAndUsersList.size();
    }
    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            Log.d("TAG", url);
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    class MyPostsViewHolder extends RecyclerView.ViewHolder{
        private ImageView posts_item_avatar,posts_item_more,posts_item_comment,posts_item_like;
        private Button posts_item_not_interested;
        private TextView posts_item_username,posts_item_publish_time,posts_item_a,posts_item_comment_count,posts_item_like_count;

        private View layout_item_posts_a;
        private WebView posts_item_context;
        private ViewGroup layout_item_posts_like;

        public MyPostsViewHolder(@NonNull View itemView) {
            super(itemView);

            // ImageView
            posts_item_avatar = itemView.findViewById(R.id.posts_item_avatar);
//            posts_item_follow = itemView.findViewById(R.id.posts_item_follow);
            posts_item_more = itemView.findViewById(R.id.posts_item_more);
//            posts_item_dialogue = itemView.findViewById(R.id.posts_item_dialogue);
            posts_item_comment = itemView.findViewById(R.id.posts_item_comment);
            posts_item_like = itemView.findViewById(R.id.posts_item_like);

            // Button
            posts_item_not_interested = itemView.findViewById(R.id.posts_item_not_interested);

            // TextView
            posts_item_username = itemView.findViewById(R.id.posts_item_username);
            posts_item_publish_time = itemView.findViewById(R.id.posts_item_publish_time);
            posts_item_a = itemView.findViewById(R.id.posts_item_a);
            posts_item_comment_count = itemView.findViewById(R.id.posts_item_comment_count);
            posts_item_like_count = itemView.findViewById(R.id.posts_item_like_count);

//            View
            layout_item_posts_a=itemView.findViewById(R.id.layout_item_posts_a);
//            layout_item_posts_like=itemView.findViewById(R.id.layout_item_posts_like);
            posts_item_context=itemView.findViewById(R.id.posts_item_context);
        }


    }




}
