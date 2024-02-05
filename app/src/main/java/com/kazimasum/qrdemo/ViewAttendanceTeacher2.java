package com.kazimasum.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class ViewAttendanceTeacher2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapterDates adapter;
    private RecyclerAdapterDates.RecyclerViewClickListener listener;
    String FinalSubject;

    ArrayList<String> dates = new ArrayList<>();
    //ArrayList<Attendance> s = new ArrayList<>();
    DatabaseReference reff;
    HashSet<Attendance> hashSet = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_teacher2);
        //getSupportActionBar().hide();

        TextView sub = findViewById(R.id.sub);
        String subject = "sub not found";
        Bundle extras = getIntent().getExtras();

        if(extras!=null)
        {
            subject = extras.getString("subject");
        }

        sub.setText(subject);

        reff = FirebaseDatabase.getInstance().getReference("Attendance");

        FinalSubject = subject;

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(snapshot1.getKey().equals(FinalSubject))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            dates.add(snapshot2.getKey());
                        }
                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapterDates(this, dates, listener);
        recyclerView.setAdapter(adapter);


    }


    private void setOnClickListener() {

        listener = new RecyclerAdapterDates.RecyclerViewClickListener()
        {

            @Override
            public void onClick(View v, int position) {

                Bundle extras = getIntent().getExtras();


                Log.e("tag","sub" + FinalSubject);
                Log.e("tag","date " + dates.get(position));



                Intent intent = new Intent(getApplicationContext(),ViewAttendanceTeacher3.class);
                intent.putExtra("Subject", FinalSubject);
                intent.putExtra("Date",dates.get(position));
                startActivity(intent);
            }
        };


    }




}






/*
package com.kazimasum.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAttendanceTeacher2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapterDates adapter;
    private RecyclerAdapterDates.RecyclerViewClickListener listener;

    ArrayList<String> dates = new ArrayList<>();
    static ArrayList<String > en = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_teacher2);

        TextView sub =  findViewById(R.id.sub);

        String subject = "sub not set";

        Bundle extras = getIntent().getExtras();

        if (extras!=null)
        {
            subject = extras.getString("subject");

        }

        sub.setText(subject);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        String finalSubject = subject;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(snapshot1.getKey().equals(finalSubject))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren()) {
                            dates.add(snapshot2.getKey());

                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapterDates(this, dates, listener);
        recyclerView.setAdapter(adapter);


    }


    private void setOnClickListener() {

        listener = (v, position) -> {

            Bundle extras = getIntent().getExtras();
            String sub = "Subject";

            if (extras!=null)
            {
                sub = extras.getString("subject");

            }

            Intent intent = new Intent(getApplicationContext(),ViewAttendanceTeacher3.class);
            intent.putExtra("date",dates.get(position));
            intent.putExtra("subject",sub);
            startActivity(intent);

        };


    }




}

 */

