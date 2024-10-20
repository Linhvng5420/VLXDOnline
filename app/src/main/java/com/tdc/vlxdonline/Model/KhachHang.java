package com.tdc.vlxdonline.Model;

public class KhachHang{
    private int ID;
    private String ten, SDT, email;
    private String avata, cccdMatTruoc, cccdMatSau;
    private String soCCCD, diaChi;

    public KhachHang() {
    }

    public KhachHang(int ID, String ten, String SDT, String email, String avata, String cccdMatTruoc, String cccdMatSau, String soCCCD, String diaChi) {
        this.ID = ID;
        this.ten = ten;
        this.SDT = SDT;
        this.email = email;
        this.avata = avata;
        this.cccdMatTruoc = cccdMatTruoc;
        this.cccdMatSau = cccdMatSau;
        this.soCCCD = soCCCD;
        this.diaChi = diaChi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public String getCccdMatTruoc() {
        return cccdMatTruoc;
    }

    public void setCccdMatTruoc(String cccdMatTruoc) {
        this.cccdMatTruoc = cccdMatTruoc;
    }

    public String getCccdMatSau() {
        return cccdMatSau;
    }

    public void setCccdMatSau(String cccdMatSau) {
        this.cccdMatSau = cccdMatSau;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
