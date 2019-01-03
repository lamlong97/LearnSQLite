package com.hutech.lamth.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteCmd extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SQLiteDemo.db";
    private static final int SCHEMA_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String KEY_ID = "_id";
    private static final String KEY_STUDENT_NAME = "name";
    private static final String KEY_STUDENT_MARKS = "address";

    public SQLiteCmd(Context context){
        super(context,DATABASE_NAME,null,SCHEMA_VERSION);
    }

    public SQLiteCmd(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    public void insert(String name,String address){
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("address",address);

        getWritableDatabase().insert("student","name",cv);
    }

    public Cursor getAll(){
        Cursor cursor;
        cursor = getReadableDatabase().rawQuery("SELECT _id,name,address FROM student",null);
        return (cursor);
    }

    public String getName(Cursor c){
        return c.getString(1);
    }
    public String getAddress(Cursor c){
        return c.getString(2);
    }
}
