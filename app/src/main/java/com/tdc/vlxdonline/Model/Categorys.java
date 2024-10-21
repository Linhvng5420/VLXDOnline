package com.tdc.vlxdonline.Model;

public class Categorys {
    private String ten, anh;
    private int id;

    public Categorys() {
    }

    public Categorys(String ten, int id, String anh) {
        this.ten = ten;
        this.id = id;
        this.anh = anh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
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
