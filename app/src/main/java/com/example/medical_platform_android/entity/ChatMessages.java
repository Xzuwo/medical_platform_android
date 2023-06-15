package com.example.medical_platform_android.entity;

import java.io.Serializable;
import java.util.Date;

public class ChatMessages implements Serializable {
    private Integer id;
    private Integer senderId;
    private Integer reveiverId;
    private String message;
    private String createTime;
    private String a;
    private String b;
    private String c;

    public ChatMessages() {
    }

    public ChatMessages(Integer id, Integer senderId, Integer reveiverId, String message, String createTime, String a, String b, String c) {
        this.id = id;
        this.senderId = senderId;
        this.reveiverId = reveiverId;
        this.message = message;
        this.createTime = createTime;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReveiverId() {
        return reveiverId;
    }

    public void setReveiverId(Integer reveiverId) {
        this.reveiverId = reveiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "ChatMessages{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", reveiverId=" + reveiverId +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
