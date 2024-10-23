package com.tdc.vlxdonline.Model;

public class TypeUser {
    private String id;
    private String ten;

    public TypeUser(String id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public TypeUser() {
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

    @Override
    public String toString() {
        return ten;
    }
}
