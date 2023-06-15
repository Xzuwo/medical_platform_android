package com.example.medical_platform_android.entity;

import java.util.List;

public class DrugsListResponse {
    private int code;
    private String msg;
    private List<Drugs> drugsList;

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

    public List<Drugs> getDrugsList() {
        return drugsList;
    }

    public void setDrugsList(List<Drugs> drugsList) {
        this.drugsList = drugsList;
    }
}
