package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.model.History;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DEMO.db";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDb.CREATE_USER_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + UserDb.TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    // add new user_data
    public long addContact(History history) {
        final SQLiteDatabase db = this.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(UserDb.COLUMN_DATE, history.get_date()); // saved date
        values.put(UserDb.COLUMN_HEIGHT, history.get_Height()); // user height
        values.put(UserDb.COLUMN_WEIGHT, history.get_Weight()); // user weight
        values.put(UserDb.COLUMN_AGE, history.get_Age()); // user age
        values.put(UserDb.COLUMN_GENDER, history.get_Gender()); // user gender
        values.put(UserDb.COLUMN_IDEAL_WEIGHT, history.get_IdealWeight()); // user ideal weight
        values.put(UserDb.COLUMN_FAT, history.get_Fat()); // fat in body
        values.put(UserDb.COLUMN_BMI, history.get_Bmi()); // calculated bmi
        values.put(UserDb.COLUMN_CATEGORY, history.get_Category()); // bmi category
        values.put(UserDb.COLUMN_TIME, history.get_time()); // saved time

        // Inserting Row
        final long id = db.insert(UserDb.TABLE_USER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

        return id;
    }

    // get all user_data in a list
    public List<History> getHistoryList() {
        List<History> userHistoryList = new ArrayList<>();

        // Select All Query
        final String selectQuery = "SELECT  * FROM " + UserDb.TABLE_USER + " ORDER BY " + UserDb.COLUMN_USER_ID + " DESC";

        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.set_id(Integer.parseInt(cursor.getString(0)));
                history.set_date(cursor.getString(1));
                history.set_Height(cursor.getString(2));
                history.set_Weight(cursor.getString(3));
                history.set_Age(cursor.getString(4));
                history.set_Gender(cursor.getString(5));
                history.set_IdealWeight(cursor.getString(6));
                history.set_Fat(cursor.getString(7));
                history.set_Bmi(cursor.getString(8));
                history.set_Category(cursor.getString(9));
                history.set_time(cursor.getString(10));

                // Adding contact to list
                userHistoryList.add(history);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return userHistoryList;
    }

    // update a single user_data
    public int UpdateUserData(History history) {
        final SQLiteDatabase db = this.getWritableDatabase();

        final ContentValues contentValues = new ContentValues();
        contentValues.put(UserDb.COLUMN_DATE, history.get_date());
        contentValues.put(UserDb.COLUMN_HEIGHT, history.get_Height());
        contentValues.put(UserDb.COLUMN_WEIGHT, history.get_Weight());
        contentValues.put(UserDb.COLUMN_AGE, history.get_Age());
        contentValues.put(UserDb.COLUMN_GENDER, history.get_Gender());
        contentValues.put(UserDb.COLUMN_IDEAL_WEIGHT, history.get_IdealWeight());
        contentValues.put(UserDb.COLUMN_FAT, history.get_Fat());
        contentValues.put(UserDb.COLUMN_BMI, history.get_Bmi());
        contentValues.put(UserDb.COLUMN_CATEGORY, history.get_Category());

        // updating row
        return db.update(UserDb.TABLE_USER, contentValues, UserDb.COLUMN_USER_ID + "= ?", new String[]{String.valueOf(history.get_id())});
    }

    // delete single user_data
    public void deleteUserData(History history) {
        final SQLiteDatabase db = this.getWritableDatabase();

        db.delete(UserDb.TABLE_USER, UserDb.COLUMN_USER_ID + "= ?", new String[]{String.valueOf(history.get_id())});
        db.close();
    }

    // Getting user_data Count
    private int getDataCount() {
        final SQLiteDatabase db = this.getWritableDatabase();

        final String countquery = "SELECT * FROM " + UserDb.TABLE_USER;
        final Cursor cursor = db.rawQuery(countquery, null);

        cursor.close();
        return cursor.getCount();
    }
}
