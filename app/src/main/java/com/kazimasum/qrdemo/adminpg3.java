package com.kazimasum.qrdemo;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class adminpg3 extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton2;

    EditText name1, phone1, email1,  age1, user1, password1,password2;
    Button submit, clear;
    Spinner year,branch;
    teachers ts;
    long maxid;
    RadioGroup rg;

    RadioButton rb;
    DatabaseReference databaseReference2;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpg3);

        submit = (Button) findViewById(R.id.tsubmit);
        clear = (Button) findViewById(R.id.tclear);
        name1 = (EditText) findViewById(R.id.tname);
        phone1 = (EditText) findViewById(R.id.tnumber);
        password1 = (EditText) findViewById(R.id.tpass);
        password2 = (EditText) findViewById(R.id.tcpass);

        age1 = (EditText) findViewById(R.id.tage);
        email1 = (EditText) findViewById(R.id.taddress);
        user1 = (EditText) findViewById(R.id.tusername);

        rg=(RadioGroup)findViewById(R.id.radio2);
        ImageView bck2 = (ImageView) findViewById(R.id.backbutton1);

        bck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }

        });

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("teachers");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Intent i = new Intent(adminpg3.this,adminpg3.class);
                startActivity(i);
                finish();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    name1.setText(null);
                    phone1.setText(null);
                    password1.setText(null);
                    password2.setText(null);
                    age1.setText(null);
                    email1.setText(null);
                    user1.setText(null);

                    i = rg.getCheckedRadioButtonId();
                    rb = findViewById(i);
                    rb.setChecked(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });




        ts = new teachers();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = name1.getText().toString().trim();
                String b = email1.getText().toString().trim();
                String c = phone1.getText().toString().trim();
                String d = age1.getText().toString().trim();
                String e = password1.getText().toString().trim();
                String f = user1.getText().toString().trim();
                String g = password2.getText().toString().trim();

                i= rg.getCheckedRadioButtonId();
                rb=findViewById(i);
                String ab=rb.getText().toString().trim();


                String z = "";
                if ( a.equals(z)||b.equals(z)||c.equals(z)||d.equals(z)||e.equals(z)||f.equals(z)||g.equals(z)||ab.equals(z)) {
                    Toast.makeText(adminpg3.this, "Invalid details", Toast.LENGTH_SHORT).show();


                }
                else if (e.equals(g)){
                    ts.setName2(name1.getText().toString().trim());

                    ts.setPhone(Long.parseLong(phone1.getText().toString().trim()));
                    ts.setEmail(email1.getText().toString().trim());
                    ts.setAge(age1.getText().toString().trim());
                    ts.setUsernm(user1.getText().toString().trim());
                    ts.setPassword(password1.getText().toString().trim());

                    ts.setDate(dateButton2.getText().toString().trim());

                    ts.setGender(ab);
                    //long id=Long.parseLong(f);
                    databaseReference2.child(f).setValue(ts);
                    Toast.makeText(adminpg3.this, "Added data succesfully", Toast.LENGTH_SHORT).show();

                }
                else  {

                    Toast.makeText(adminpg3.this, "password does not match", Toast.LENGTH_SHORT).show();

                }
            }
        });

        initDatePicker();
        dateButton2 = findViewById(R.id.dob);
        dateButton2.setText(getTodaysDate());
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton2.setText(date);
            }
        };
        Calendar cal1 = Calendar.getInstance();
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year1, month1, day1);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat2(month) + " " + day + " " + year;
    }

    private String getMonthFormat2(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}