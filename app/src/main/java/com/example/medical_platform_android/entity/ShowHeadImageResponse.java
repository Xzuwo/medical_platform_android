package com.example.medical_platform_android.entity;

public class ShowHeadImageResponse {
    private int code;
    private Users user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ShowHeadImageResponse(int code, Users user) {
        this.code = code;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShowHeadImageResponse{" +
                "code=" + code +
                ", user=" + user +
                '}';
    }

    public ShowHeadImageResponse() {
    }
}
