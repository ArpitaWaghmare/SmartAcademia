package com.kazimasum.qrdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.kazimasum.qrdemo.data.ProfileData;

public class sprofile extends AppCompatActivity {
    EditText Name, Phone, Email, Age, User, Password, Branchh, Yearr, DofB, Roll, Gender;
    Button chngpass;
    students stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprofile);

        Name = (EditText) findViewById(R.id.n1);

        chngpass = (Button) findViewById(R.id.change);
        Phone = (EditText) findViewById(R.id.p1);
        Email = (EditText) findViewById(R.id.e1);
        Age = (EditText) findViewById(R.id.editTextTextPersonName6);
        User = (EditText) findViewById(R.id.editTextTextPersonName9);
        Yearr = (EditText) findViewById(R.id.editTextTextPersonName10);
        Gender = (EditText) findViewById(R.id.editTextTextPersonName8);

        DofB = (EditText) findViewById(R.id.editTextTextPersonName4);
        Branchh = (EditText) findViewById(R.id.editTextTextPersonName5);
        Roll = (EditText) findViewById(R.id.editTextTextPersonName7);

        Name.setEnabled(false);
        Phone.setEnabled(false);
        Age.setEnabled(false);
        Yearr.setEnabled(false);
        Gender.setEnabled(false);
        DofB.setEnabled(false);
        Branchh.setEnabled(false);
        Roll.setEnabled(false);
        Email.setEnabled(false);
        User.setEnabled(false);

        Log.e("chk", ProfileData.getName());
        ImageView bck4 = (ImageView) findViewById(R.id.backbutton6);

        bck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        chngpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 =new Intent (sprofile.this, Pass_chng.class);
                finish();
                startActivity(intent2);



            }
        });
        showAllUserData();


    }

    @SuppressLint("SetTextI18n")
    private void showAllUserData() {

        Phone.setText(ProfileData.getPhoneNumber());
        User.setText(ProfileData.getUserName());
        Age.setText(ProfileData.getAge());
        Roll.setText(ProfileData.getEnrollNo());
        Email.setText(ProfileData.getEmail_id());
        DofB.setText(ProfileData.getDob());
        Branchh.setText(ProfileData.getBranch());
        Gender.setText(ProfileData.getGender());
        Name.setText(ProfileData.getName());
        Yearr.setText(ProfileData.getYear());


    }
}

