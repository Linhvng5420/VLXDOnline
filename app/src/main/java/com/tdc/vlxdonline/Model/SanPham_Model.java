package com.tdc.vlxdonline.Model;

import android.text.Editable;

public class SanPham_Model {
    public String images, tenSP, giabanSP, soluong, daban;

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
