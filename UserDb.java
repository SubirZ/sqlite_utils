package com.example.sqlite;

class UserDb {

    static final String TABLE_USER = "USERS";

    static final String COLUMN_USER_ID = "ID";
    static final String COLUMN_DATE = "DATE";
    static final String COLUMN_HEIGHT = "HEIGHT";
    static final String COLUMN_WEIGHT = "WEIGHT";
    static final String COLUMN_AGE = "AGE";
    static final String COLUMN_GENDER = "GENDER";
    static final String COLUMN_IDEAL_WEIGHT = "IDEAL_WEIGHT";
    static final String COLUMN_FAT = "FAT";
    static final String COLUMN_BMI = "BMI";
    static final String COLUMN_CATEGORY = "CATEGORY";
    static final String COLUMN_TIME = "TIME";

    static final String CREATE_USER_DATA = "CREATE TABLE "
            + TABLE_USER
            + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_HEIGHT + " TEXT,"
            + COLUMN_WEIGHT + " TEXT,"
            + COLUMN_AGE + " TEXT,"
            + COLUMN_GENDER + " TEXT,"
            + COLUMN_IDEAL_WEIGHT + " TEXT,"
            + COLUMN_FAT + " TEXT,"
            + COLUMN_BMI + " TEXT,"
            + COLUMN_CATEGORY + " TEXT,"
            + COLUMN_TIME + " TEXT"
            + ")";
}
