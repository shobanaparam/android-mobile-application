package com.example.it17039000.savealife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "myDatabase1.db";
    public static final String TABLE_NAME = "bank_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "BLOODGROUP";
    public static final String COL_2 = "UNITS";

    public DatabaseHelper( Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , BLOODGROUP TEXT , UNITS INTEGER )");


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //insert

    public boolean insertData(String bloodgroup , String units ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,bloodgroup);
        contentValues.put(COL_2,units);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
    public Integer DeleteData(String bloodgroup){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " BLOODGROUP  = ? ", new String[]{ bloodgroup });


    }
    public boolean updateData(String boodgroup , String units ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,boodgroup);
        contentValues.put(COL_2,units);

        db.update(TABLE_NAME, contentValues, " BLOODGROUP = ?" ,new  String[]{ boodgroup });
        return true;

    }
    public Cursor search(String value){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE BLOODGROUP='"+value+"'",null);
        return res;


    }


    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  data;
    }




}
