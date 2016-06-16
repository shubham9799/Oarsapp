package com.example.dell1.oarsapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class search_result extends ActionBarActivity {

    public Toolbar toolbar;
    public String s;
    Cursor cursor;
    DatabaseAdapter helper;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Search Result");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DatabaseAdapter(this);
        Intent intent=getIntent();
        int z=intent.getIntExtra("0",1);
        s=intent.getStringExtra("1");

        if(z==1){
            cursor= helper.getData_course(s);
        }
        if(z==2){
            cursor=helper.getData_title(s);
        }
        if(z==3){
            cursor=helper.getData_depart(s);
        }
        if(z==4){
            cursor=helper.getData_ins(s);
        }
        if(z==5){
            cursor=helper.getData_lec(s);
        }
        if(z==6){
            cursor=helper.getData_lab(s);
        }
        if(z==7){
            cursor=helper.getData_lec_day(s);
        }
        if(z==8){
            cursor=helper.getData_lab_day(s);
        }
        if(z==9){
            cursor=helper.getAllData();
        }
        String[] COLUMNS=new String[]{Helper.Course,Helper.Title,Helper.pre,Helper.department,Helper.notes,Helper.instructor,Helper.email,Helper.units,Helper.lec_day,Helper.lec_time,Helper.lab_day,Helper.lab_time,Helper.t_day,Helper.t_time};
        int[] to = new int[] { R.id.textView10, R.id.textView11, R.id.textView12, R.id.textView13, R.id.textView14, R.id.textView15, R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19, R.id.textView20, R.id.textView21, R.id.textView22, R.id.textView23 };
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.search_list, cursor, COLUMNS, to);
        ListView list=(ListView)findViewById(R.id.list1);
        list.setAdapter(mAdapter);

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

}

