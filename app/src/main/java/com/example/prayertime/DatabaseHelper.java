package com.example.prayertime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;

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
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (email TEXT PRIMARY KEY, password TEXT, prayerName TEXT, prayerTime TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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
