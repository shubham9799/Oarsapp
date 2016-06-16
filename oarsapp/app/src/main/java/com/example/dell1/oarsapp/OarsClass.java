package com.example.dell1.oarsapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by shubham9799 on 06-06-2015.
 */
public class OarsClass extends ActionBarActivity {
    private Toolbar toolbar;
    ListView list;
    String[] web = {
            "Time-Table",
            "Time_Table_Save",
            "Course",
            "Course_Save"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oarsview);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("OARS");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            Intent i = new Intent(this, Oars_login.class);
            NavUtils.navigateUpTo(this, i);
        }

        return super.onOptionsItemSelected(item);
    }


    public void Timetable(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(OarsClass.this,
                "Timetable", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, timetable.class);
        startActivity(i);

    }


    public void Course(View view) {
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(OarsClass.this,
                "Course", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, course.class);
        startActivity(i);
    }

    public void Timetable_Calendar(View view) {
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(OarsClass.this,
                " Timetable Calendar", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Timetable_calendar.class);
        startActivity(i);
    }

    public void Calendar_Course(View view) {
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(OarsClass.this,
                "Course Calendar", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Course_calendar.class);
        startActivity(i);
    }

    public void del_timetable(View view) {
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        final String DEFAULT = "N/A";
        int index = 0;
        int j = 0;
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();

        SharedPreferences sharedPreferences = getSharedPreferences("Data2", Context.MODE_PRIVATE);
        String x = sharedPreferences.getString("-1", "");
        if (x.equals(DEFAULT)) {

        } else {
            int length = Integer.parseInt(x);
            int i;
            for (i = 0; i < length; i++) {
                String key = String.valueOf(i);
                String id = sharedPreferences.getString(key, key);
                int event_id = Integer.parseInt(id);
                if (id.equals(DEFAULT)) {

                } else {
                    j = 1;
                    Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/events");
                    Uri url = ContentUris.withAppendedId(CALENDAR_URI, event_id);
                    int rows = getContentResolver().delete(url, null, null);
                }
            }
            if (j == 1) {
                Toast.makeText(OarsClass.this,
                        "Deleted Successfully", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void del_course(View view) {
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        view.startAnimation(animation);
        String DEFAULT = "N/A";
        int index = 0;
        int j = 0;

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String x = sharedPreferences.getString("-1", DEFAULT);
        if (x.equals(DEFAULT)) {
            Toast.makeText(OarsClass.this,
                    "No data to delete", Toast.LENGTH_LONG).show();
        } else {
            int length = Integer.parseInt(x);
            int i;
            for (i = 0; i < length; i++) {
                String key = String.valueOf(i);
                String id = sharedPreferences.getString(key, DEFAULT);
                int event_id = Integer.parseInt(id);
                if (id.equals(DEFAULT)) {
                    Toast.makeText(OarsClass.this,
                            "No Data To Delete", Toast.LENGTH_LONG).show();
                } else {
                    Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/events");
                    Uri url = ContentUris.withAppendedId(CALENDAR_URI, event_id);
                    int rows = getContentResolver().delete(url, null, null);
                    j = 1;
                }
            }
        }
        if (j == 1) {
            Toast.makeText(OarsClass.this,
                    "Deleted Successfully", Toast.LENGTH_LONG).show();
        }
    }
}
