package com.example.medical_platform_android.entity;

import java.io.Serializable;

public class UserRoles implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private String roleName;

    private String a;

    private String b;

    private String c;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public UserRoles(Integer id, Integer userId, Integer roleId, String roleName, String a, String b, String c) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public UserRoles() {
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
