package com.example.medical_platform_android.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical_platform_android.R;
import com.example.medical_platform_android.adapter.CommentsListAdapter;
import com.example.medical_platform_android.adapter.PostsListAdapter;
import com.example.medical_platform_android.entity.Comments;
import com.example.medical_platform_android.entity.DataNullResponse;
import com.example.medical_platform_android.utils.DividerItemDecoration;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogShowComments extends BottomSheetDialogFragment {
    private Context context;
    private List<Comments> commentsList;

    private int postId;
    private RecyclerView commentsRecyclerView;
    private EditText commentsEdit;

    private View view;
    private ImageView commentButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载布局文件
        view = inflater.inflate(R.layout.dialog_show_comments, container, false);

        initView(view); // 初始化控件
//        setBehavior(view); // 设置底部弹出的行为


        setAdapter();

        return view;
    }

    private void setAdapter() {

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);

                CommentsListAdapter commentsListAdapter = new CommentsListAdapter(commentsList,getActivity());
                commentsRecyclerView.setAdapter(commentsListAdapter);


                commentsRecyclerView.addItemDecoration(new DividerItemDecoration(Color.GRAY, 5));


            }
        });


    }

    private void initView(View view) {
        commentsRecyclerView = view.findViewById(R.id.comments_recycler_view);
        commentsEdit = view.findViewById(R.id.comments_edit);
        commentButton = view.findViewById(R.id.comment_button);
    }

//    private void setBehavior(@NonNull View view) {
//        // 设置底部弹出的行为
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
//        bottomSheetBehavior.setPeekHeight(300); // 设置弹出的高度
//        bottomSheetBehavior.setHideable(true); // 设置是否可以隐藏
//        bottomSheetBehavior.setDraggable(true); // 设置是否可以拖拽
//    }

    public DialogShowComments(Context context, List<Comments> commentsList,int postId) {
        this.context = context;
        this.commentsList = commentsList;
        this.postId=postId;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 设置底部弹出的宽度
        if (getDialog() != null) {
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            getDialog().getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 在这里可以设置RecyclerView的Adapter和LayoutManager
        // ...

        // 在这里可以设置评论按钮的点击事件
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 处理评论按钮的点击事件
                // ...
                if(SPUtil.loginStar(context)){
                    String comment=commentsEdit.getText().toString();
                    if(comment==null || comment==""){
                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        HashMap<String,Object> t=new HashMap<>();
                        t.put("userId",SPUtil.getString(context,"userid"));
                        t.put("postId",postId);
                        t.put("content",comment);

                        OkhttpUtil.postRequest(UrlConstants.xzw_url + "comments/addComment", t, new ResponseCallback() {
                            @Override
                            public void response(String res) {
                                DataNullResponse dataNullResponse = new Gson().fromJson(res, DataNullResponse.class);
                                if(dataNullResponse.getCode()==200){
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "评论成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Comments comments=new Comments(0,Integer.parseInt(SPUtil.getString(context,"userid")),postId,null,comment,new Date().toString(),
                                            SPUtil.getString(context,"headImage"),SPUtil.getString(context,"name"),"");
                                    commentsList.add(comments);
                                    setAdapter();
                                }



                            }

                            @Override
                            public void failure(Exception e) {
                                Log.d("TAG", "failure: 连接报错————————"+e);
                            }
                        });

                    }
                    commentsEdit.setText("");

                }else{
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "还没登录，不能评论", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });
    }
}
