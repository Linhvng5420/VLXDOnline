package com.tdc.vlxdonline.Model;

import java.io.Serializable;

// Lớp NhanVien implement giao diện Serializable để có thể truyền dữ liệu giữa các Activity hoặc Fragment
public class NhanVien implements Serializable {
    private String ID;
    private String IDChu;
    private String TenNV;
    private String ChucVu;
    private String SDT;
    private String Email;

    // Constructor rỗng (cần thiết cho Firebase)
    public NhanVien() {}

    public NhanVien(String ID, String IDChu, String tenNV, String chucVu, String SDT, String email) {
        this.ID = ID;
        this.IDChu = IDChu;
        TenNV = tenNV;
        ChucVu = chucVu;
        this.SDT = SDT;
        Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDChu() {
        return IDChu;
    }

    public void setIDChu(String IDChu) {
        this.IDChu = IDChu;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
