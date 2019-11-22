package com.example.cuoiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cuoiki.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public static final String TABLE_NAME = "HoaDon";
    public static final String DATABASE_NAME = "QuanliHoaDon";

    public static final String ID = "_id";

    public static final String SO_XE = "so_xe";
    public static final String QUANG_DUONG = "quang_duong";
    public static final String DON_GIA = "don_gia";
    public static final String PHAN_TRAM = "phan_tram";


    private SQLiteDatabase database;
    private Context context;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SO_XE + " TEXT, " +
                QUANG_DUONG + " REAL, " +
                DON_GIA + " TEXT, " +
                PHAN_TRAM + " INT)  ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long insert(HoaDon hoaDon) {
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SO_XE, hoaDon.getSoXe());
        values.put(QUANG_DUONG, hoaDon.getQuangDuong());
        values.put(DON_GIA, hoaDon.getDonGia());
        values.put(PHAN_TRAM, hoaDon.getPhanTram());
        return database.insert(TABLE_NAME, null, values);
    }

    public long update(HoaDon hoaDon, int id) {
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SO_XE, hoaDon.getSoXe());
        values.put(QUANG_DUONG, hoaDon.getQuangDuong());
        values.put(DON_GIA, hoaDon.getDonGia());
        values.put(PHAN_TRAM, hoaDon.getPhanTram());
        return database.update(TABLE_NAME, values, ID + " = " + id, null);
    }

    public long delete(int id) {
        database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, ID + " = " + id, null);
    }

    public List<HoaDon> getAll() {
        database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);

        List<HoaDon> hoaDons = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String soXe = cursor.getString(1);
            double quangDuong = cursor.getDouble(2);
            String donGia = cursor.getString(3);
            int phanTram = cursor.getInt(4);

            HoaDon hoaDon = new HoaDon(soXe,quangDuong,donGia,phanTram);
            hoaDons.add(hoaDon);
            cursor.moveToNext();
        }

        return hoaDons;
    }

    public void openDB() {
        database = this.getWritableDatabase();
    }

    public void closeDB() {
        if (database == null || database.isOpen()) {
            database.close();
        }
    }


}
