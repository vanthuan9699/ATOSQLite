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
    final Context context = this;
    private SQLiteDatabase db;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        init();
        initData();
        inserRow();
        loadData();
    }
    private void init() {
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
//        String sql = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXA', 'Phiếu nhập kho')";
//        db.execSQL(sql);
//        String sql2 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu xuất điều chuyển')";
//        db.execSQL(sql2);
//        String sql3 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('TBL', 'Thông báo nhập kho')";
//        db.execSQL(sql3);
//                String sql4 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('TBX', 'Thông báo')";
//        db.execSQL(sql4);
//        String sql1 = "INSERT INTO zcdmct_giaodich(Ma_ct, Ma_gd, Ten_gd) VALUES ('TB1', 2, 'Xuất nội bộ')";
//        db.execSQL(sql1);
//        String sql1 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu xuất điều chuyển')";
//        db.execSQL(sql1);
//        String sql2 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXA', 'Phiếu nhập kho')";
//        db.execSQL(sql2);
//        String sql3 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PND', 'Phiếu nhập kho')";
////        db.execSQL(sql3);
//        String sql4 = "INSERT INTO zcdmct_tudong(Ma_ct, Ten_ct) VALUES ('PXB', 'Phiếu nhập điều chuyển')";
//        db.execSQL(sql4);
//        String sql = "DELETE FROM zcdmct_tudong";
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
        String sql2 = "select Ten_ct from zcdmct_tudong";
        Cursor cursor1 = db.rawQuery(sql2, null);
        while (cursor1.moveToNext()) {
            String chungtu = cursor1.getString(0);
            arrayList.add(chungtu);
            sp_ct.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
//        adapter.notifyDataSetChanged();
//        sp_mact.setAdapter(adapter);


//        sp_mact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String sql2 = "select Ten_ct from zcdmct_tudong";
//                Cursor cursor1 = db.rawQuery(sql2, null);
//                listmachungtu.clear();
//                while (cursor1.moveToNext()) {
//                    String machungtu = cursor1.getString(1);
//                    listmachungtu.add(machungtu);
//                    Log.d("AAA", listmachungtu.get(1));
//                }
//                adapter.notifyDataSetChanged();
//
////
////                String sql = "SELECT * FROM zcdmct_tudong WHERE Ten_ct = '" + listmachungtu.get(position) + "' ";
////                Cursor cursor = db.rawQuery(sql, null);
////                while (cursor.moveToNext()) {
////                    String tenchungtu = cursor.getString(1);
////                    Log.d("BBB", tenchungtu);
////                   // listmachungtu.add(tenchungtu);
////                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}