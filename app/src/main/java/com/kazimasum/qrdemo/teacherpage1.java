package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class teacherpage1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherpage1);
        ImageView lg1 = (ImageView) findViewById(R.id.log2);

        lg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }

        });

        ImageView Tattendance = (ImageView) findViewById(R.id.bus);
        Tattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacherpage1.this, GenerateQRCode.class);
                startActivity(i);
            }
        });

        ImageView VAttendance = (ImageView) findViewById(R.id.view_attendance);
        VAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacherpage1.this, ViewAttendanceTeacher.class);
                startActivity(i);
            }
        });
        ImageView Tprofile = (ImageView) findViewById(R.id.pr);
        Tprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(teacherpage1.this, tprofile.class);
                startActivity(i);
            }
        });
    }
}