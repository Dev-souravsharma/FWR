package com.student.fwr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RegisterDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CELL = "cell";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 1;
    // Create Entries
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_CELL + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_PASSWORD + " TEXT)";

    // Delete Entries
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public RegisterDbHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    // Insert data in DB
    public long insertUser(String name, String address, String cell, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CELL, cell);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long newRowId = db.insert(TABLE_NAME, null, values);
        return newRowId;
    }

    public Boolean checkUsername(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email= ?",new String[]{email});
        if (cursor.getCount()>0) {
            return true;
        }
        return  false;
    }

    public  int checkUsernameAndPassword(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select name from user",null);
      Cursor cursor = db.rawQuery("Select * from user where email = ? and password = ?",new String[] {password,email});
        Log.d("Sourav", "checkUsernameAndPassword: "+cursor.getCount());
        Boolean isValid = cursor.moveToFirst();
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return  count;
    }
}
