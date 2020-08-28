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

import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_soluong, edt_trangthai, edt_ghichu, edt_soct, edtNgay, edtMavt, edtMavitri, edtMalo, edtKhohang, edtPhieuPO, edtPhieuxh, edtPhieuth;
    Spinner sp_mact, sp_magd;
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
//        String sql6 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('PXA', 2, 'Xuất nội bộ')";
//        db.execSQL(sql6);
//        String sql7 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('PXA', 'XB', 'Xuất bán')";
//        db.execSQL(sql7);
//        String sql = "DELETE FROM zc_barcode_auto";
//        db.execSQL(sql);
    }

    private void loadData() {
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

                String sql = "INSERT INTO zc_barcode_auto (Ma_ct, " +
                        "Ma_gd, So_ct, Ngay_ct, Ma_vt, Ma_vi_tri, " +
                        "Ma_lo, Ma_kho, So_luong, Stt_rec_po, " +
                        "Stt_rec_dn, Stt_rec_th,Status, Status_fast)" +
                        " VALUES ('" + ma_ct + "', " +
                        "'" + ma_gd + "', " +
                        "'" + so_ct + "', " +
                        "'" + ngay + "', " +
                        "'" + mavatu + "', " +
                        "'" + mavitri + "'," +
                        "'" + malo + "', " +
                        "'" + makho + "'  ," +
                        "'" + soluong + "', " +
                        "'" + po + "', " +
                        "'" + dn + "', " +
                        "'" + th + "', " +
                        "'" + trangthai + "', " +
                        "'" + status + "')";
                db.execSQL(sql);
                Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                edt_soct.setText("");
                edtNgay.setText("");
                edtMavt.setText("");
                edtMavitri.setText("");
                edtMalo.setText("");
                edtKhohang.setText("");
                edt_soluong.setText("");
                edtPhieuPO.setText("");
                edtPhieuxh.setText("");
                edtPhieuth.setText("");
                edt_trangthai.setText("");
                edt_ghichu.setText("");
            }
        });
    }
}