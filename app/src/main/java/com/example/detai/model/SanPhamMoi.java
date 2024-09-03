package com.example.detai.model;

import java.io.Serializable;
import java.sql.Date;

public class SanPhamMoi implements Serializable {
    int id;
    int danhmuc_id;
    String tensp;
    int price;
    String hinhanh;

    String mota;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDanhmuc_id() {
        return danhmuc_id;
    }

    public void setDanhmuc_id(int danhmuc_id) {
        this.danhmuc_id = danhmuc_id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

  
}
