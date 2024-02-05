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

public class tremove extends AppCompatActivity {
    EditText t2;
    Button b3, b4,b5;
    TextView nt;
    DatabaseReference reference5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tremove);
        t2 = (EditText) findViewById(R.id.Enteruser);
        nt = (TextView) findViewById(R.id.nameofs);
        b3 = (Button) findViewById(R.id.Search3);
        b4 = (Button) findViewById(R.id.Remove2);
        b5 = (Button) findViewById(R.id.clear3);
        ImageView bck3 = (ImageView) findViewById(R.id.backbutton3);

        bck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }

        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nt.setText(null);
                t2.setText(null);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = t2.getText().toString().trim();
                if (!s1.isEmpty()) {
                    readData(s1);

                } else {
                    t2.setError("Details required");
                }
            }
        });
    }

    private void readData(String username) {
        reference5 = FirebaseDatabase.getInstance().getReference("teachers");
        reference5.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        DataSnapshot dataSnapshot = task.getResult();


                        String namee = String.valueOf(dataSnapshot.child("name2").getValue());
                        String unamee = String.valueOf(dataSnapshot.child("usernm").getValue());

                        Log.d("name2", namee.toString());
                        Log.d("usernm", unamee.toString());



                        if(unamee.equals(username)){

                            nt.setText(namee);
                        }

                    }
                    else
                    {

                        Toast.makeText(tremove.this, "Teacher not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("teachers").orderByChild("usernm").equalTo(username);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(tremove.this, "Teacher Deleted", Toast.LENGTH_SHORT).show();
                nt.setText(null);
                t2.setText(null);

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
