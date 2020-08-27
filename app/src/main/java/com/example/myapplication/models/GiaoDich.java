package com.example.myapplication.models;

public class GiaoDich {
    private int id;
    private String MaCT;
    private String MaGD;
    private String TenGD;
    public GiaoDich() {
    }
    public GiaoDich(String maCT, String maGD, String tenGD) {
        this.MaCT = maCT;
        this.MaGD = maGD;
        this.TenGD = tenGD;
    }

    public GiaoDich(int id, String maCT, String maGD, String tenGD) {
        this.id = id;
        this.MaCT = maCT;
        this.MaGD = maGD;
        this.TenGD = tenGD;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaCT() {
        return MaCT;
    }

    public void setMaCT(String maCT) {
        MaCT = maCT;
    }

    public String getMaGD() {
        return MaGD;
    }

    public void setMaGD(String maGD) {
        MaGD = maGD;
    }

    public String getTenGD() {
        return TenGD;
    }

    public void setTenGD(String tenGD) {
        TenGD = tenGD;
    }


}
