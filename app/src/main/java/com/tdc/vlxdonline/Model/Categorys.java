package com.tdc.vlxdonline.Model;

public class Categorys {
    private String ten;
    private int id, anh;

    public Categorys() {
    }

    public Categorys(String ten, int id, int anh) {
        this.ten = ten;
        this.id = id;
        this.anh = anh;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
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
