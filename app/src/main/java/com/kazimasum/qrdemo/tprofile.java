package com.kazimasum.qrdemo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kazimasum.qrdemo.data.ProfileData3;

public class tprofile extends AppCompatActivity {
    EditText Name, Phone, Email, Age, User, DofB,  Gender;
    Button chngpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tprofile);

        Name = (EditText) findViewById(R.id.nn1);

        chngpass=(Button) findViewById(R.id.tchange);
        Phone = (EditText) findViewById(R.id.pp1);
        Email = (EditText) findViewById(R.id.ee1);
        Age = (EditText) findViewById(R.id.editTextTextPersonName26);
        User = (EditText) findViewById(R.id.editTextTextPersonName29);

        Gender = (EditText) findViewById(R.id.editTextTextPersonName28);

        DofB = (EditText) findViewById(R.id.editTextTextPersonName24);


        Name.setEnabled(false);
        Phone.setEnabled(false);
        Age.setEnabled(false);

        Gender.setEnabled(false);
        DofB.setEnabled(false);

        Email.setEnabled(false);
        User.setEnabled(false);
        ImageView bck4 = (ImageView) findViewById(R.id.backbutton5);

        bck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });


        chngpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 =new Intent (tprofile.this, Pass_chng.class);
                finish();
                startActivity(intent2);


            }
        });
        showAllUserData2();


    }


    private void showAllUserData2() {
        /*Intent intent = getIntent();
        String user_username = intent.getStringExtra("usernm");
        String user_name = intent.getStringExtra("name2");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phone");
        String user_password = intent.getStringExtra("password");
        String user_Gender = intent.getStringExtra("gender");


        String user_Dob = intent.getStringExtra("date");

        String user_Age= intent.getStringExtra("age");*/


        Phone.setText(ProfileData3.getPhoneNumber2());
        User.setText(ProfileData3.getUserName2());
        Age.setText(ProfileData3.getAge2());
        Email.setText(ProfileData3.getEmail_id2());
        DofB.setText(ProfileData3.getDob2());
        Gender.setText(ProfileData3.getGender2());
        Name.setText(ProfileData3.getName2());



    }
}