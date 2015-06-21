package com.example.dell1.materialdesign;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shubham9799 on 07-06-2015.
 */
public class timetable extends ListActivity {
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
    public Toolbar toolbar;

    public int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);

        //  toolbar = (Toolbar) findViewById(R.id.app_bar_1);
        //    setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        ///    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        ///    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contactList = new ArrayList<HashMap<String, String>>();
        ListView lv = getListView();

        httpStuff = (TextView) findViewById(R.id.course);

        text1 = (TextView) findViewById(R.id.day);
        text2 = (TextView) findViewById(R.id.time);

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

    public JSONObject lastEntry(int i) throws ClientProtocolException, IOException, JSONException {
        StringBuilder url = new StringBuilder(URL);
        HttpGet get = new HttpGet(url.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if (status == 200) {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            mARRAY = new JSONArray(data);
            JSONObject first = mARRAY.getJSONObject(i);
            return first;
        } else {
            j=1;
            Toast.makeText(timetable.this, "No Network", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<Void, Void, Void> {

        public int k = 10000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(timetable.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                json = lastEntry(0);
                for (i = 1; i < mARRAY.length(); i++) {

                    String name = json.getString(TAG_NAME);
                    String day = json.getString(TAG_DAY);
                    String time = json.getString(TAG_TIME);

                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (k == 10000) {
                        String s = String.valueOf(mARRAY.length());
                        editor.putString("-1", s);
                    }
                    String a = String.valueOf(k);
                    k = k + 1;
                    String b = String.valueOf(k);
                    k = k + 1;
                    String d = String.valueOf(k);
                    k = k + 1;
                    editor.putString(a, name);
                    editor.putString(b, day);
                    editor.putString(d, time);
                    editor.commit();

                    HashMap<String, String> contact = new HashMap<String, String>();
                    contact.put(TAG_NAME, name);
                    contact.put(TAG_DAY, day);
                    contact.put(TAG_TIME, time);
                    contactList.add(contact);
                    json = lastEntry(i);

                }
                String name = json.getString(TAG_NAME);
                String day = json.getString(TAG_DAY);
                String time = json.getString(TAG_TIME);

                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String a = String.valueOf(k);
                k = k + 1;
                String b = String.valueOf(k);
                k = k + 1;
                String d = String.valueOf(k);
                k = k + 1;
                editor.putString(a, name);
                editor.putString(b, day);
                editor.putString(d, time);
                editor.commit();

                HashMap<String, String> contact = new HashMap<String, String>();
                contact.put(TAG_NAME, name);
                contact.put(TAG_DAY, day);
                contact.put(TAG_TIME, time);
                contactList.add(contact);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
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
            if(j==0){
                Toast.makeText(timetable.this,
                        "Saved Successfully for Offline View", Toast.LENGTH_LONG).show();
            }
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
                                            timetable.this, contactList,
                                            R.layout.timetablelist, new String[]{TAG_NAME,
                                            TAG_DAY, TAG_TIME}, new int[]{R.id.course,
                                            R.id.day, R.id.time});

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
