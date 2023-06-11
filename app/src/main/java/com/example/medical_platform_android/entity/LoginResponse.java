package com.example.medical_platform_android.entity;

public class LoginResponse {

    private int code;
    private String msg;
    private String token;
    private Integer id;
    private String password;
    private String name;
    private String birthdate;
    private String gender;
    private String headImage;
    private String medication_history;

    public String getMedication_history() {
        return medication_history;
    }

    public void setMedication_history(String medication_history) {
        this.medication_history = medication_history;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    private String username;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public LoginResponse(int code, String msg, String token, Integer id, String password, String name, String birthdate, String gender, String headImage, String medication_history, String username) {
        this.code = code;
        this.msg = msg;
        this.token = token;
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.headImage = headImage;
        this.medication_history = medication_history;
        this.username = username;
    }

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                ", headImage='" + headImage + '\'' +
                ", medication_history='" + medication_history + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
