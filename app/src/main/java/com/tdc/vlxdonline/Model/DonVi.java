package com.tdc.vlxdonline.Model;

public class DonVi {
    private String ten;
    private String id;

    public DonVi() {
    }

    public DonVi(String ten, String id) {
        this.ten = ten;
        this.id = id;
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
