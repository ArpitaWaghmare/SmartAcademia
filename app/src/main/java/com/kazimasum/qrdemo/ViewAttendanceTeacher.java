package com.kazimasum.qrdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class ViewAttendanceTeacher extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    ArrayList<String> s = new ArrayList<>();
    //ArrayList<Attendance> s = new ArrayList<>();
    DatabaseReference reff;
    HashSet<Attendance> hashSet = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_teacher);
        //getSupportActionBar().hide();

        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(this, s, listener);
        recyclerView.setAdapter(adapter);

        reff = FirebaseDatabase.getInstance().getReference("Attendance");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Attendance attendance = dataSnapshot.getValue(Attendance.class);
                    s.add(dataSnapshot.getKey());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void setOnClickListener() {

        listener = new RecyclerAdapter.RecyclerViewClickListener()
        {

            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),ViewAttendanceTeacher2.class);
                intent.putExtra("subject",s.get(position));
                startActivity(intent);
            }
        };


    }




}


