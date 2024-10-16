package com.tdc.vlxdonline.Model;

public class DonNhap {
    private int id, idChu, idTao, tongTien;
    private String ngayTao;

    public DonNhap(int id, int idChu, int idTao, int tongTien, String ngayTao) {
        this.id = id;
        this.idChu = idChu;
        this.idTao = idTao;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }

    public DonNhap() {
    }

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

    public int getIdTao() {
        return idTao;
    }

    public void setIdTao(int idTao) {
        this.idTao = idTao;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
