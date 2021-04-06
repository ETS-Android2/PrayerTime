package com.example.prayertime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "prayerTime.db";
    public static final String TABLE_NAME = "prayerTime_table";
    public static final String COL_1 = "email";
    public static final String COL_2 = "password";
    public static final String COL_3 = "prayerName";
    public static final String COL_4 = "prayerTime";
    
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (email TEXT PRIMARY KEY, password TEXT) ");
        sqLiteDatabase.execSQL("create table prayer_time (email TEXT,name TEXT,time TEXT,FOREIGN KEY(email) REFERENCES prayerTime_table (email));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+"prayer_time");
        onCreate(sqLiteDatabase);

    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean insertPrayerTimeData(String email, String name,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",name);
        contentValues.put("time",time);
        long result = db.insert("prayer_time",null,contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public ArrayList<PrayerTime> getTimes(String em){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<PrayerTime> list= new ArrayList<PrayerTime>();
        Cursor cur = null;

        db.beginTransaction();

        try{
            cur = db.query("prayer_time",new String[] {}, "email = '"+em+"'",null,null,null,null);
            if(cur!=null){
                if(cur.moveToFirst()){
                    do{
                        PrayerTime t = new PrayerTime();
                        t.setName(cur.getString(cur.getColumnIndex("name")));
                        t.setTime(cur.getString(cur.getColumnIndex("time")));
                        list.add(t);
                    }while (cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }

        return list;

    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from prayerTime_table where email = ?", new String[] {email});
        if(cursor.getCount() >0) return true;
        else
            return false;
    }
    public Boolean checkEmailandPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from prayerTime_table where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0) return true;
        else
            return false;
    }

    public Boolean UpdateAcount(String email, String password,String Oemail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.update(TABLE_NAME,contentValues,"email=?",new String[]{ Oemail});
        if(result == -1) return false;
        else
            return true;

    }}
