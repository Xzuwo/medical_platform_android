package com.example.medical_platform_android.entity;

import java.io.Serializable;
import java.util.List;

public class UserListResponse {
    private int code;
    private String msg;
    private List<Users> usersList;

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

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
