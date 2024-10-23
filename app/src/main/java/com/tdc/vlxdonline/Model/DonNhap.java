package com.tdc.vlxdonline.Model;

public class DonNhap {
    private String id, idChu, idTao, tongTien;
    private String ngayTao;

    public DonNhap(String id, String idChu, String idTao, String tongTien, String ngayTao) {
        this.id = id;
        this.idChu = idChu;
        this.idTao = idTao;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
    }

    public DonNhap() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdChu() {
        return idChu;
    }

    public void setIdChu(String idChu) {
        this.idChu = idChu;
    }

    public String getIdTao() {
        return idTao;
    }

    public void setIdTao(String idTao) {
        this.idTao = idTao;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
