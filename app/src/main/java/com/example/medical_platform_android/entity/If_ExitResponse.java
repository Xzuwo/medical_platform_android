package com.example.medical_platform_android.entity;

public class If_ExitResponse {
    public Users user;
    public int code;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public If_ExitResponse(Users user, int code) {
        this.user = user;
        this.code = code;
    }

    public If_ExitResponse() {
    }

    @Override
    public String toString() {
        return "If_ExitResponse{" +
                "user=" + user +
                ", code='" + code + '\'' +
                '}';
    }
}
