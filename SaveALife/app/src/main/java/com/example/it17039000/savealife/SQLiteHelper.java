package com.example.it17039000.savealife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "event.db";
    public static final String TABLE_NAME = "event_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "ADDRESS";
    public static final String COL_5 = "TIME";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,DATE TEXT,ADDRESS TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String date, String address, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, address);
        contentValues.put(COL_5, time);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
    //update
    public boolean updateData(String name , String date , String address , String time ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,time);

        db.update(TABLE_NAME, contentValues, "NAME = ?" ,new  String[]{ name });
        return true;

    }
    //search
    public Cursor search(String value){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE NAME='"+value+"'",null);
        return res;

    }
    //delete
    public Integer DeleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " NAME = ? ", new String[]{ name });


    }
}