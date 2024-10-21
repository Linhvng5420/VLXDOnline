package com.tdc.vlxdonline.Model;

public class Products {
    private String ten, moTa, donVi, anh;
    private int id, idChu, gia, danhMuc, tonKho, daBan = 0;
    private double soSao = 0;

    public Products(String ten, String donVi, String moTa, int idChu, int id, String anh, int gia, double soSao, int danhMuc, int tonKho, int daBan) {
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

    public int getIdChu() {
        return idChu;
    }

    public void setIdChu(int idChu) {
        this.idChu = idChu;
    }

    public double getSoSao() {
        return soSao;
    }

    public void setSoSao(double soSao) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(int danhMuc) {
        this.danhMuc = danhMuc;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public int getDaBan() {
        return daBan;
    }

    public void setDaBan(int daBan) {
        this.daBan = daBan;
    }
}
