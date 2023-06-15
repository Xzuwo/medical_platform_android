package com.example.medical_platform_android.entity;

import java.io.Serializable;

public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String a;

    private String b;

    private String c;

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Tags(Integer id, String name, String a, String b, String c) {
        this.id = id;
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Tags() {
    }
}
