package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button adminbtn,login1,login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adminbtn=findViewById(R.id.adlogin);
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Login.this, Adminlogin.class);
                startActivity(intent);
            }
        });
        login1=findViewById(R.id.tlogin);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 =new Intent (Login.this, loginuser2.class);
                startActivity(intent2);
            }
        });
        login2=findViewById(R.id.slogin);
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 =new Intent (Login.this, loginuser1.class);
                startActivity(intent2);
            }
        });
    }
}




