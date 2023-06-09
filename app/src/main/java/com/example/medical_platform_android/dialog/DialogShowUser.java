package com.example.medical_platform_android.dialog;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.entity.PostsAndUser;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;

import java.util.HashMap;

public class DialogShowUser extends Dialog {
    private PostsAndUser postsAndUser;
    private Context context;
    private ImageView dialog_user_avatar
            ,dialog_user_follow
            ,dialog_user_chat;
    private TextView dialog_user_username
            ,dialog_user_introduction
            ,dialog_user_authentication
            ;
    private View layout_dialog_button;

    public DialogShowUser(@NonNull Context context, PostsAndUser postsAndUser) {
        super(context);
        this.context=context;
        this.postsAndUser = postsAndUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_user);

        //获取Dialog的win对象


        initView();
        setListener();



    }





    private void setListener() {
        //关注按钮
        dialog_user_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "点击关注", Toast.LENGTH_SHORT).show();
                if(SPUtil.loginStar(context)){
                    HashMap<String,Object> t=new HashMap<>();
                    t.put("userid1",Integer.parseInt(SPUtil.getString(context,"userid")));
                    t.put("userid2",postsAndUser.getUserid());
                    OkhttpUtil.postRequest(UrlConstants.xzw_url + "social-relationships/add-social-relationships",
                            t,
                            new ResponseCallback() {
                                @Override
                                public void response(String res) {
                                    postsAndUser.setFollow(false);
                                    setFollowView();
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void failure(Exception e) {
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                }else{
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "您还没有登录", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        //对话按钮
        dialog_user_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "点击对话", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    private void initView() {
        dialog_user_avatar = findViewById(R.id.dialog_user_avatar);
        dialog_user_follow = findViewById(R.id.dialog_user_follow);
        dialog_user_chat = findViewById(R.id.dialog_user_chat);
        dialog_user_username = findViewById(R.id.dialog_user_username);
        dialog_user_introduction = findViewById(R.id.dialog_user_introduction);
        dialog_user_authentication = findViewById(R.id.dialog_user_authentication);
        layout_dialog_button=findViewById(R.id.layout_dialog_button);

        //设置背景色
            //女：79EDAEB4
            //男：C5D8EA
        LinearLayout dialogMain = findViewById(R.id.dialog_main);
        if(postsAndUser.getGender().equals("女")){
            int colorGirl = Color.parseColor("#79EDAEB4");
            dialogMain.setBackgroundColor(colorGirl);
        }else{
            int colorBay = Color.parseColor("#C5D8EA");
            dialogMain.setBackgroundColor(colorBay);
        }

        setFollowView();
        
        //添加划线
        Drawable drawable = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(3);
                canvas.drawLine(0, 0, canvas.getWidth(), 0, paint);
//                canvas.drawLine(0, canvas.getHeight() - 2, canvas.getWidth(), canvas.getHeight() - 2, paint);
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
        layout_dialog_button.setBackground(drawable);

//        认证模块
        int user_role = postsAndUser.getUserRole();
        if(user_role==4){
            dialog_user_authentication.setText("暂无任何认证");
            dialog_user_authentication.setTextColor(Color.RED);
        }else if(user_role==3){
            dialog_user_authentication.setText("药厂认证");
        } else if (user_role==2) {
            dialog_user_authentication.setText("医师认证");
        } else if (user_role==1) {
            dialog_user_authentication.setText("管理员认证");
        }

//        头像
        String imagePath = postsAndUser.getHeadImage();
        String imageUrl=imagePath.replace(UrlConstants.xzw_old_path_image, UrlConstants.xzw_url+"upload/images");
        Glide.with(context).load(imageUrl).into(dialog_user_avatar);

//        用户名、简介
        dialog_user_username.setText(postsAndUser.getUsername());
        dialog_user_introduction.setText(postsAndUser.getIntroduction());


    }
//    设置关注的状态
    public void setFollowView(){
        //        设置关注状态
        if(postsAndUser.isFollow()){
            dialog_user_follow.setImageResource(R.drawable.button_follow);
        }else{
            dialog_user_follow.setImageResource(R.drawable.button_follow_no);
        }
    }
}
