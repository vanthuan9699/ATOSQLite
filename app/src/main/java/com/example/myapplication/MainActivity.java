package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText edt_soluong, edt_trangthai, edt_ghichu, edt_soct, edtNgay, edtMavt, edtMavitri, edtMalo, edtKhohang, edtPhieuPO, edtPhieuxh, edtPhieuth;
    Spinner sp_mact, sp_ct, sp_magd;
    Button btn_save;
    final Context context = this;
    private SQLiteDatabase db;
    ArrayList<String> arrayList, arrayList2, arrayList3;
    ArrayAdapter adapter, adapter2, adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter.notifyDataSetChanged();

        arrayList2 = new ArrayList<>();
        adapter2 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList2);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter2.notifyDataSetChanged();

        arrayList3 = new ArrayList<>();
        adapter3 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList3);
        adapter3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter3.notifyDataSetChanged();
        init();
        initData();
        inserRow();
        loadData();
        Save();
    }

    private void init() {

        btn_save = findViewById(R.id.btn_save);
        edt_soct = findViewById(R.id.soct);
        sp_ct = findViewById(R.id.sp_ct);
        sp_magd = findViewById(R.id.sp_magd);
        sp_mact = findViewById(R.id.sp_mact);
        edtNgay = findViewById(R.id.ngay);
        edtMavt = findViewById(R.id.mavattu);
        edtMavitri = findViewById(R.id.mavitri);
        edtMalo = findViewById(R.id.malo);
        edtKhohang = findViewById(R.id.khohang);
        edtPhieuPO = findViewById(R.id.phieupo);
        edtPhieuxh = findViewById(R.id.ycxh);
        edtPhieuth = findViewById(R.id.ycth);
        edt_trangthai = findViewById(R.id.edt_trangthai);
        edt_ghichu = findViewById(R.id.edt_status);
        edt_soluong = findViewById(R.id.soluong);

    }

    private void initData() {
        db = openOrCreateDatabase("QuanLy.db", MODE_PRIVATE, null);
        String sql1 = "CREATE TABLE IF NOT EXISTS zcdmct_tudong(Ma_ct char(3)" +
                ", Ten_ct nvarchar(128))";
        db.execSQL(sql1);
        String sql2 = "CREATE TABLE IF NOT EXISTS zcdmct_giaodich(Ma_ct char(3)" +
                ", Ma_gd char(3)" +
                ", Ten_gd nvarchar(128))";
        db.execSQL(sql2);
        String sql3 = "CREATE TABLE IF NOT EXISTS zc_barcode_auto(Ma_ct char(3)" +
                ", Ma_gd char(2)," +
                " So_ct char(12)," +
                " Ngay_ct smalldatetime," +
                " Ma_vt char(16)," +
                " Ma_vi_tri char(16)," +
                " Ma_lo char(16)," +
                " Ma_kho char(16)," +
                " So_luong numeric(16,2)," +
                "Stt_rec_po char(13)," +
                "Stt_rec_dn char(13)," +
                "Stt_rec_th char(13)," +
                "Status char(1)," +
                "Status_fast char(1))";
        db.execSQL(sql3);
    }

    private void inserRow() {
//        String sql = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('TBL', 'Thông báo nhập kho')";
//        db.execSQL(sql);
//        String sql1 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu xuất điều chuyển')";
//        db.execSQL(sql1);
//        String sql3 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXA', 'Phiếu xuất kho')";
//        db.execSQL(sql3);
//        String sql4 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PND', 'Phiếu nhập kho')";
//        db.execSQL(sql4);
//        String sql5 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PNF', 'Phiếu nhập điều chuyển')";
//        db.execSQL(sql5);
//
//        String sql6 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('PXA', 2, 'Xuất nội bộ')";
//        db.execSQL(sql6);
//        String sql7 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('PXA', 'XB', 'Xuất bán')";
//        db.execSQL(sql7);
//        String sql = "DELETE FROM zc_barcode_auto";
//        db.execSQL(sql);


    }

    private void loadData() {
//        String sql = "SELECT * FROM zcdmct_tudong";
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            edt_soct.setText(cursor.getString(1));
//        }
//        String sql1 = "SELECT * FROM zcdmct_giaodich";
//        Cursor cursor1 = db.rawQuery(sql1, null);
//        while (cursor1.moveToNext()) {
//            tv_magd.setText(cursor1.getString(2));
//        }
        String sql = "select Ten_ct from zcdmct_tudong";
        Cursor cursor1 = db.rawQuery(sql, null);
        while (cursor1.moveToNext()) {
            String chungtu = cursor1.getString(0);
            arrayList.add(chungtu);
            sp_ct.setAdapter(adapter);
        }

        String sql1 = "select Ma_ct from zcdmct_tudong";
        Cursor cursor = db.rawQuery(sql1, null);
        while (cursor.moveToNext()) {
            String machungtu = cursor.getString(0);
            arrayList2.add(machungtu);
            sp_mact.setAdapter(adapter2);
        }

        String sql2 = "select Ma_gd from zcdmct_giaodich";
        Cursor cursor2 = db.rawQuery(sql2, null);
        while (cursor2.moveToNext()) {
            String magd = cursor2.getString(0);
            arrayList3.add(magd);
            sp_magd.setAdapter(adapter3);
        }
    }

    private void Save() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma_ct = sp_mact.getSelectedItem().toString();
                String ma_gd = sp_magd.getSelectedItem().toString();
                String so_ct = edt_soct.getText().toString();
                String ngay = edtNgay.getText().toString();
                String mavatu = edtMavt.getText().toString();
                String mavitri = edtMavitri.getText().toString();
                String malo = edtMalo.getText().toString();
                String makho = edtKhohang.getText().toString();
                String soluong = edt_soluong.getText().toString();
                String po = edtPhieuPO.getText().toString();
                String dn = edtPhieuxh.getText().toString();
                String th = edtPhieuth.getText().toString();
                String trangthai = edt_trangthai.getText().toString();
                String status = edt_ghichu.getText().toString();
                String sql = "INSERT INTO zc_barcode_auto (Ma_ct, Ma_gd, So_ct, Ngay_ct, Ma_vt, Ma_vi_tri, Ma_lo, Ma_kho, So_luong,Stt_rec_po, Stt_rec_dn, Stt_rec_th,Status, Status_fast)" +
                        " VALUES ('" + ma_ct + "', " +
                        "'" + ma_gd + "', " +
                        "'" + so_ct + "', " +
                        "'" + ngay + "', " +
                        "'" + mavatu + "', " +
                        "'" + mavitri + "'," +
                        " '" + malo + "', " +
                        "'" + makho + "'  ," +
                        "'" + soluong + "', " +
                        "'" + po + "', " +
                        "'" + dn + "', " +
                        "'" + th + "', " +
                        "'" + trangthai + "', " +
                        "'" + status + "')";
                db.execSQL(sql);
                Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
//                String sql = "select * from zc_barcode_auto";
//                Cursor cursor = db.rawQuery(sql, null);
//                while (!cursor.isAfterLast()) {
//                    String soct = cursor.getString(0);
//                    String ngay = cursor.getString(1);
//                    String mavt = cursor.getString(2);
//                    String mavtri = cursor.getString(3);
//                    String malo = cursor.getString(4);
//                    String khohang = cursor.getString(5);
//                    String phieupo = cursor.getString(6);
//                    String phieuxh = cursor.getString(7);
//                    String phieuth = cursor.getString(8);
//
//                    SQLdata u = new SQLdata();
//                    u.setSo_CT(soct);
//                    u.setNgay_CT(ngay);
//                    u.setMa_VT(mavt);
//                    u.setMa_vi_tri(mavtri);
//                    u.setMa_lo(malo);
//                    u.setMa_kho(khohang);
//                    u.setStt_rec_po(phieupo);
//                    u.setStt_rec_dn(phieuxh);
//                    u.setStt_rec_th(phieuth);
//                }

            }
        });
    }
}