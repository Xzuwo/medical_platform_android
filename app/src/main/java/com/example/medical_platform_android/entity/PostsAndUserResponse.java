package com.example.medical_platform_android.entity;

import java.util.List;

public class PostsAndUserResponse {
    public int code;
    public String message;
    public List<PostsAndUser> dataobject;

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

    public List<PostsAndUser> getDataobject() {
        return dataobject;
    }

    public void setDataobject(List<PostsAndUser> dataobject) {
        this.dataobject = dataobject;
    }

    public PostsAndUserResponse(int code, String message, List<PostsAndUser> dataobject) {
        this.code = code;
        this.message = message;
        this.dataobject = dataobject;
    }

    public PostsAndUserResponse() {
    }
}
