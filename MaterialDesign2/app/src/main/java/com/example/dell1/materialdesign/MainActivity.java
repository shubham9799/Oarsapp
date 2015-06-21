package com.example.dell1.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolbar;
    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;


    ListView list;
    String[] web = {
            "OARS",
            "SNT",
            "Antaragini",
            "Techkriti",
            "Course Search"

    };
    Integer[] imageId = {
            R.drawable.logo_black,
            R.drawable.snto,
            R.drawable.antaragni,
            R.drawable.tnb,
            R.drawable.searchgreenicon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Main");
        setSupportActionBar(toolbar);


        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (com.example.dell1.materialdesign.NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        selectItem(position);
    }

    public void Timetable(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(MainActivity.this,
                "TimeTable", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Timetable_offline.class);
        startActivity(i);
    }

    public void Course(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(MainActivity.this,
                "Course", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, course_offline.class);
        startActivity(i);
    }

    public void login(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(MainActivity.this,
                "OARS Login", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Oars_login.class);
        startActivity(i);
    }
    public void help(View view){
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(MainActivity.this,
                "About App", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, help.class);
        startActivity(i);
    }
    public void search(View view) {
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Toast.makeText(MainActivity.this,
                "Advanced Course Search", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, advanced_course_search.class);
        startActivity(i);
    }

    public void selectItem(int position) {

        list.setItemChecked(position, true);
        if (position == 0) {
            Intent i = new Intent(this, Oars_login.class);
            startActivity(i);

        }

        if (position == 4) {
            Toast.makeText(MainActivity.this,
                    "Advanced Course Search", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, advanced_course_search.class);
            startActivity(i);
        }
    }


}
