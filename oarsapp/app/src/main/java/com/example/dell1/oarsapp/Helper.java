package com.example.dell1.oarsapp;

/**
 * Created by DELL1 on 6/18/2015.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL1 on 6/18/2015.
 */
public class Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "oarsdatabase";
    public static final String TABLE_NAME = "oarstable";
    public static final int DATABASE_VERSION = 2;
   // public static final String NAME = "name";
   // public static final String PASSWORD = "password";
    public static final String Course = "course";

    public static final String Title = "title";
    public static final  String pre = "pre";
    public static final String department ="depart";
    public static final String notes = "note";
    public static final String instructor = "ins";
    public static final String email= "email";
    public static final String units= "unit";
    public static final String lec_day="lec_d";
    public static final String lec_time="lec_t";
    public static final String lab_day="lab_d";
    public static final String lab_time="lab_t";
    public static final String t_day="t_d";
    public static final String t_time="t_t";

    public static final String UID = "_id";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    private Context context;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Course + " VARCHAR(255),"+ Title + " VARCHAR(255),"+ pre + " VARCHAR(255),"+ department + " VARCHAR(255),"+ notes + " VARCHAR(255),"+ instructor + " VARCHAR(255),"+ email + " VARCHAR(255),"+ units + " VARCHAR(255),"+ lec_day + " VARCHAR(255),"+ lec_time + " VARCHAR(255),"+ lab_day + " VARCHAR(255),"+ lab_time + " VARCHAR(255),"+ t_day + " VARCHAR(255),"+ t_time + " VARCHAR(255));";
 //   public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Course + " VARCHAR(255),"
    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Message.message(context, "Constructor Was Called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Message.message(context, "on Create Called");
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }
    }


}

