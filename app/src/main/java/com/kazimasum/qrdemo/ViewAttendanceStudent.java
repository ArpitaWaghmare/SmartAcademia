package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kazimasum.qrdemo.data.ProfileData;

public class ViewAttendanceStudent extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentRecyclerAdapter adapter;
    private StudentRecyclerAdapter.RecyclerViewClickListener listener;

    //ArrayList<String> subjectlist = new ArrayList<>();
    //String[] firstyr = {"AJP", "C", "C++", "CSCL", "DBMS", "DS", "EDP", "Java", "Python", "ST"};
    String[] firstyr = {"C", "MATH", "PHYSICS", "CHEMISTRY", "FCIT", "ENGLISH","BCS"};
    String[] secondyr = {"C++", "PYTHON", "DS", "DBMS", "MPP", "SE"};
    String[] thirdyr = {"JAVA", "AJP", "EDP", "ST", "CSCL", "ADBMS"};
    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_student);
        //getSupportActionBar().hide();

        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(snapshot1.getKey().equals(loginuser2.userid))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            if(snapshot2.getKey().equals("year"))
                            {
                                year = snapshot2.getKey();
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

         year = ProfileData.getYear();

        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    subjectlist.add(snapshot1.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        switch (year)
        {
            case "1st" : adapter = new StudentRecyclerAdapter(this, firstyr, listener);
            break;

            case "2nd" :  adapter = new StudentRecyclerAdapter(this, secondyr, listener);
                break;

            case "3rd" :  adapter = new StudentRecyclerAdapter(this, thirdyr, listener);
                break;

        }


        //adapter = new StudentRecyclerAdapter(this, firstyr, listener);
        recyclerView.setAdapter(adapter);
    }


    private void setOnClickListener() {

        listener = new StudentRecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),ViewAttendanceStudent2.class);


                switch (year)
                {
                    case "1st" :
                        intent.putExtra("subject",firstyr[position]);
                        break;

                    case "2nd" :
                        intent.putExtra("subject",secondyr[position]);
                        break;

                    case "3rd" :
                        intent.putExtra("subject",thirdyr[position]);
                        break;

                }



                //intent.putExtra("subject", firstyr[position]);
                startActivity(intent);
            }
        };


    }

}


