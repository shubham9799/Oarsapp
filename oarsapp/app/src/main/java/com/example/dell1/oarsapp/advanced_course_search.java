package com.example.dell1.oarsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class advanced_course_search extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private ProgressDialog pDialog;
    public Toolbar toolbar;
    DatabaseAdapter helper;
    Helper helper2;
    EditText course;
    // JSON Node names
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

    public static final String DEFAULT = "N/A";
    int y = 0;
    public String S;
    public int index = 1;
    // contacts JSONArray


    // Hashmap for ListView
    JSONArray mARRAY;
    JSONObject json;
    //contacts JSONArray
    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;
    private Spinner spinner;
    private static final String[] paths = {"Course", "Title", "Department", "Instructor", "LEC TIME", "LAB TIME", "LEC DAY", "LAB DAY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_course_search);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Advanced Course Search");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        course = (EditText) findViewById(R.id.course);
        contactList = new ArrayList<HashMap<String, String>>();


        helper2 = new Helper(this);
        helper = new DatabaseAdapter(this);
        SQLiteDatabase sqLiteDatabase = helper2.getWritableDatabase();

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(advanced_course_search.this,
                android.R.layout.simple_spinner_item, paths);
        //ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.spinnerarray,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //adapter.setDropDownViewResource();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                index = 1;
                course.setHint("Example :- MTH102A");

                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                index = 2;
                course.setHint("Example :- MATHEMATICS-II");
                break;
            case 2:
                // Whatever you want to happen when the third item gets selected
                index = 3;
                course.setHint("Example :- MTH");
                break;

            case 3:
                // Whatever you want to happen when the fourth item gets selected
                index = 4;
                course.setHint("");
                break;

            case 4:
                // Whatever you want to happen when the thrid item gets selected
                index = 5;
                course.setHint("Example :- 10:00-12:00");
                break;

            case 5:
                // Whatever you want to happen when the thrid item gets selected
                index = 6;
                course.setHint("Example :- 09:00-11:00");
                break;
            case 6:
                // Whatever you want to happen when the thrid item gets selected
                index = 7;
                course.setHint("Example :- MTTh");
                break;
            case 7:
                // Whatever you want to happen when the thrid item gets selected
                index = 8;
                course.setHint("Examle:- W");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void getData(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(advanced_course_search.this,
                "ALL DATA", Toast.LENGTH_LONG).show();
        S = course.getText().toString();
        Intent i = new Intent(this, search_result.class);
        i.putExtra("1", S);
        i.putExtra("0", 9);
        startActivity(i);

    }

    public void search(View view) {

        S = course.getText().toString();
        Intent i = new Intent(this, search_result.class);
        i.putExtra("1", S);
        i.putExtra("0", index);
        startActivity(i);

    }

    public void upgrade(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(advanced_course_search.this,
                "UPGRADE", Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, Course_Search.class);
        startActivity(i);
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
            Intent i = new Intent(this, MainActivity.class);
            NavUtils.navigateUpTo(this, i);
        }
        return super.onOptionsItemSelected(item);
    }


}
