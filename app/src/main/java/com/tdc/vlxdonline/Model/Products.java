package com.tdc.vlxdonline.Model;

public class Products {
    private String ten, moTa, donVi, anh;
    private String id, idChu, gia, danhMuc, tonKho, daBan = "0";
    private String soSao = "0";

    public Products(String ten, String donVi, String moTa, String idChu, String id, String anh, String gia, String soSao, String danhMuc, String tonKho, String daBan) {
        this.ten = ten;
        this.donVi = donVi;
        this.moTa = moTa;
        this.idChu = idChu;
        this.id = id;
        this.anh = anh;
        this.gia = gia;
        this.soSao = soSao;
        this.danhMuc = danhMuc;
        this.tonKho = tonKho;
        this.daBan = daBan;
    }

    public Products() {
    }

    public String getIdChu() {
        return idChu;
    }

    public void setIdChu(String idChu) {
        this.idChu = idChu;
    }

    public String getSoSao() {
        return soSao;
    }

    public void setSoSao(String soSao) {
        this.soSao = soSao;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getTonKho() {
        return tonKho;
    }

    public void setTonKho(String tonKho) {
        this.tonKho = tonKho;
    }

    public String getDaBan() {
        return daBan;
    }

    public void setDaBan(String daBan) {
        this.daBan = daBan;
    }
}
