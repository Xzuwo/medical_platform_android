package com.example.medical_platform_android.entity;

public class Health_Status_Response {
    public HealthStatus healthStatus;
    public int code;
    public String msg;

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

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

    public Health_Status_Response(HealthStatus healthStatus, int code, String msg) {
        this.healthStatus = healthStatus;
        this.code = code;
        this.msg = msg;
    }

    public Health_Status_Response() {
    }

    @Override
    public String toString() {
        return "Health_Status_Response{" +
                "healthStatus=" + healthStatus +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
