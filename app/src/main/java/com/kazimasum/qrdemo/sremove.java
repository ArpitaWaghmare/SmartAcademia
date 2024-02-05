package com.kazimasum.qrdemo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class sremove extends AppCompatActivity {
    EditText t1;
    TextView n,r,b,y;
    Button b1,b2,b3;
    DatabaseReference reference3;
    boolean student_found = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sremove);
        t1 = (EditText) findViewById(R.id.Enteruser);
        n = (TextView) findViewById(R.id.nameofs);
        b = (TextView) findViewById(R.id.branchofs);
        r = (TextView) findViewById(R.id.Rollofs);
        y = (TextView) findViewById(R.id.yearofs);
        b1 = (Button) findViewById(R.id.Search3);
        b2 = (Button) findViewById(R.id.Remove);
        b3 = (Button) findViewById(R.id.clear4);
        ImageView bck4 = (ImageView) findViewById(R.id.backbutton4);

        bck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(sremove.this,Login.class);
                finish();
            }

        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                n.setText(null);
                r.setText(null);
                b.setText(null);
                y.setText(null);
                t1.setText(null);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = t1.getText().toString();
                if (!s1.isEmpty()) {
                    readData(s1);

                } else{
                    t1.setError("Enrollment no. required");
                }
            }
        });
    }
    private void readData(String username) {
        reference3 = FirebaseDatabase.getInstance().getReference("students");
        reference3.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        DataSnapshot dataSnapshot = task.getResult();

                        String branchh = String.valueOf(dataSnapshot.child("branch").getValue());
                        String rolll = String.valueOf(dataSnapshot.child("roll").getValue());
                        String namee = String.valueOf(dataSnapshot.child("name").getValue());
                        String yearr = String.valueOf(dataSnapshot.child("year").getValue());
                        String usernme = String.valueOf(dataSnapshot.child("usernm").getValue());

                        Log.d("roll", rolll.toString());
                        Log.d("branch", branchh.toString());
                        Log.d("year", yearr.toString());
                        Log.d("name", namee.toString());

                        if (usernme.equals(username)) {
                            n.setText(namee);
                            b.setText(branchh);
                            y.setText(yearr);
                            r.setText(rolll);

                            student_found=true;
                            Toast.makeText(sremove.this, "Student  found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {

                        Toast.makeText(sremove.this, "Student not found", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("students").orderByChild("usernm").equalTo(username);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(sremove.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                n.setText(null);
                b.setText(null);
                r.setText(null);
                y.setText(null);
                t1.setText(null);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    }



