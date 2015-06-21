package com.example.dell1.materialdesign;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class course_offline extends ListActivity {
    private ProgressDialog pDialog;

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
        setContentView(R.layout.course_offline);

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        new GetContacts().execute();

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        public static final String DEFAULT = "N/A";
        int k = 1;
        int z = 0;
        int j=0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(course_offline.this);
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
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String x = sharedPreferences.getString("0", DEFAULT);
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
                String e = String.valueOf(k);
                k = k + 1;
                String name = sharedPreferences.getString(a, DEFAULT);
                String title = sharedPreferences.getString(b, DEFAULT);
                String mid = sharedPreferences.getString(d, DEFAULT);
                String end = sharedPreferences.getString(e, DEFAULT);
                if (title.equals(DEFAULT) || name.equals(DEFAULT) || end.equals(DEFAULT) || mid.equals(DEFAULT)) {
                        j=1;
                } else {

                    // tmp hashmap for single contact
                    HashMap<String, String> contact = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    contact.put(TAG_TITLE, title);
                    contact.put(TAG_NAME, name);
                    contact.put(TAG_END, end);
                    contact.put(TAG_MID, mid);
                    // adding contact to contact list
                    contactList.add(contact);
                }
            }
            if(j==1){
                Toast.makeText(course_offline.this,
                        "No Data,Please Save Data First By Using Opening Course After Login", Toast.LENGTH_LONG).show();
            }

            return null;
        }

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
            Toast.makeText(course_offline.this,
                    "Loaded Successfully", Toast.LENGTH_LONG).show();

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
                                            course_offline.this, contactList,
                                            R.layout.course_offline_list, new String[]{TAG_NAME, TAG_TITLE,
                                            TAG_MID, TAG_END}, new int[]{R.id.textview1,
                                            R.id.textview2, R.id.textview3, R.id.textview4});

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

