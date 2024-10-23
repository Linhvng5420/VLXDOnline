package com.tdc.vlxdonline.Model;

public class DonViDanhMuc_Model {
    public String images, tenDM,tenDV;

    public DonViDanhMuc_Model(String images, String tenDM, String tenDV) {
        this.images = images;
        this.tenDM = tenDM;
        this.tenDV = tenDV;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }
}
