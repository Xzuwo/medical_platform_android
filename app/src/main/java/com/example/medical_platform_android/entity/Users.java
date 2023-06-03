package com.example.medical_platform_android.entity;

import com.google.android.material.timepicker.TimeFormat;
//import com.google.gson.annotations.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Users implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String birthdate;
    private String medicationHistory;
    private String geneticDiseaseHistory;
    private String headImage;
    private String a;
    private String b;
    private String c;

    public Users() {
    }

    public Users(Integer id, String username, String password, String name, String gender, String birthdate, String medicationHistory, String geneticDiseaseHistory, String headImage, String a, String b, String c) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.medicationHistory = medicationHistory;
        this.geneticDiseaseHistory = geneticDiseaseHistory;
        this.headImage = headImage;
        this.a = a;
        this.b = b;
        this.c = c;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGeneticDiseaseHistory() {
        return geneticDiseaseHistory;
    }

    public void setGeneticDiseaseHistory(String geneticDiseaseHistory) {
        this.geneticDiseaseHistory = geneticDiseaseHistory;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medicationHistory='" + medicationHistory + '\'' +
                ", geneticDiseaseHistory='" + geneticDiseaseHistory + '\'' +
                ", headImage='" + headImage + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }
}
