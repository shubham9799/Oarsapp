package com.example.dell1.oarsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
import android.widget.EditText;
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


public class Course_Search extends ActionBarActivity {
    DatabaseAdapter helper;
    Helper helper2;
    private Toolbar toolbar;
    TextView httpStuff, text1, text2, text3;
    HttpClient client;
    final static String URL = "http://home.iitk.ac.in/~aadilh/oars/course_search.json";
    JSONObject json;

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

    EditText name;
    int i;
    JSONArray mARRAY;
    //contacts JSONArray
    JSONObject contacts = null;
    JSONArray contactList;
    private ProgressDialog pDialog;
    // Hashmap for ListView
//    ArrayList<HashMap<String, String>> contactList;

    public int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course__search);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Upgrade");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contactList = new JSONArray();
        //   contactList = new ArrayList<HashMap<String, String>>();
        //    ListView lv = getListView();


        helper2 = new Helper(this);
        helper = new DatabaseAdapter(this);
        SQLiteDatabase sqLiteDatabase = helper2.getWritableDatabase();


        client = new DefaultHttpClient();
        new Read().execute();


    }

    public void Delete(View view) {
        helper.deleteAll();
        Message.message(this, "Success");
    }

    public void Insert(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(Course_Search.this,
                "Insert", Toast.LENGTH_LONG).show();
        int k = 0;
        try {
            json = contactList.getJSONObject(0);
            for (k = 1; k < contactList.length(); k++) {

                String Course = json.getString(TAG_Course);
                String Title = json.getString(TAG_Title);
                String pre = json.getString(TAG_Pre);
                String department = json.getString(TAG_Department);
                String notes = json.getString(TAG_Notes);
                String instructor = json.getString(TAG_Instructor);
                String email = json.getString(TAG_Email);
                String units = json.getString(TAG_Units);
                String lec_day = json.getString(TAG_LEC_DAY);
                String lec_time = json.getString(TAG_LEC_TIME);
                String lab_day = json.getString(TAG_LAB_DAY);
                String lab_time = json.getString(TAG_LAB_TIME);
                String t_day = json.getString(TAG_T_DAY);
                String t_time = json.getString(TAG_T_TIME);
                long id = helper.insertData(Course, Title, pre, department, notes, instructor, email, units, lec_day, lec_time, lab_day, lab_time, t_day, t_time);
                if (id < 0) {
                    //   Message.message(this, "UNSUUCESSFUL");
                } else {
                    // Message.message(this, "SUCCESSFUL");
                }
                json = contactList.getJSONObject(k);
                // HashMap<String, String> contact = new HashMap<String, String>();
            }
            Message.message(this, "Successful");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            Intent i = new Intent(this, advanced_course_search.class);
            NavUtils.navigateUpTo(this, i);
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
            j = 1;
            Toast.makeText(Course_Search.this, "Not Connected To Internet", Toast.LENGTH_SHORT);
            return null;
        }
    }

    public class Read extends AsyncTask<Void, Void, Void> {

        public int k = 1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Course_Search.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                json = lastEntry(0);
                for (i = 1; i < mARRAY.length(); i++) {
                    String Course = json.getString(TAG_Course);
                    String Title = json.getString(TAG_Title);
                    String pre = json.getString(TAG_Pre);
                    String department = json.getString(TAG_Department);
                    String notes = json.getString(TAG_Notes);
                    String instructor = json.getString(TAG_Instructor);
                    String email = json.getString(TAG_Email);
                    String units = json.getString(TAG_Units);
                    String lec_day = json.getString(TAG_LEC_DAY);
                    String lec_time = json.getString(TAG_LEC_TIME);
                    String lab_day = json.getString(TAG_LAB_DAY);
                    String lab_time = json.getString(TAG_LAB_TIME);
                    String t_day = json.getString(TAG_T_DAY);
                    String t_time = json.getString(TAG_T_TIME);

                    if (pre == "") {
                        pre = "none";
                    }
                    if (notes == " ") {
                        notes = "none";
                    }


  /*

        long id = helper.insertData(Course, Title, pre, department, notes, instructor, email, units, lec_day, lec_time, lab_day, lab_time, t_day, t_time);
                    if (id < 0) {
                        Message.message(Course_Search.this, "UNSUUCESSFUL");
                    } else {
                        Message.message(Course_Search.this, "SUCCCESSFUL");
                    }
*/
                    // HashMap<String, String> contact = new HashMap<String, String>();
                    JSONObject contact = new JSONObject();
                    contact.put(TAG_Course, Course);
                    contact.put(TAG_Title, Title);
                    contact.put(TAG_Pre, pre);
                    contact.put(TAG_Department, department);
                    contact.put(TAG_Notes, notes);
                    contact.put(TAG_Instructor, instructor);
                    contact.put(TAG_Email, email);
                    contact.put(TAG_Units, units);
                    contact.put(TAG_LEC_DAY, lec_day);
                    contact.put(TAG_LEC_TIME, lec_time);
                    contact.put(TAG_LAB_DAY, lab_day);
                    contact.put(TAG_LAB_TIME, lab_time);
                    contact.put(TAG_T_DAY, t_day);
                    contact.put(TAG_T_TIME, t_time);
                    contactList.put(contact);

                    json = lastEntry(i);
                }
                String Course = json.getString(TAG_Course);
                String Title = json.getString(TAG_Title);
                String pre = json.getString(TAG_Pre);
                String department = json.getString(TAG_Department);
                String notes = json.getString(TAG_Notes);
                String instructor = json.getString(TAG_Instructor);
                String email = json.getString(TAG_Email);
                String units = json.getString(TAG_Units);
                String lec_day = json.getString(TAG_LEC_DAY);
                String lec_time = json.getString(TAG_LEC_TIME);
                String lab_day = json.getString(TAG_LAB_DAY);
                String lab_time = json.getString(TAG_LAB_TIME);
                String t_day = json.getString(TAG_T_DAY);
                String t_time = json.getString(TAG_T_TIME);
                if (pre == "") {
                    pre = "none";
                }
                if (notes == "") {
                    notes = "none";
                }

     /*           long id = helper.insertData(Course, Title, pre, department, notes, instructor, email, units, lec_day, lec_time, lab_day, lab_time, t_day, t_time);
                if (id < 0) {
                         Message.message(Course_Search.this, "UNSUUCESSFUL");
                } else {
                       Message.message(Course_Search.this, "SUCCCESSFUL");
                }
*/
                // HashMap<String, String> contact = new HashMap<String, String>();
                JSONObject contact = new JSONObject();
                contact.put(TAG_Course, Course);
                contact.put(TAG_Title, Title);
                contact.put(TAG_Pre, pre);
                contact.put(TAG_Department, department);
                contact.put(TAG_Notes, notes);
                contact.put(TAG_Instructor, instructor);
                contact.put(TAG_Email, email);
                contact.put(TAG_Units, units);
                contact.put(TAG_LEC_DAY, lec_day);
                contact.put(TAG_LEC_TIME, lec_time);
                contact.put(TAG_LAB_DAY, lab_day);
                contact.put(TAG_LAB_TIME, lab_time);
                contact.put(TAG_T_DAY, t_day);
                contact.put(TAG_T_TIME, t_time);
                contactList.put(contact);

 /*                   HashMap<String, String> contact = new HashMap<String, String>();
                    contact.put(TAG_TITLE, title);
                    contact.put(TAG_NAME, name);
                    contact.put(TAG_MID, mid);
                    contact.put(TAG_END, end);
                    contactList.add(contact);
                    json = lastEntry(i);


                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (k == 1) {
                        String s = String.valueOf(mARRAY.length());
                        editor.putString("0", s);
                    }
                    String a = String.valueOf(k);
                    k = k + 1;
                    String b = String.valueOf(k);
                    k = k + 1;
                    String d = String.valueOf(k);
                    k = k + 1;
                    String e = String.valueOf(k);
                    k = k + 1;
                    editor.putString(a, name);
                    editor.putString(b, title);
                    editor.putString(d, mid);
                    editor.putString(e, end);
                    editor.commit();
                String name = json.getString(TAG_NAME);
                String end = json.getString(TAG_END);
                String mid = json.getString(TAG_MID);
                String title = json.getString(TAG_TITLE);
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
                editor.putString(a, name);
                editor.putString(b, title);
                editor.putString(d, mid);
                editor.putString(e, end);
                editor.commit();

                HashMap<String, String> contact = new HashMap<String, String>();
                contact.put(TAG_TITLE, title);
                contact.put(TAG_NAME, name);
                contact.put(TAG_MID, mid);
                contact.put(TAG_END, end);
                contactList.add(contact);*/
                //     Insert();
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
            /**
             * Updating parsed JSON data into ListView
             * */
            // runThread();
            if (j == 0) {
                Toast.makeText(Course_Search.this,
                        "Load Successful", Toast.LENGTH_LONG).show();
            }

        }

    /*    private void runThread() {

            new Thread() {
                public void run() {
                    while (i++ < 1000) {
                        try {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ListAdapter adapter = new SimpleAdapter(
                                            Course_Search.this, contactList,
                                            R.layout.courselist, new String[]{TAG_NAME, TAG_TITLE,
                                            TAG_END, TAG_MID}, new int[]{R.id.name,
                                            R.id.title, R.id.midsem, R.id.endsem});

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
        }*/
    }
}



