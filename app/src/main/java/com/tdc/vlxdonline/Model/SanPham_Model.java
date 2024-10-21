package com.tdc.vlxdonline.Model;

import android.text.Editable;

public class SanPham_Model {
    public String images, tenSP, giabanSP, soluong, daban;
public String moTa,donVi,danhMuc;

    public SanPham_Model(String danhMuc, String donVi, String moTa) {
        this.danhMuc = danhMuc;
        this.donVi = donVi;
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public SanPham_Model() {
    }

    public SanPham_Model(String images, String tenSP, String giabanSP, String soluong, String daban) {
        this.images = images;
        this.tenSP = tenSP;
        this.giabanSP = giabanSP;
        this.soluong = soluong;
        this.daban = daban;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getGiabanSP() {
        return giabanSP;
    }

    public void setGiabanSP(String giabanSP) {
        this.giabanSP = giabanSP;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getDaban() {
        return daban;
    }

    public void setDaban(String daban) {
        this.daban = daban;
    }
}
