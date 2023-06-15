package com.example.medical_platform_android.entity;

import java.util.List;

public class FindAllDrugsResponse {
    public  int code;
    public  String msg;
    public List<Drugs> DrugList;

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

    public List<Drugs> getDrugList() {
        return DrugList;
    }

    public void setDrugList(List<Drugs> drugList) {
        DrugList = drugList;
    }

    public FindAllDrugsResponse(int code, String msg, List<Drugs> drugList) {
        this.code = code;
        this.msg = msg;
        DrugList = drugList;
    }

    public FindAllDrugsResponse() {
    }

    @Override
    public String toString() {
        return "FindAllDrugsResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", DrugList=" + DrugList +
                '}';
    }
}
