package com.tdc.vlxdonline.Model;

public class ChucVu {
    private int id;
    private String ten;

    public ChucVu(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public ChucVu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
