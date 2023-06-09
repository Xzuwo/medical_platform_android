package com.example.medical_platform_android.entity;

import java.util.List;

public class TagsResponse {
    private int code;
    private String message;
    private List<Tags> dataobject;

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

    public List<Tags> getTags() {
        return dataobject;
    }

    public void setTags(List<Tags> tags) {
        this.dataobject = tags;
    }

    public TagsResponse(int code, String message, List<Tags> tags) {
        this.code = code;
        this.message = message;
        this.dataobject = tags;
    }

    public TagsResponse() {
    }
}
