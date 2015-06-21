package com.example.dell1.materialdesign;

/**
 * Created by DELL1 on 6/18/2015.
 */
import android.content.Context;
import android.widget.Toast;

/**
 * Created by DELL1 on 6/17/2015.
 */
public class Message {
    public static void message(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}

