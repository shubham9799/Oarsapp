package com.example.dell1.materialdesign;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


public class Timetable_calendar extends ActionBarActivity {

    int count=0;
    private ProgressDialog pDialog;
    TextView httpStuff, text1, text2;
    HttpClient client;
    final static String URL = "http://home.iitk.ac.in/~aadilh/oars/timetable.json";
    JSONObject json;
    private static final String TAG_NAME = "course";
    private static final String TAG_DAY = "day";
    private static final String TAG_TIME = "time";
    int i;
    JSONArray mARRAY;
    //contacts JSONArray
    JSONArray contacts = null;


    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_calendar);

        contactList = new ArrayList<HashMap<String, String>>();
        client = new DefaultHttpClient();
        new Read().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public class Read extends AsyncTask<Void, Void, Void> {

        public static final String DEFAULT = "N/A";
        public int k = 10000;
        public int z = 1;
        public int index = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Timetable_calendar.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String x = sharedPreferences.getString("-1", DEFAULT);
            z = Integer.parseInt(x);


            //For THE Del Timetable java file
            SharedPreferences sharedPreferences1 = getSharedPreferences("Data2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            String id = String.valueOf(z);
            String key = "-1";
            editor1.putString(key, x);
            editor1.commit();

            for (int i = 0; i < z; i++) {
                String a = String.valueOf(k);
                k = k + 1;
                String b = String.valueOf(k);
                k = k + 1;
                String d = String.valueOf(k);
                k = k + 1;

                String name = sharedPreferences.getString(a, DEFAULT);
                String day = sharedPreferences.getString(b, DEFAULT);
                String time = sharedPreferences.getString(d, DEFAULT);
                if (name.equals(DEFAULT) || day.equals(DEFAULT) || time.equals(DEFAULT)) {

                } else {
                    insert(name, day, time);
                }
            }

            return null;
        }


        //// @Override
        //   protected String doInBackground(String... params) {
        //           return null;
        //       }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
                runThread();
            Toast.makeText(Timetable_calendar.this,
                    "Saved successfully", Toast.LENGTH_LONG).show();

        }

        @SuppressLint("NewApi")
        public void insert(String title, String day, String time) {


            int x, X;  //x for start hour and X for end hour

            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            Date d = new Date();
            int DATE = d.getDate();
            int year = d.getYear();
            int month = d.getMonth();

            //All this to get start hour and end hour
            char a = time.charAt(0);
            char b = time.charAt(1);
            if (b == '.') {
                x = a - '0';
            } else {
                x = (a - '0') * 10 + (b - '0');
            }

            char S = time.charAt(5);

            if (S == '-') {
                char A = time.charAt(6);
                char B = time.charAt(7);
                if (B == '.') {
                    X = A - '0';
                } else {
                    X = (A - '0') * 10 + (B - '0');
                }

            } else {
                char A = time.charAt(5);
                char B = time.charAt(6);
                if (B == '.') {
                    X = A - '0';
                } else {
                    X = (A - '0') * 10 + (B - '0');
                }
            }


            int DAY = 0;
            if (day.equals("Monday")) {
                DAY = 1;

            }
            if (day.equals("Tuesday")) {
                DAY = 2;

            }
            if (day.equals("Wednesday")) {
                DAY = 3;

            }
            if (day.equals("Thursday")) {
                DAY = 4;

            }
            if (day.equals("Friday")) {
                DAY = 5;
            }

            if (DAY == d.getDay()) {

                startTime.set(2015, month, DATE, x, 0);
                endTime.set(2015, month, DATE, X, 0);
            } else {
                if (DAY - d.getDay() > 0) {
                    DATE = d.getDate() + DAY - d.getDay();
                    startTime.set(2015, month, DATE, x, 0);
                    endTime.set(2015, month, DATE, X, 0);
                } else {
                    DATE = d.getDate() + 7 + (DAY - d.getDay());
                    startTime.set(2015, month, DATE, x, 0);
                    endTime.set(2015, month, DATE, X, 0);
                }
            }

            char o = title.charAt(title.length() - 2);
            if (o == 'P') {
                //Adding details to event
                if (count==0 || count==3) {
                    if(month>4){
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, title)
                                .putExtra(CalendarContract.Events.DESCRIPTION, "Class")
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, "LHC")
                                .putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=1;UNTIL=20151131");
                        startActivity(intent);

                    }
                    else{
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, title)
                                .putExtra(CalendarContract.Events.DESCRIPTION, "Class")
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, "LHC")
                                .putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=1;UNTIL=20150431");
                        startActivity(intent);

                    }

                }
                count = count + 1;
            } else {


                long calID = 1;
                long startMillis = 0;
                long endMillis = 0;
                TimeZone timeZone = TimeZone.getDefault();

                Uri uri = CalendarContract.Calendars.CONTENT_URI;
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
                values.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
                values.put(CalendarContract.Events.TITLE, title);
                values.put(CalendarContract.Events.DESCRIPTION, "Class");
                values.put(CalendarContract.Events.CALENDAR_ID, calID);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
                if (month > 4) {
                    values.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=1;UNTIL=20151131");
                } else {
                    values.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;INTERVAL=1;UNTIL=20150431");
                }


                Uri urL = cr.insert(CalendarContract.Events.CONTENT_URI, values);
                long eventID = Long.parseLong(urL.getLastPathSegment());
                //
                SharedPreferences sharedPreferences = getSharedPreferences("Data2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String id = String.valueOf(eventID);
                String key = String.valueOf(index);
                editor.putString(key, id);
                editor.commit();
                index = index + 1;
            }
        }

        public void runThread() {

            new Thread() {
                public void run() {
                    while (i++ < 15) {
                        try {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(Timetable_calendar.this,
                                            "Please Wait", Toast.LENGTH_LONG).show();

                                }
                            });
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}
