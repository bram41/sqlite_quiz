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

        //TABLE NAME
        public static final String TABLE_USERS = "users";
        public static final String TABLE_SOAL = "soal";

        //TABLE USERS COLUMNS
        //ID COLUMN @primaryKey
        public static final String KEY_ID = "id";

        //COLUMN user name
        public static final String KEY_USER_NAME = "username";

        //COLUMN email
        public static final String KEY_EMAIL = "email";

        //COLUMN password
        public static final String KEY_PASSWORD = "password";


        public static final String KEY_SOAL = "soal";
        public static final String KEY_BENAR = "benar";
        public static final String KEY_PERTAMA = "pertama";
        public static final String KEY_KEDUA = "kedua";
        public static final String KEY_KETIGA = "ketiga";
        public static final String KEY_KEEMPAT = "keempat";


        private SQLiteDatabase dbase;
        //SQL for creating users table
        public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
                + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_USER_NAME + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT"
                + " ) ";

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
            sqLiteDatabase.execSQL(SQL_TABLE_USERS);
            sqLiteDatabase.execSQL(SQL_TABLE_SOAL);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //drop table to create new one if database version updated
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SOAL);
        }

        //using this method we can add users to user table
        public void addUser(User user) {

            //get writable database
            SQLiteDatabase db = this.getWritableDatabase();

            //create content values to insert
            ContentValues values = new ContentValues();

            //Put username in  @values
            values.put(KEY_USER_NAME, user.userName);

            //Put email in  @values
            values.put(KEY_EMAIL, user.email);

            //Put password in  @values
            values.put(KEY_PASSWORD, user.password);

            // insert row
            long todo_id = db.insert(TABLE_USERS, null, values);
        }

        public User Authenticate(User user) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                    KEY_EMAIL + "=?",
                    new String[]{user.email},//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email
                User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                //Match both passwords check they are same or not
                if (user.password.equalsIgnoreCase(user1.password)) {
                    return user1;
                }
            }

            //if user password does not matches or there is no record with that email then return @false
            return null;
        }

        public boolean isEmailExists(String email) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                    new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                    KEY_EMAIL + "=?",
                    new String[]{email},//Where clause
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
                //if cursor has value then in user database there is user associated with this given email so return true
                return true;
            }

            //if email does not exist return false
            return false;
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
