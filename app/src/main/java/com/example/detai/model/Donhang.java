package com.example.detai.model;

import java.util.Date;

public class Donhang {
    int id;
    String hoten;
    String sdt;
    String diachi;
    Date thoidiemdathang;
    String tongtien;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getThoidiemdathang() {
        return thoidiemdathang;
    }

    public void setThoidiemdathang(Date thoidiemdathang) {
        this.thoidiemdathang = thoidiemdathang;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
