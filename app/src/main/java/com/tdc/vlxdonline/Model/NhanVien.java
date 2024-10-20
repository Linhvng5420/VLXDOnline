package com.tdc.vlxdonline.Model;

import java.io.Serializable;

// Lớp NhanVien implement giao diện Serializable để có thể truyền dữ liệu giữa các Activity hoặc Fragment
public class NhanVien implements Serializable {
    private int id;
    private int idChu;
    private String tenNV;
    private int chucVu;
    private String sdt;
    private String email;

    // Constructor rỗng (cần thiết cho Firebase)
    public NhanVien() {}
    
    // Constructor
    public NhanVien(int id, int idChu, String tenNV, int chucVu, String sdt, String email) {
        this.id = id;
        this.idChu = idChu;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.email = email;
    }

    // Getters and Setters
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

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
