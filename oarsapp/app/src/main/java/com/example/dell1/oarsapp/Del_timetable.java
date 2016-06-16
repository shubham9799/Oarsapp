package com.example.dell1.oarsapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class Del_timetable extends ActionBarActivity {
    public static final String DEFAULT = "N/A";
    public int index=0;
//    public int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_timetable);
        ContentResolver cr = getContentResolver();
        ContentValues values= new ContentValues();

        SharedPreferences sharedPreferences = getSharedPreferences("Data2", Context.MODE_PRIVATE);
        String x = sharedPreferences.getString("-1", DEFAULT);
        if(x.equals(DEFAULT)){

        }
        else
        {
            int length = Integer.parseInt(x);
            int i;
            for(i=0;i<length;i++)
            {
                String key=String.valueOf(i);
                String id = sharedPreferences.getString(key, DEFAULT);
                int event_id = Integer.parseInt(id);

                    Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/events");
                    Uri url = ContentUris.withAppendedId(CALENDAR_URI, event_id);
                    int rows=getContentResolver().delete(url, null, null);


            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_del_timetable, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
