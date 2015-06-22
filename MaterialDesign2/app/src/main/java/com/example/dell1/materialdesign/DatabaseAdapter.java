package com.example.dell1.materialdesign;

/**
 * Created by DELL1 on 6/18/2015.
 */

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by DELL1 on 6/17/2015.
 */
public class DatabaseAdapter extends ListActivity {

    private static final String TAG_Course = "Course No.";
    private static final String TAG_Title = "Course Title";
    private static final String TAG_LEC_DAY = "LEC:DAY";
    private static final String TAG_LEC_TIME = "LEC:TIME";
    private static final String TAG_LAB_DAY = "LAB:DAY";
    private static final String TAG_LAB_TIME = "LAB:TIME";
    private static final String TAG_T_DAY = "T/D:DAY";
    private static final String TAG_T_TIME = "T/D:TIME";
    private static final String TAG_Registration = "Total Registration";
    private static final String TAG_Pre = "Pre-Requisites";
    private static final String TAG_Department = "Department";
    private static final String TAG_Notes = "Instructor's Notes";
    private static final String TAG_Units = "Units";
    private static final String TAG_Instructor = "Instructor";
    private static final String TAG_Email = "Email";
    Helper helper;

    int k=1;
    public DatabaseAdapter(Context context) {
        helper = new Helper(context);
    }

    public void deleteAll()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from " + Helper.TABLE_NAME;
        try {
            db.execSQL(sql);
           // Message.message(this,"SUCCESSFUL");
        } catch (SQLException e) {
         //   Message.message(this,"UNSUCCESSFUL");
       //     setTitle("exception");
        }
       // db.delete(Helper.TABLE_NAME,null, null);
    }

    public long insertData(String Course, String Title, String pre, String department, String notes, String instructor, String email, String units, String lec_day, String lec_time, String lab_day, String lab_time, String t_day, String t_time) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.Course, Course);
        contentValues.put(Helper.Title, Title);
        contentValues.put(Helper.pre, pre);
        contentValues.put(Helper.department, department);
        contentValues.put(Helper.notes, notes);
        contentValues.put(Helper.instructor, instructor);
        contentValues.put(Helper.email, email);
        contentValues.put(Helper.units, units);
        contentValues.put(Helper.lec_day, lec_day);
        contentValues.put(Helper.lec_time, lec_time);
        contentValues.put(Helper.lab_day, lab_day);
        contentValues.put(Helper.lab_time, lab_time);
        contentValues.put(Helper.t_day, t_day);
        contentValues.put(Helper.t_time, t_time);

        long id = db.insert(Helper.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData_course(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.Course + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

           // String[] COLUMNS = new String[] {m_course,m_title,m_pre,m_department,m_notes,m_instructor,m_email,m_units,m_lec_day,m_lec_time,m_lab_day,m_lab_time,m_t_day,m_t_time };

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
            count=count+1;
        }
        return cursor;
    }

    public Cursor getData_title(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.Title + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            count=count+1;
            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
        }

        return cursor;
    }

    public Cursor getData_depart(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.department + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            count=count+1;
            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
        }

        return cursor;
    }

    public Cursor getData_ins(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.instructor + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            count=count+1;

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
        }

        return cursor;
    }

    public Cursor getData_lec(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.lec_time + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
            count=count+1;
        }

        return cursor;
    }

    public Cursor getData_lab(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.lab_time + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
            count=count+1;
        }

        return cursor;
    }


    public Cursor getData_lec_day(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.lec_day + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
            count=count+1;
        }

        return cursor;
    }

    public Cursor getData_lab_day(String name) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, Helper.lab_day + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        int count=0;
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);

            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);

            buffer.append(m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
            count=count+1;
        }

        return cursor;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Helper.UID, Helper.Course, Helper.Title, Helper.pre, Helper.department, Helper.notes, Helper.instructor, Helper.email, Helper.units, Helper.lab_day, Helper.lab_time, Helper.lec_day, Helper.lec_time, Helper.t_day, Helper.t_time};
        Cursor cursor = db.query(Helper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Helper.UID);
            int index2 = cursor.getColumnIndex(Helper.Course);
            int index3 = cursor.getColumnIndex(Helper.Title);
            int index4 = cursor.getColumnIndex(Helper.pre);
            int index5 = cursor.getColumnIndex(Helper.department);
            int index6 = cursor.getColumnIndex(Helper.notes);
            int index7 = cursor.getColumnIndex(Helper.instructor);
            int index8 = cursor.getColumnIndex(Helper.email);
            int index9 = cursor.getColumnIndex(Helper.units);
            int index10 = cursor.getColumnIndex(Helper.lec_day);
            int index11 = cursor.getColumnIndex(Helper.lec_time);
            int index12 = cursor.getColumnIndex(Helper.lab_day);
            int index13 = cursor.getColumnIndex(Helper.lab_time);
            int index14 = cursor.getColumnIndex(Helper.t_day);
            int index15 = cursor.getColumnIndex(Helper.t_time);
            // int index15=cursor.getColumnIndex(Helper.Title);


            int cid = cursor.getInt(index1);
            String m_course = cursor.getString(index2);
            String m_title = cursor.getString(index3);
            String m_pre = cursor.getString(index4);
            String m_department = cursor.getString(index5);
            String m_notes = cursor.getString(index6);
            String m_instructor = cursor.getString(index7);
            String m_email = cursor.getString(index8);
            String m_units = cursor.getString(index9);
            String m_lec_day = cursor.getString(index10);
            String m_lec_time = cursor.getString(index11);
            String m_lab_day = cursor.getString(index12);
            String m_lab_time = cursor.getString(index13);
            String m_t_day = cursor.getString(index14);
            String m_t_time = cursor.getString(index15);


            //   buffer.append(cid+""+m_course+" "+title+"\n");
            buffer.append(cid + "" + m_course + " " + m_title + " " + m_pre + " " + m_department + " " + m_notes + " " + m_instructor + " " + m_email + " " + m_units + " " + m_lec_day + " " + m_lec_time + " " + m_lab_day + " " + m_lab_time + " " + m_t_day + " " + m_t_time + "\n");
        }
        return cursor;
    }
}

