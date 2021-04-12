package com.example.it17039000.savealife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseManager extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "myDatabase1";    // Database Name
    private static final String TABLE_NAME = "Blood1";   // Table Name
    private static final int DATABASE_Version = 1;    // Database Version
    private static final String ID="id";     // Column I (Primary Key)
    private static final String NAME = "Name";    //Column II
    private static final String BloodGroup= "BloodGroup";    // Column III
    private static final String Unit= "Unit";
    private static final String Hospital= "Hospital";
    private static final String Phone= "Phone";
    private static final String Place= "Place";

    public MyDatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME+" (\n" +
                " "+ID+" INTEGER NOT NULL CONSTRAINT blood1_pk PRIMARY KEY AUTOINCREMENT,\n" +
                " "+NAME+" varchar(20) NOT NULL,\n" +
                " "+BloodGroup+" varchar(10) NOT NULL,\n" +
                " "+Unit+" varchar NOT NULL,\n" +
                " "+Hospital+" varchar(100) NOT NULL,\n" +
                " "+Phone+" varchar NOT NULL,\n" +
                " "+Place+" varchar(100) NOT NULL\n" +
                ");";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    boolean addBloodRequests(String name, String bloodGroup, String unit, String hospital, String phone, String place){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(BloodGroup, bloodGroup);
        //cv.put(Unit, String.valueOf(unit));
        cv.put(Unit, unit);
        cv.put(Hospital,hospital );
        //cv.put(Phone, String.valueOf(phone));
        cv.put(Phone, phone);
        cv.put(Place, place);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;
    }

    Cursor getBloodRequests(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

    }

    Cursor getBloodNotifications(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

    }

    boolean updateBloodrequests(int id, String name, String bloodGroup, String unit, String hospital, String phone, String place){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(BloodGroup, bloodGroup);
        cv.put(Unit, String.valueOf(unit));
        cv.put(Hospital,hospital );
        cv.put(Phone, String.valueOf(phone));
        cv.put(Place, place);
        return sqLiteDatabase.update(TABLE_NAME, cv, ID +"=?", new String[]{String.valueOf(id)}) > 0;
    }


    boolean deleteBloodRequests(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)})  > 0 ;
    }
}
