package com.example.medical_platform_android.entity;

import java.time.LocalDateTime;

public class Comments {
    private Integer id;

    private Integer userId;



    private Integer postId;

    private Integer parentId;

    private String content;


    private String createTime;

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



    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Comments(Integer id, Integer userId,  Integer postId, Integer parentId, String content, String createTime, String a, String b, String c) {
        this.id = id;
        this.userId = userId;

        this.postId = postId;
        this.parentId = parentId;
        this.content = content;
        this.createTime = createTime;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Comments() {
    }
}
