package com.tdc.vlxdonline.Model;

public class ThongTinChu{
    private int ID;
    private String ten, SDT, email;
    private String diaChi, STK;

    public ThongTinChu() {
    }

    public ThongTinChu(int ID, String ten, String SDT, String email, String diaChi, String STK) {
        this.ID = ID;
        this.ten = ten;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
        this.STK = STK;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSTK() {
        return STK;
    }

    public void setSTK(String STK) {
        this.STK = STK;
    }
}
