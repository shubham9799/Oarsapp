package com.example.dell1.materialdesign;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;


public class Course_calendar extends ActionBarActivity {

    private ProgressDialog pDialog;
    public long[] eventID = new long[8];
    // URL to get contacts JSON
    private Toolbar toolbar;
    TextView httpStuff, text1, text2, text3;
    HttpClient client;
    private static String url = "http://home.iitk.ac.in/~aadilh/oars/course_detail.json";

    // JSON Node names
    private static final String TAG_NAME = "Course Name";
    private static final String TAG_END = "End Sem";
    private static final String TAG_MID = "Mid Sem";
    private static final String TAG_TITLE = "Course Title";
    int i;
    // contacts JSONArray


    // Hashmap for ListView
    JSONArray mARRAY;
    JSONObject json;
    //contacts JSONArray
    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_calendar);

        contactList = new ArrayList<HashMap<String, String>>();

        new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        public static final String DEFAULT = "N/A";
        int k = 1;
        int z = 0;
        int index = 0;

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        public String x = sharedPreferences.getString("0", DEFAULT);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Course_calendar.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            // Making a request to url and getting response


            // looping through All Contacts
            //


            z = Integer.parseInt(x);

            SharedPreferences sharedPreferences1 = getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            String id = String.valueOf(2 * z);
            String key = "-1";
            editor1.putString(key, id);
            editor1.commit();

            for (int i = 0; i < z; i++) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String a = String.valueOf(k);
                k = k + 1;
                String b = String.valueOf(k);
                k = k + 1;
                String d = String.valueOf(k);
                k = k + 1;
                String e = String.valueOf(k);
                k = k + 1;
                String name = sharedPreferences.getString(a, DEFAULT);
                String title = sharedPreferences.getString(b, DEFAULT);
                String mid = sharedPreferences.getString(d, DEFAULT);
                String end = sharedPreferences.getString(e, DEFAULT);
                if (title.equals(DEFAULT) || name.equals(DEFAULT) || end.equals(DEFAULT) || mid.equals(DEFAULT)) {

                } else {
                    insert(title, mid);
                    insert(title, end);
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            Toast.makeText(Course_calendar.this,
                    "Saved Successfully", Toast.LENGTH_LONG).show();
            /**
             * Updating parsed JSON data into ListView
             * */

        }

        public void insert(String Title, String MidSem) {

            long calID = 1;
            long startMillis = 0;
            long endMillis = 0;
            String s = "";
            Calendar startTime = Calendar.getInstance();

            char a = MidSem.charAt(0);
            char b = MidSem.charAt(1);
            int c = a - '0';
            int d = b - '0';
            int e = c * 10 + d;
            char f = MidSem.charAt(8);
            int g = 201 * 10 + f - '0';
            int x;
            char h = MidSem.charAt(3);
            if (h == 'F') {
                x = 1;
                startTime.set(g, x, e);
                s = "MidSem";
            }
            if (h == 'A') {
                x = 3;
                startTime.set(g, x, e);
                s = "EndSem";
            }
            if (h == 'S') {
                x = 8;
                startTime.set(g, x, e);
                s = "MidSem";
            }
            if (h == 'N') {
                x = 10;
                startTime.set(g, x, e);
                s = "EndSem";
            }
            TimeZone timeZone = TimeZone.getDefault();
            Uri uri = CalendarContract.Calendars.CONTENT_URI;
            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
            values.put(CalendarContract.Events.DTEND, startTime.getTimeInMillis());
            values.put(CalendarContract.Events.TITLE, Title);
            values.put(CalendarContract.Events.DESCRIPTION, s);
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
            Uri urL = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            eventID[index] = Long.parseLong(urL.getLastPathSegment());

            SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String id = String.valueOf(eventID[index]);
            String key = String.valueOf(index);
            editor.putString(key, id);
            editor.commit();
            index = index + 1;
        }
    }


    public void del(View view) {
        int Index;
        for (Index = 0; Index < 8; Index++) {
            Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/events");
            Uri url = ContentUris.withAppendedId(CALENDAR_URI, eventID[Index]);
            int rows = getContentResolver().delete(url, null, null);
        }
    }
}
