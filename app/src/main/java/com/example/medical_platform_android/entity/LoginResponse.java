package com.example.medical_platform_android.entity;

public class LoginResponse {
    private int code;
    private String msg;
    private String token;
    private Integer id;

    private String username;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginResponse(int code, String msg, String token, Integer id, String username) {
        this.code = code;
        this.msg = msg;
        this.token = token;
        this.id = id;
        this.username = username;
    }

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
