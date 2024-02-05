package com.kazimasum.qrdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kazimasum.qrdemo.data.ProfileData3;

public class Pass_chng extends AppCompatActivity {
    Button   chngpss ;
    EditText e1,e2;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_chng);
        chngpss = (Button) findViewById(R.id.cnfm);
        e1 = (EditText) findViewById(R.id.Passwrd);
        e2 = (EditText) findViewById(R.id.cnpass);
        ImageView bck1 = (ImageView) findViewById(R.id.backbutton9);

        bck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        chngpss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "";
                if (e1.equals(a) || e2.equals(a)) {

                    Toast.makeText(Pass_chng.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Pass_chng.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                }

            }

        });
}
}
//            public void onClick(View view) {
//                String s1 = e1.getText().toString();
//                String s2 = e2.getText().toString();
//
//                if (!s1.isEmpty()){
//                    if (!s2.isEmpty())
//                        {
//
//                            readData(s1, s2);
//
//                        } else{
//                            e1.setError("Password required");
//                        }
//                    }
//            }
//        });
//    }
//    private void readData( String password ,String new_password) {
//        reference = FirebaseDatabase.getInstance().getReference("teachers");
//
//        reference.child(password).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    if (task.getResult().exists()) {
//
//                        DataSnapshot dataSnapshot = task.getResult();
//
//                        String pass = String.valueOf(dataSnapshot.child("password").getValue());
//
//                        Query applesQuery =  reference.child("teachers").orderByChild("password").equalTo(password);
//                            if (pass.equals(password)) {
//                                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
//                                            appleSnapshot.getRef().setValue(pass);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//
//
//                                });
//
//                            }
//
//
//                    } else {
//                        Toast.makeText(Pass_chng.this, "Password is not proper", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//}