package com.kazimasum.qrdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class adminpg2 extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    EditText name1, phone1, email1, roll1, age1, user1, password1,password2;
    Button submit,clear;
    Spinner year,branch;
    students s;
    long id;
    RadioGroup rg;
    RadioButton rb;
    DatabaseReference databaseReference;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpg2);

        clear= (Button) findViewById(R.id.button5);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    name1.setText(null);
                    phone1.setText(null);
                    email1.setText(null);
                    roll1.setText(null);
                    age1.setText(null);
                    user1.setText(null);
                    password1.setText(null);
                    password2.setText(null);
                    branch.setSelection(0);
                    year.setSelection(0);

                    i = rg.getCheckedRadioButtonId();
                    rb = findViewById(i);
                    rb.setChecked(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        submit = (Button) findViewById(R.id.button4);

        name1 = (EditText) findViewById(R.id.editTextTextPersonName3);
        phone1 = (EditText) findViewById(R.id.editTextNumber);

        password1 = (EditText) findViewById(R.id.editTextNumberPassword2);
        password2 = (EditText) findViewById(R.id.editTextNumberPassword3);
        roll1 = (EditText) findViewById(R.id.editTextNumber2);
        age1 = (EditText) findViewById(R.id.editTextNumber4);
        email1 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        user1 = (EditText) findViewById(R.id.editTextNumber5);
        year= (Spinner) findViewById(R.id.spinner);
        branch = (Spinner) findViewById(R.id.spinner2);
        rg=(RadioGroup)findViewById(R.id.radio);
        ImageView bck1 = (ImageView) findViewById(R.id.backbutton2);

        bck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    id = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        s = new students();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String a = name1.getText().toString().trim();
                    String b = email1.getText().toString().trim();
                    String c = phone1.getText().toString().trim();
                    String d = age1.getText().toString().trim();
                    String h = year.getSelectedItem().toString().trim();
                    String j = branch.getSelectedItem().toString().trim();
                    String f = user1.getText().toString().trim();
                    String e = password1.getText().toString().trim();
                    String g = password2.getText().toString().trim();

                    i = rg.getCheckedRadioButtonId();
                    rb = findViewById(i);
                    String ab = rb.getText().toString().trim();


                    String z = "";
                    if (a.equals(z) || b.equals(z) || c.equals(z) || d.equals(z) || e.equals(z) || f.equals(z) || g.equals(z) || h.equals(z) ||
                            j.equals(z) || ab.equals(z)) {
                        Toast.makeText(adminpg2.this, "Invalid details", Toast.LENGTH_SHORT).show();
                    } else if (e.equals(g)) {
                        s.setName(name1.getText().toString().trim());

                        s.setPhone(Long.parseLong(phone1.getText().toString().trim()));
                        s.setEmail(email1.getText().toString().trim());
                        s.setAge(age1.getText().toString().trim());
                        s.setUsernm(user1.getText().toString().trim());
                        s.setPassword(password1.getText().toString().trim());
                        s.setRoll(roll1.getText().toString().trim());
                        s.setDate(dateButton.getText().toString().trim());
                        s.setYear(j);
                        s.setBranch(h);
                        s.setGender(ab);
                        id = Long.parseLong(f);
                        databaseReference.child(String.valueOf(id)).setValue(s);
                        Toast.makeText(adminpg2.this, "Added data succesfully", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(adminpg2.this, "password does not match", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(adminpg2.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        Spinner spinnerBranch = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerBranch.setAdapter(adapter);

        Spinner spinneryear = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinneryear.setAdapter(adapter2);
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
                String date1 = makeDateString(day, month, year);
                dateButton.setText(date1);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
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

