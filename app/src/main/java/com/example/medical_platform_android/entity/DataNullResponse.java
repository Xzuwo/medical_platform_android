package com.example.medical_platform_android.entity;

import java.io.Serializable;

public class DataNullResponse implements Serializable {
    private int code;
    private String message;

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

    public DataNullResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataNullResponse() {
    }
}
