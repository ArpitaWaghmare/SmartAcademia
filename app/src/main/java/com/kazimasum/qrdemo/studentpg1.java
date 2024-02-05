package com.kazimasum.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class studentpg1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpg1);
        ImageView lg = (ImageView) findViewById(R.id.log);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();


            }

        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("usernm");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phoneNo = intent.getStringExtra("phone");
        String password = intent.getStringExtra("password");
        String Gender = intent.getStringExtra("gender");
        String Branch = intent.getStringExtra("branch");
        String Roll = intent.getStringExtra("roll");
        String Dob = intent.getStringExtra("date");
        String Year = intent.getStringExtra("year");
        String Age= intent.getStringExtra("age");

        ImageView scan = (ImageView) findViewById(R.id.scanner1);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(studentpg1.this, scannerView.class);
                startActivity(i);
            }
        });

        ImageView attend = (ImageView) findViewById(R.id.attend);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(studentpg1.this, ViewAttendanceStudent.class);
                startActivity(i);
            }
        });
        ImageView sprofile = (ImageView) findViewById(R.id.profile1);
        sprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(studentpg1.this, sprofile.class);
                intent.putExtra("name", name);
                intent.putExtra("usernm", username);
                intent.putExtra("phone", phoneNo);
                intent.putExtra("password", password);
                intent.putExtra("age", Age);
                intent.putExtra("roll",Roll);
                intent.putExtra("email", email);
                intent.putExtra("date", Dob);
                intent.putExtra("branch", Branch);
                intent.putExtra("gender", Gender);
                intent.putExtra("year",  Year);
                startActivity(i);

            }
        });
    }
}