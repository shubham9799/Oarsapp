package com.example.dell1.materialdesign;

/**
 * Created by shubham9799 on 07-06-2015.
 */

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shubham9799 on 07-06-2015.
 */
public class Timetable_offline extends ListActivity {

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
        setContentView(R.layout.timetable_offline);

        contactList = new ArrayList<HashMap<String, String>>();
        ListView lv = getListView();


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
        public int j=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Timetable_offline.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... voids) {

            SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String x = sharedPreferences.getString("-1", DEFAULT);
            if (x.length() == 1) {
                char p = x.charAt(0);
                z = p - '0';
            }
            if (x.length() == 2) {
                char p = x.charAt(0);
                char q = x.charAt(1);
                z = (p - '0') * 10 + q - '0';
            }
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
                    j=1;
                } else {

                    // tmp hashmap for single contact
                    HashMap<String, String> contact = new HashMap<String, String>();
                    // adding each child node to HashMap key => valu
                    contact.put(TAG_NAME, name);
                    contact.put(TAG_DAY, day);
                    contact.put(TAG_TIME, time);
                    // adding contact to contact list
                    contactList.add(contact);
                }
            }
            if(j==1){
                Toast.makeText(Timetable_offline.this,
                        "No Data,Please Save Data First By Using Opening TimeTable After Login", Toast.LENGTH_LONG).show();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            runThread();
            Toast.makeText(Timetable_offline.this,
                    "Loaded Successfully", Toast.LENGTH_LONG).show();
            /**
             * Updating parsed JSON data into ListView
             * */

        }

        public void runThread() {

            new Thread() {
                public void run() {
                    while (i++ < 1000) {
                        try {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ListAdapter adapter = new SimpleAdapter(
                                            Timetable_offline.this, contactList,
                                            R.layout.timetable_offline_list, new String[]{TAG_NAME,
                                            TAG_DAY, TAG_TIME}, new int[]{R.id.TEXT1,
                                            R.id.TEXT2, R.id.TEXT3});
                                    setListAdapter(adapter);

                                }
                            });
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}