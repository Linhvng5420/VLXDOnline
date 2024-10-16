package com.tdc.vlxdonline.Model;

public class DaDuyetKhach {
    private int idKhach, iDChu, trangThai;

    public DaDuyetKhach(int idKhach, int iDChu, int trangThai) {
        this.idKhach = idKhach;
        this.iDChu = iDChu;
        this.trangThai = trangThai;
    }

    public DaDuyetKhach() {
    }

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public int getiDChu() {
        return iDChu;
    }

    public void setiDChu(int iDChu) {
        this.iDChu = iDChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
