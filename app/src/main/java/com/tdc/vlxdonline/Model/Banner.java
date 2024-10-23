package com.tdc.vlxdonline.Model;

public class Banner {
    private String anh;
    private String id, idChu;

    public Banner(String anh, String id, String idChu) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdChu() {
        return idChu;
    }

    public void setIdChu(String idChu) {
        this.idChu = idChu;
    }
}
