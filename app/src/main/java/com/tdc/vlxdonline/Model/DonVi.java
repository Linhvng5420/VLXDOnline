package com.tdc.vlxdonline.Model;

public class DonVi {
    private String ten;
    private int id;

    public DonVi() {
    }

    public DonVi(String ten, int id) {
        this.ten = ten;
        this.id = id;
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
