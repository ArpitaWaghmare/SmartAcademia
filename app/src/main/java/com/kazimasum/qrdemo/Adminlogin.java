package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Adminlogin extends AppCompatActivity {
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        TextView username = (TextView) findViewById(R.id.editTextNumber3);
        TextView password = (TextView) findViewById(R.id.editTextTextPassword);

        Button lognbtn1 = (Button) findViewById(R.id.button);

        lognbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("12345")&& password.getText().toString().equals("admin"))
                {

                    Intent intent =new Intent (Adminlogin.this, adminpg.class);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Details",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }






}