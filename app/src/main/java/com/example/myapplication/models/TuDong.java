package com.example.myapplication.models;

public class TuDong {
    private int id;
    private String MaCT;
    private String TenCT;
    public TuDong(){}
    public TuDong(String maCT, String tenCT){
        this.MaCT = maCT;
        this.TenCT = tenCT;
    }
    public TuDong(int id, String maCT, String tenCT){
        this.id = id;
        this.MaCT = maCT;
        this.TenCT = tenCT;
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

    public String getTenCT() {
        return TenCT;
    }

    public void setTenCT(String tenCT) {
        TenCT = tenCT;
    }

}
