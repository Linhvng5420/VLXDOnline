package com.tdc.vlxdonline.Model;

import java.io.Serializable;

// Lớp NhanVien implement giao diện Serializable để có thể truyền dữ liệu giữa các Activity hoặc Fragment
public class NhanVien implements Serializable {
    private int ID;
    private int IDChu;
    private String TenNV;
    private int ChucVu;
    private String SDT;
    private String Email;

    // Constructor rỗng (cần thiết cho Firebase)
    public NhanVien() {}

    public NhanVien(int ID, int IDChu, String TenNV, int ChucVu, String SDT, String Email) {
        this.ID = ID;
        this.IDChu = IDChu;
        this.TenNV = TenNV;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.Email = Email;
    }

    // Getters and Setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDChu() {
        return IDChu;
    }

    public void setIDChu(int IDChu) {
        this.IDChu = IDChu;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int chucVu) {
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
