package com.tdc.vlxdonline.Model;

public class DonHang {
    private int id, idChu, idKhach, tongTien, trangThai, trangThaiTT, idTao, idGiao, phiTraGop;
    private String anh, tenKhach, sdt, diaChi, ngayTao;

    public DonHang(String anh, int id, int idChu, int idKhach, int tongTien, int trangThai, int trangThaiTT, int idTao, int idGiao, int phiTraGop, String tenKhach, String sdt, String diaChi, String ngayTao) {
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

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(int trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }

    public int getIdTao() {
        return idTao;
    }

    public void setIdTao(int idTao) {
        this.idTao = idTao;
    }

    public int getIdGiao() {
        return idGiao;
    }

    public void setIdGiao(int idGiao) {
        this.idGiao = idGiao;
    }

    public int getPhiTraGop() {
        return phiTraGop;
    }

    public void setPhiTraGop(int phiTraGop) {
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
