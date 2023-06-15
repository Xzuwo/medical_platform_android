package com.example.medical_platform_android.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class HealthStatus implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer heartRate;

    private Integer bloodOxygen;

    private String sleepStatus;

    private String abnormalIndicator;

    private Date recordTime;

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

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(Integer bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public String getSleepStatus() {
        return sleepStatus;
    }

    public void setSleepStatus(String sleepStatus) {
        this.sleepStatus = sleepStatus;
    }

    public String getAbnormalIndicator() {
        return abnormalIndicator;
    }

    public void setAbnormalIndicator(String abnormalIndicator) {
        this.abnormalIndicator = abnormalIndicator;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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

    public HealthStatus(Integer id, Integer userId, Integer heartRate, Integer bloodOxygen, String sleepStatus, String abnormalIndicator, Date recordTime, String a, String b, String c) {
        this.id = id;
        this.userId = userId;
        this.heartRate = heartRate;
        this.bloodOxygen = bloodOxygen;
        this.sleepStatus = sleepStatus;
        this.abnormalIndicator = abnormalIndicator;
        this.recordTime = recordTime;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public HealthStatus() {
    }

    @Override
    public String toString() {
        return "HealthStatus{" +
                "id=" + id +
                ", userId=" + userId +
                ", heartRate=" + heartRate +
                ", bloodOxygen=" + bloodOxygen +
                ", sleepStatus='" + sleepStatus + '\'' +
                ", abnormalIndicator='" + abnormalIndicator + '\'' +
                ", recordTime=" + recordTime +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
