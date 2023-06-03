package com.example.medical_platform_android.entity;

import java.io.Serializable;
import java.util.List;

public class DoctorsListResponse {
    private int code;
    private String msg;
    private List<Doctors> doctorsList;

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

    public List<Doctors> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(List<Doctors> doctorsList) {
        this.doctorsList = doctorsList;
    }

}
