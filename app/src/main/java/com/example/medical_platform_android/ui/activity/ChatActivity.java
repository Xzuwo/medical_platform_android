package com.example.medical_platform_android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medical_platform_android.Interface.MyOnItemClickListener;
import com.example.medical_platform_android.R;
import com.example.medical_platform_android.activity.Login;
import com.example.medical_platform_android.adapter.ChatMessagesListAdapter;
import com.example.medical_platform_android.adapter.UsersLsitAdapter;
import com.example.medical_platform_android.entity.ChatMessages;
import com.example.medical_platform_android.entity.ChatMessagesListResponse;
import com.example.medical_platform_android.entity.UserListResponse;
import com.example.medical_platform_android.entity.Users;
import com.example.medical_platform_android.ui.Fragment.ContactsFragment;
import com.example.medical_platform_android.utils.OkhttpUtil;
import com.example.medical_platform_android.utils.ResponseCallback;
import com.example.medical_platform_android.utils.SPUtil;
import com.example.medical_platform_android.utils.UrlConstants;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private TextView tvChatPerson;
    private ImageView ivReturn;
    private Button btnSend;
    private EditText inputText;
    private RecyclerView rvMsgRecyclerView;
    private ChatMessagesListAdapter chatMessagesListAdapter;
    private List<ChatMessages> chatMessagesListData;
    private Users users;
    private int senderId;
    private int reveiverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initView();
        loadReceiverDetails();
        getDataFromNet();
        setListener();
    }
    private void setSenderId(int senderId){
        this.senderId = senderId;
    }
    private void setReveiverId(int reveiverId){
        this.reveiverId = reveiverId;
    }
    private void getDataFromNet() {
        String url = UrlConstants.FINDALLMESSAGEBYID_URL_CHAT;
        Map<String,Object> params = new HashMap<>();
        params.put("senderId",senderId);
        Log.d(TAG,"senderId: " + senderId);
        params.put("reveiverId",reveiverId);
        Log.d(TAG,"reveiverId: " + reveiverId);
        OkhttpUtil.postRequest(url,params, new ResponseCallback() {
            @Override
            public void response(String res) {
                Log.d(TAG,"response : res=" + res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ChatMessagesListResponse chatMessagesListResponse = new Gson().fromJson(res,ChatMessagesListResponse.class);
                        if(chatMessagesListResponse != null && chatMessagesListResponse.getCode() == 200){
                            chatMessagesListData = chatMessagesListResponse.getChatMessagesList();
                            Log.d(TAG,"chatMessagesListData : " + chatMessagesListData);
                            chatMessagesListAdapter.setChatMessagesListData(chatMessagesListData);
                        }
                        if(chatMessagesListData != null){
                            rvMsgRecyclerView.smoothScrollToPosition(chatMessagesListData.size() - 1);
                        }
                    }
                });
            }

            @Override
            public void failure(Exception e) {
                Log.d(TAG,"网络访问出现问题，错误原因：" + e.getMessage());
            }
        });

    }
    private void initView() {
        tvChatPerson = findViewById(R.id.tv_chat_person);
        ivReturn = this.findViewById(R.id.iv_return);
        btnSend = this.findViewById(R.id.send);
        inputText = this.findViewById(R.id.input_text);

        rvMsgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvMsgRecyclerView.setLayoutManager(linearLayoutManager);
        //适配器
        chatMessagesListAdapter = new ChatMessagesListAdapter(this);
        rvMsgRecyclerView.setAdapter(chatMessagesListAdapter);
    }

    private void setListener() {
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this,ContactsActivity.class);
                startActivity(intent);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = UrlConstants.SENDMESSAGE_URL_CHAT;
                Date currentDate = new Date();
                Map<String,Object> params = new HashMap<>();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createTime = formatter.format(currentDate);
                params.put("senderId",senderId);
                params.put("reveiverId",reveiverId);
                //params.put("createTime",new Date());
                params.put("message",inputText.getText().toString());
                OkhttpUtil.postRequest(url, params, new ResponseCallback() {
                    @Override
                    public void response(String res) {
                        Log.d(TAG,"params: " + params);
                        Log.d(TAG,"response : res=" + res);
                        getDataFromNet();
                        if(chatMessagesListData != null){
                            rvMsgRecyclerView.smoothScrollToPosition(chatMessagesListData.size() - 1);
                        }
                    }

                    @Override
                    public void failure(Exception e) {
                        Log.d(TAG,"网络访问出现问题，错误原因：" + e.getMessage());
                    }
                });
                inputText.setText("");
            }
        });
    }

    private void loadReceiverDetails() {
        Intent intent = getIntent();
        users = (Users) intent.getSerializableExtra("users");
        senderId = Integer.parseInt(SPUtil.getString(ChatActivity.this,"userId"));
        reveiverId = users.getId();
        tvChatPerson.setText(users.getName());
        Log.d("name: ",users.getName());
        Log.d("reveiverId: ", String.valueOf(reveiverId));
        Log.d("users: ",users.toString());
    }

}