package com.example.medical_platform_android.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MedicineOrders implements Serializable {

    private Integer id;

    private Integer userId;

    private Integer drugsId;

    private Integer quantity;

    private String status;

    private String username;

    private String drugsname;

    private String drugsManufacturer;

    private double drugsPrice;

    private String drugsDescription;

    private String createTime;

    public MedicineOrders() {
    }

    public MedicineOrders(Integer id, Integer userId, Integer drugsId, Integer quantity, String status, String username, String drugsname, String drugsManufacturer, double drugsPrice, String drugsDescription, String createTime) {
        this.id = id;
        this.userId = userId;
        this.drugsId = drugsId;
        this.quantity = quantity;
        this.status = status;
        this.username = username;
        this.drugsname = drugsname;
        this.drugsManufacturer = drugsManufacturer;
        this.drugsPrice = drugsPrice;
        this.drugsDescription = drugsDescription;
        this.createTime = createTime;
    }

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

    public Integer getDrugsId() {
        return drugsId;
    }

    public void setDrugsId(Integer drugsId) {
        this.drugsId = drugsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDrugsname() {
        return drugsname;
    }

    public void setDrugsname(String drugsname) {
        this.drugsname = drugsname;
    }

    public String getDrugsManufacturer() {
        return drugsManufacturer;
    }

    public void setDrugsManufacturer(String drugsManufacturer) {
        this.drugsManufacturer = drugsManufacturer;
    }

    public double getDrugsPrice() {
        return drugsPrice;
    }

    public void setDrugsPrice(double drugsPrice) {
        this.drugsPrice = drugsPrice;
    }

    public String getDrugsDescription() {
        return drugsDescription;
    }

    public void setDrugsDescription(String drugsDescription) {
        this.drugsDescription = drugsDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MedicineOrders{" +
                "id=" + id +
                ", userId=" + userId +
                ", drugsId=" + drugsId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", drugsname='" + drugsname + '\'' +
                ", drugsManufacturer='" + drugsManufacturer + '\'' +
                ", drugsPrice=" + drugsPrice +
                ", drugsDescription='" + drugsDescription + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
