package com.example.dell1.oarsapp;

import android.content.Intent;
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
import android.widget.Toast;

public class Oars_login extends ActionBarActivity{
    private Toolbar toolbar;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oars_login);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("login");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
                return  super.onCreateOptionsMenu(menu);
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
        if(id==android.R.id.home){
            Intent i=new Intent(this,NavigationDrawerFragment.class);
            NavUtils.navigateUpTo(this,i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view){
        Toast.makeText(Oars_login.this,
                "Login", Toast.LENGTH_LONG).show();

        final Animation animation= AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        view.startAnimation(animation);
        Intent i=new Intent(this,OarsClass.class);
        startActivity(i);
    }

    public void runThread() {

        new Thread() {
            public void run() {
                while (i++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {


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
