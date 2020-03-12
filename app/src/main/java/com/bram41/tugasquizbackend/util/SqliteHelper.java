package com.bram41.tugasquizbackend.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {

        //NAMA DATABASE
        public static final String DATABASE_NAME = "quizapp";

        //VERSI DATABASE
        public static final int DATABASE_VERSION = 1;

        //TABLE
        public static final String TABLE_SOAL = "soal";

        //TABLE USERS COLUMNS
        //ID COLUMN @primaryKey
        public static final String KEY_ID = "id";


        public static final String KEY_SOAL = "soal";
        public static final String KEY_BENAR = "benar";
        public static final String KEY_PERTAMA = "pertama";
        public static final String KEY_KEDUA = "kedua";
        public static final String KEY_KETIGA = "ketiga";
        public static final String KEY_KEEMPAT = "keempat";


        private SQLiteDatabase dbase;
        public static final String SQL_TABLE_SOAL =  " CREATE TABLE " + TABLE_SOAL
                + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_SOAL + " TEXT, "
                + KEY_BENAR + " TEXT, "
                + KEY_PERTAMA + " TEXT,"
                + KEY_KEDUA + " TEXT, "
                + KEY_KETIGA + " TEXT, "
                + KEY_KEEMPAT + " TEXT"
                + " ) ";

        public SqliteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //Create Table when oncreate gets called
            sqLiteDatabase.execSQL(SQL_TABLE_SOAL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //drop table to create new one if database version updated
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SOAL);
        }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SOAL;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_ID, cursor.getString(0));
                map.put(KEY_SOAL, cursor.getString(1));
                map.put(KEY_BENAR, cursor.getString(2));
                map.put(KEY_PERTAMA, cursor.getString(3));
                map.put(KEY_KEDUA, cursor.getString(4));
                map.put(KEY_KETIGA, cursor.getString(5));
                map.put(KEY_KEEMPAT, cursor.getString(6));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select soal ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String soal, String benar, String pertama, String kedua, String ketiga, String keempat) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SOAL + " (soal, benar, pertama, kedua, ketiga, keempat) " +
                "VALUES ('" + soal + "', '" + benar + "', '" + pertama + "', '"+kedua+"','"+ketiga+"', '"+keempat+"')";

        Log.e("insert soal ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String soal, String benar, String pertama, String kedua, String ketiga, String keempat) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SOAL + " SET "
                + KEY_SOAL + "='" + soal + "', "
                + KEY_BENAR + "='" + benar + "',"
                + KEY_PERTAMA + "='" + pertama + "', "
                + KEY_KEDUA + "='" + kedua + "',"
                + KEY_KETIGA + "='" + ketiga + "', "
                + KEY_KEEMPAT + "='" + keempat + "' "
                + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update soal ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SOAL + " WHERE " + KEY_ID + "=" + "'" + id + "'";
        Log.e("update soal ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    public List<Data> getAllQuestions() {
        List<Data> quesList = new ArrayList<Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SOAL;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Data quest = new Data();
                quest.setId(cursor.getString(0));
                quest.setSoal(cursor.getString(1));
                quest.setBenar(cursor.getString(2));
                quest.setPertama(cursor.getString(3));
                quest.setKedua(cursor.getString(4));
                quest.setKetiga(cursor.getString(5));
                quest.setKeempat(cursor.getString(6));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_SOAL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

}
