package com.example.medical_platform_android.entity;

public class FindRoleNameResponse {
    int code;
    String msg;
    String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public FindRoleNameResponse(int code, String msg, String roleName) {
        this.code = code;
        this.msg = msg;
        this.roleName = roleName;
    }

    public FindRoleNameResponse() {
    }

    @Override
    public String toString() {
        return "findRoleNameResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
