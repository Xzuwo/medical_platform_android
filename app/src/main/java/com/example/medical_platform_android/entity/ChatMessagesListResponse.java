package com.example.medical_platform_android.entity;

import java.util.List;

public class ChatMessagesListResponse {
    private int code;
    private String msg;
    private List<ChatMessages> chatMessagesList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ChatMessages> getChatMessagesList() {
        return chatMessagesList;
    }

    public void setChatMessagesList(List<ChatMessages> chatMessagesList) {
        this.chatMessagesList = chatMessagesList;
    }
}
