package com.tdc.vlxdonline.Model;

public class Banner {
    private String anh;
    private int id, idChu;

    public Banner(String anh, int id, int idChu) {
        this.anh = anh;
        this.id = id;
        this.idChu = idChu;
    }

    public Banner() {
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

    public int getIdChu() {
        return idChu;
    }

    public void setIdChu(int idChu) {
        this.idChu = idChu;
    }
}
