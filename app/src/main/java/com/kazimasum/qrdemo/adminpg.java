package com.kazimasum.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class adminpg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpg);
        ImageView i = (ImageView) findViewById(R.id.add);
        ImageView i2 = (ImageView) findViewById(R.id.remove);
        ImageView i3 = (ImageView) findViewById(R.id.stadd);
        ImageView i4 = (ImageView) findViewById(R.id.stremove);

        ImageView lg1 = (ImageView) findViewById(R.id.logout1);
        lg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminpg.this, Adminlogin.class);
                startActivity(intent);
                finish();


            }

        });

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminpg.this, adminpg3.class);
                startActivity(intent);


            }

        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminpg.this, tremove.class);
                startActivity(intent);


            }

        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminpg.this, adminpg2.class);
                startActivity(intent);


            }

        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminpg.this, sremove.class);
                startActivity(intent);


            }

        });
    }
}