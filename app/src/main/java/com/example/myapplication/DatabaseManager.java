package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


    private final Context context;

    private String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
            ID + " integer primary key,"+
            MACT +" CHAR(3), "+
            TENCT + " NVARCHAR(128))";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Log.d(Tag, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
        Log.d(Tag, "onCreate: ");
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

    public List<TuDong> getAllTuDong(){
        List<TuDong> listTuDong = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                TuDong tuDong = new TuDong();
                tuDong.setId(cursor.getInt(0));
                tuDong.setMaCT(cursor.getString(1));
                tuDong.setTenCT(cursor.getString(2));
                listTuDong.add(tuDong);

            }while (cursor.moveToNext());
        }
        db.close();
        return listTuDong;
    }
    public int updateTuDong(TuDong tuDong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MACT, tuDong.getMaCT());
        contentValues.put(TENCT, tuDong.getTenCT());
        return db.update(TABLE_NAME,contentValues,ID +"=?",new String[] { String.valueOf(tuDong.getId())});
    }
    public int deleteTuDong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID +"=?", new String[] {String.valueOf(id)});
    }
}
