package com.example.medical_platform_android.entity;

import java.util.List;

public class CommentResponse {
    private int code;
    private String message;
    private List<Comments> dataobject;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Comments> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<Comments> dataobject) {
        this.dataobject = dataobject;
    }

    public CommentResponse() {
    }
}
