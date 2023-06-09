package com.example.medical_platform_android.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostsAndUser {
    private Integer postsId;

    private Integer userid;

    private String username;

    private String name;

    private String gender;

    private String birthdate;

    private String medicationHistory;

    private String introduction;

    private String headImage;

//    posts


    private String content;

    private String publishTime;

    private boolean followState;

    private String a;

    private String b;

    private int likeCount;

    private int commentCount;

    private int userRole;

    public boolean isFollowState() {
        return followState;
    }

    public void setFollowState(boolean followState) {
        this.followState = followState;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    private boolean likeState;

    public PostsAndUser() {
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMedicationHistory() {
        return medicationHistory;
    }

    public void setMedicationHistory(String medicationHistory) {
        this.medicationHistory = medicationHistory;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public boolean isFollow() {
        return followState;
    }

    public void setFollow(boolean follow) {
        this.followState = follow;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isLikeState() {
        return likeState;
    }

    public void setLikeState(boolean likeState) {
        this.likeState = likeState;
    }

    public PostsAndUser(Integer postsId, Integer userid, String username, String name, String gender, String birthdate, String medicationHistory, String introduction, String headImage, String content, String publishTime, boolean follow, String a, String b, int likeCount, int commentCount, boolean likeState) {
        this.postsId = postsId;
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.medicationHistory = medicationHistory;
        this.introduction = introduction;
        this.headImage = headImage;
        this.content = content;
        this.publishTime = publishTime;
        this.followState = follow;
        this.a = a;
        this.b = b;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.likeState = likeState;
    }
}
