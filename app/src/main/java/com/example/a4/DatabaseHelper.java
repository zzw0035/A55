package com.example.a4;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, "store.db", null, 4);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int a, int b){
        onCreate(sqLiteDatabase);
    }


    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE asd (id INTEGER PRIMARY KEY AUTOINCREMENT, Time varchar(255), Usd DOUBLE, Item varchar(255))");

    }





    public void Create(String time,double usd,String item){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Time", time);
        cv.put("Usd", usd);
        cv.put("Item", item);
        sqLiteDatabase.insert("asd", null, cv);
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor a = sqLiteDatabase.rawQuery("SELECT * FROM asd",null);
        return a;
    }
}
