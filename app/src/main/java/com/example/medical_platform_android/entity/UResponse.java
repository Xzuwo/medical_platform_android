package com.example.medical_platform_android.entity;

public class UResponse {
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

    public UResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public UResponse() {
    }

    public static int code;
    public static String message;

}
