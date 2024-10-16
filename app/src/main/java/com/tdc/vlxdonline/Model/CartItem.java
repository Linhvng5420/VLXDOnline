package com.tdc.vlxdonline.Model;

public class CartItem {
    private int idKhach, idSanPham, anh, gia, soLuong;
    private String tenSP, moTa;
    private boolean selected = false;

    public CartItem(int idKhach, int idSanPham, int anh, int gia, int soLuong, String tenSP, String moTa, boolean selected) {
        this.idKhach = idKhach;
        this.idSanPham = idSanPham;
        this.anh = anh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public CartItem() {
    }

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
