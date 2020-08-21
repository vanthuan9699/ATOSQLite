package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tv_ct, tv_magd, tv_soct;
    EditText edt_ghichu;
    final Context context = this;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initData();
        inserRow();
        loadData();
    }

    private void init() {
        tv_ct = findViewById(R.id.ct);
        tv_magd = findViewById(R.id.gd);
        tv_soct = findViewById(R.id.soct);

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
        String sql = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXA', 'Phiếu nhập kho')";
        db.execSQL(sql);
        String sql1 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('TB1', 2, 'Xuất nội bộ')";
        db.execSQL(sql1);
//        String sql1 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu xuất điều chuyển')";
//        db.execSQL(sql1);
//        String sql2 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXA', 'Phiếu nhập kho')";
//        db.execSQL(sql2);
//        String sql3 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PND', 'Phiếu nhập kho')";
//        db.execSQL(sql3);
//        String sql4 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu nhập điều chuyển')";
//        db.execSQL(sql4);
//        String sql = "DELETE FROM zcdmct_tudong";
//        db.execSQL(sql);
    }
    private void loadData() {
        String sql = "SELECT * FROM zcdmct_tudong";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            tv_ct.setText(cursor.getString(1));
        }
        String sql1 = "SELECT * FROM zcdmct_giaodich";
        Cursor cursor1 = db.rawQuery(sql1, null);
        while (cursor1.moveToNext()){
            tv_magd.setText(cursor1.getString(2));
        }
    }
}