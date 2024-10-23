package com.tdc.vlxdonline.Model;

public class AnhSanPham {
    private String anh;
    private String id, idSanPham;

    public AnhSanPham() {
    }

    public AnhSanPham(String anh, String id, String idSanPham) {
        this.idSanPham = idSanPham;
        this.id = id;
        this.anh = anh;
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

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }
}
