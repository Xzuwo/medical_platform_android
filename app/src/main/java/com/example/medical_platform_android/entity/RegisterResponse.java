package com.example.medical_platform_android.entity;

public class RegisterResponse {
    private int code;
    private String msg;

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

    public RegisterResponse() {
    }

    public RegisterResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
