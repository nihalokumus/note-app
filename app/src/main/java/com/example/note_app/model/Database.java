package com.example.note_app.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Notlar";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOT = "CREATE TABLE Notlar (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "baslik VARCHAR, " +
                "notMetin VARCHAR, " +
                "listName VARCHAR, " +
                "imageUrl VARCHAR, " +
                "color VARCHAR, " +
                "tarih VARCHAR)";
        db.execSQL(CREATE_NOT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Notlar");
        onCreate(db);
    }

    public void yeniNot(Not not) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_NOT = "INSERT INTO Notlar (baslik, notMetin, list, imageUrl, color, tarih) " +
                "VALUES ('" + not.getBaslik() + "', '" + not.getNotMetin() + "', '" +
                not.getListName() + "', '" + not.getImageUrl() + "', '" + not.getRenk() + "', '" + not.getTarih() + "')";
        db.execSQL(INSERT_NOT);
        db.close();
    }

    public ArrayList<Not> getNotlarım() {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_NOT = "SELECT * FROM Notlar ORDER BY id DESC";
        Cursor cursor = db.rawQuery(SELECT_NOT, null);
        ArrayList<Not> notlar = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                try {
                    do {
                        Not not = new Not("", "", "", "", "", "" );
                        not.setId(cursor.getInt(0));
                        not.setBaslik(cursor.getString(1));
                        not.setNotMetin(cursor.getString(2));
                        not.setListName(cursor.getString(3));
                        not.setImageUrl(cursor.getString(4));
                        not.setRenk(cursor.getString(5));
                        not.setTarih(cursor.getString(6));
                        notlar.add(not);
                    } while (cursor.moveToNext());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return notlar;
    }

    public void NotGuncelle(Not not) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_NOT = "UPDATE Notlar SET baslik='" + not.getBaslik() + "', " +
                "notMetin='" + not.getNotMetin() + "', list='" + not.getListName() + "', " +
                "imageUrl='" + not.getImageUrl() + "', color='" + not.getRenk() + "', " +
                "tarih='" + not.getTarih() + "' WHERE id=" + not.getId();
        db.execSQL(UPDATE_NOT);
        db.close();
    }

    public void NotSil(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_NOT = "DELETE FROM Notlar WHERE id=" + id;
        db.execSQL(DELETE_NOT);
        db.close();
    }
}
