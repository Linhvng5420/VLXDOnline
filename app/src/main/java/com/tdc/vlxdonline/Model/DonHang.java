package com.tdc.vlxdonline.Model;

public class DonHang {
    private String id, idChu, idKhach, tongTien, trangThai, trangThaiTT, idTao, idGiao, phiTraGop;
    private String anh, tenKhach, sdt, diaChi, ngayTao;

    public DonHang(String anh, String id, String idChu, String idKhach, String tongTien, String trangThai, String trangThaiTT, String idTao, String idGiao, String phiTraGop, String tenKhach, String sdt, String diaChi, String ngayTao) {
        this.id = id;
        this.idChu = idChu;
        this.idKhach = idKhach;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.trangThaiTT = trangThaiTT;
        this.idTao = idTao;
        this.idGiao = idGiao;
        this.phiTraGop = phiTraGop;
        this.tenKhach = tenKhach;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.anh = anh;
    }

    public DonHang() {
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
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

    public String getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(String idKhach) {
        this.idKhach = idKhach;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(String trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }

    public String getIdTao() {
        return idTao;
    }

    public void setIdTao(String idTao) {
        this.idTao = idTao;
    }

    public String getIdGiao() {
        return idGiao;
    }

    public void setIdGiao(String idGiao) {
        this.idGiao = idGiao;
    }

    public String getPhiTraGop() {
        return phiTraGop;
    }

    public void setPhiTraGop(String phiTraGop) {
        this.phiTraGop = phiTraGop;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
