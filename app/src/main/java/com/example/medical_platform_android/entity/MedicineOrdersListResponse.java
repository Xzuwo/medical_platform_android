package com.example.medical_platform_android.entity;

import java.util.List;

public class MedicineOrdersListResponse {
    private int code;
    private String msg;
    private List<MedicineOrders> medicineOrdersList;

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

    public List<MedicineOrders> getMedicineOrdersList() {
        return medicineOrdersList;
    }

    public void setMedicineOrdersList(List<MedicineOrders> medicineOrdersList) {
        this.medicineOrdersList = medicineOrdersList;
    }
}
