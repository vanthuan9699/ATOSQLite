package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.models.Barcode;
import com.example.myapplication.models.GiaoDich;
import com.example.myapplication.models.TuDong;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    private final String Tag = "DatabaseManager";
    private static final String DATABASE_NAME = "Quanly.db";
    private static final String TABLE_NAME = "zcdmct_tudong";
    private static final String TABLE_NAME1 = "zcdmct_giaodich";
    private static final String TABLE_NAME2 = "zc_barcode_auto";
    private static final String ID = "id";
    private static final String MACT = "Ma_ct";
    private static final String TENCT = "Ten_ct";
    private static final String MAGD = "Ma_gd";
    private static final String TENGD = "Ten_gd";


    private static final String SOCT = "So_ct";
    private static final String Ngay = "Ngay_ct";
    private static final String MaVT = "Ma_vt";
    private static final String MAVITRI = "Ma_vi_tri";
    private static final String MALO = "Ma_lo";
    private static final String MAKHO = "Ma_kho";
    private static final String SOLUONG = "So_luong";
    private static final String STTPO = "Stt_rec_po";
    private static final String STTDN = "Stt_rec_dn";
    private static final String STTTH = "Stt_rec_th";
    private static final String STATUS = "Status";
    private static final String STATUS_FAST = "Status_fast";

    private final Context context;

    private String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key," +
            MACT + " CHAR(3), " +
            TENCT + " NVARCHAR(128))";

    private String sqlQuery1 = "CREATE TABLE " + TABLE_NAME1 + " (" +
            ID + " integer primary key," +
            MACT + " CHAR(3), " +
            MAGD + " CHAR(3)," +
            TENGD + " NVARCHAR(128))";
    private String sqlQuery2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
            ID + " integer primary key," +
            MACT + " CHAR(3), " +
            MAGD + " CHAR(3)," +
            SOCT + " CHAR(12)," +
            Ngay + " SMALLDATETIME, " +
            MaVT + " CHAR(16)," +
            MAVITRI + " CHAR(16)," +
            MALO + " CHAR(16)," +
            MAKHO + " CHAR(16)," +
            SOLUONG + " NUMERIC(16,2)," +
            STTPO + " CHAR(13)," +
            STTDN + " CHAR(13)," +
            STTTH + " CHAR(13)," +
            STATUS + " CHAR(1)," +
            STATUS_FAST + " CHAR(1))";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Log.d(Tag, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
        db.execSQL(sqlQuery1);
        db.execSQL(sqlQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addTuDong(TuDong tuDong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MACT, tuDong.getMaCT());
        values.put(TENCT, tuDong.getTenCT());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void addGiaoDich(GiaoDich giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MACT, giaoDich.getMaCT());
        values.put(MAGD, giaoDich.getMaGD());
        values.put(TENGD, giaoDich.getTenGD());
        db.insert(TABLE_NAME1, null, values);
        db.close();


    }

    public void addBarcode(Barcode barcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MACT, barcode.getMaCT());
        values.put(MAGD, barcode.getMaGD());
        values.put(SOCT, barcode.getSo_CT());
        values.put(Ngay, barcode.getNgay_CT());
        values.put(MaVT, barcode.getMa_VT());
        values.put(MAVITRI, barcode.getMa_vi_tri());
        values.put(MALO, barcode.getMa_lo());
        values.put(MAKHO, barcode.getMa_kho());
        values.put(SOLUONG, barcode.getSo_luong());
        values.put(STTPO, barcode.getStt_rec_po());
        values.put(STTDN, barcode.getStt_rec_dn());
        values.put(STTTH, barcode.getStt_rec_th());
        values.put(STATUS, barcode.getStatus());
        values.put(STATUS_FAST, barcode.getStatus_fast());
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }

    public List<Barcode> getAllBarcode() {
        List<Barcode> barcodeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Barcode barcode = new Barcode();
                barcode.setId(cursor.getInt(0));
                barcode.setMaCT(cursor.getString(1));
                barcode.setMaGD(cursor.getString(2));
                barcode.setSo_CT(cursor.getString(3));
                barcode.setNgay_CT(cursor.getString(4));
                barcode.setMa_VT(cursor.getString(5));
                barcode.setMa_vi_tri(cursor.getString(6));
                barcode.setMa_lo(cursor.getString(7));
                barcode.setMa_kho(cursor.getString(8));
                barcode.setSo_luong(cursor.getString(9));
                barcode.setStt_rec_po(cursor.getString(10));
                barcode.setStt_rec_dn(cursor.getString(11));
                barcode.setStt_rec_th(cursor.getString(12));
                barcode.setStatus(cursor.getString(13));
                barcode.setStatus_fast(cursor.getString(14));
                barcodeList.add(barcode);
            } while (cursor.moveToNext());
        }
        db.close();
        return barcodeList;
    }

    public List<TuDong> getAllTuDong() {
        List<TuDong> listTuDong = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TuDong tuDong = new TuDong();
                tuDong.setId(cursor.getInt(0));
                tuDong.setMaCT(cursor.getString(1));
                tuDong.setTenCT(cursor.getString(2));
                listTuDong.add(tuDong);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTuDong;
    }

    public List<GiaoDich> getAllGiaoDich() {
        List<GiaoDich> giaoDichList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setId(cursor.getInt(0));
                giaoDich.setMaCT(cursor.getString(1));
                giaoDich.setMaGD(cursor.getString(2));
                giaoDich.setTenGD(cursor.getString(3));
                giaoDichList.add(giaoDich);

            } while (cursor.moveToNext());
        }
        db.close();
        return giaoDichList;
    }


    public int updateTuDong(TuDong tuDong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MACT, tuDong.getMaCT());
        contentValues.put(TENCT, tuDong.getTenCT());
        return db.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(tuDong.getId())});
    }

    public int updateGiaoDich(GiaoDich giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MACT, giaoDich.getMaCT());
        contentValues.put(MAGD, giaoDich.getMaGD());
        contentValues.put(TENGD, giaoDich.getTenGD());
        return db.update(TABLE_NAME1, contentValues, ID + "=?", new String[]{String.valueOf(giaoDich.getId())});
    }

    public int deleteGiaoDich(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, ID + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteTuDong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }


}
