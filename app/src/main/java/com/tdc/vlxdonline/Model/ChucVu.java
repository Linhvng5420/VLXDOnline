package com.tdc.vlxdonline.Model;

public class ChucVu {
    private String id;
    private String ten;

    public ChucVu(String id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public ChucVu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
