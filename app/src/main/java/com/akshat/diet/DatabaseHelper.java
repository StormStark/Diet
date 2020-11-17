package com.akshat.diet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_diet";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USER_TABLE = "CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT," +
                "height TEXT, weight TEXT, name TEXT, age INTEGER, gender TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        Users user = new Users(1, "akshatyadav15@gmail.com", "12345678", "85", "182", "fullName", 22, "Male");
        addUsers(user, sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    void addUsers(Users user, SQLiteDatabase sqLiteDatabase) {

        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("weight", user.getUserWeightInKG());
        values.put("height", user.getUserHeightInCM());
        values.put("name", user.getFullName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        sqLiteDatabase.insert("users", null, values);
        //sqLiteDatabase.close(); // Closing database connection
    }

    public boolean addNewUser(Users user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("weight", user.getUserWeightInKG());
        values.put("height", user.getUserHeightInCM());
        values.put("name", user.getFullName());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());
        sqLiteDatabase.insert("users", null, values);
        return true;
    }

    public Users getUserBy(String username, String password) {

        String selectQuery = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Users user = new Users();
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            user.setUserId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setUserHeightInCM(cursor.getString(3));
            user.setUserWeightInKG(cursor.getString(4));
            user.setFullName(cursor.getString(5));
            user.setAge(Integer.parseInt(cursor.getString(6)));
            user.setGender(cursor.getString(7));
            return user;
        } else return null;

    }
}
