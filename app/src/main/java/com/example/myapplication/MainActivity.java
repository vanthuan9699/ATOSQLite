package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_mact, tv_ct, tv_magd, tv_soct;
    EditText edt_ghichu, edt_soct;
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
    }
    private void init() {
        btn_save = findViewById(R.id.btn_save);
        edt_soct = findViewById(R.id.soct);
        sp_ct = findViewById(R.id.sp_ct);
        sp_magd = findViewById(R.id.sp_magd);
        sp_mact = findViewById(R.id.sp_mact);
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
//        String sql = "DELETE FROM zcdmct_giaodich";
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
    private void Save(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}