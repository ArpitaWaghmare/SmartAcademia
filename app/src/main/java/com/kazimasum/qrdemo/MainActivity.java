package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getSupportActionBar().hide();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        Thread thread=new Thread(){
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally{
                    Intent intent =new Intent (MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();
    }
}




