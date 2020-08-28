package com.example.myapplication.models;

import java.util.Date;

public class Barcode {

    private int id;
    private String MaCT;
    private String MaGD;
    private String So_CT;
    private String Ngay_CT;
    private String Ma_VT;
    private String Ma_vi_tri;
    private String Ma_lo;
    private String Ma_kho;
    private String So_luong;
    private String Stt_rec_po;
    private String Stt_rec_dn;
    private String Stt_rec_th;
    private String Status;
    private String Status_fast;

    public Barcode(){}

    public Barcode(String maCT, String maGD, String so_CT, String ngay_CT, String ma_VT, String ma_vi_tri, String ma_lo, String ma_kho, String so_luong, String stt_rec_po, String stt_rec_dn, String stt_rec_th, String status, String status_fast) {
        MaCT = maCT;
        MaGD = maGD;
        So_CT = so_CT;
        Ngay_CT = ngay_CT;
        Ma_VT = ma_VT;
        Ma_vi_tri = ma_vi_tri;
        Ma_lo = ma_lo;
        Ma_kho = ma_kho;
        So_luong = so_luong;
        Stt_rec_po = stt_rec_po;
        Stt_rec_dn = stt_rec_dn;
        Stt_rec_th = stt_rec_th;
        Status = status;
        Status_fast = status_fast;
    }
    public Barcode(int id, String maCT, String maGD, String so_CT, String ngay_CT, String ma_VT, String ma_vi_tri, String ma_lo, String ma_kho, String so_luong, String stt_rec_po, String stt_rec_dn, String stt_rec_th, String status, String status_fast) {
        this.id = id;
        MaCT = maCT;
        MaGD = maGD;
        So_CT = so_CT;
        Ngay_CT = ngay_CT;
        Ma_VT = ma_VT;
        Ma_vi_tri = ma_vi_tri;
        Ma_lo = ma_lo;
        Ma_kho = ma_kho;
        So_luong = so_luong;
        Stt_rec_po = stt_rec_po;
        Stt_rec_dn = stt_rec_dn;
        Stt_rec_th = stt_rec_th;
        Status = status;
        Status_fast = status_fast;
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

    public String getSo_CT() {
        return So_CT;
    }

    public void setSo_CT(String so_CT) {
        So_CT = so_CT;
    }

    public String getNgay_CT() {
        return Ngay_CT;
    }

    public void setNgay_CT(String ngay_CT) {
        Ngay_CT = ngay_CT;
    }

    public String getMa_VT() {
        return Ma_VT;
    }

    public void setMa_VT(String ma_VT) {
        Ma_VT = ma_VT;
    }

    public String getMa_vi_tri() {
        return Ma_vi_tri;
    }

    public void setMa_vi_tri(String ma_vi_tri) {
        Ma_vi_tri = ma_vi_tri;
    }

    public String getMa_lo() {
        return Ma_lo;
    }

    public void setMa_lo(String ma_lo) {
        Ma_lo = ma_lo;
    }

    public String getMa_kho() {
        return Ma_kho;
    }

    public void setMa_kho(String ma_kho) {
        Ma_kho = ma_kho;
    }

    public String getSo_luong() {
        return So_luong;
    }

    public void setSo_luong(String so_luong) {
        So_luong = so_luong;
    }

    public String getStt_rec_po() {
        return Stt_rec_po;
    }

    public void setStt_rec_po(String stt_rec_po) {
        Stt_rec_po = stt_rec_po;
    }

    public String getStt_rec_dn() {
        return Stt_rec_dn;
    }

    public void setStt_rec_dn(String stt_rec_dn) {
        Stt_rec_dn = stt_rec_dn;
    }

    public String getStt_rec_th() {
        return Stt_rec_th;
    }

    public void setStt_rec_th(String stt_rec_th) {
        Stt_rec_th = stt_rec_th;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus_fast() {
        return Status_fast;
    }

    public void setStatus_fast(String status_fast) {
        Status_fast = status_fast;
    }


}
