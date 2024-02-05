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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kazimasum.qrdemo.data.ProfileData3;

public class loginuser1 extends AppCompatActivity {
    int flag =1;
    EditText e1, e2;
    Button submit;
    TextView t1;
    static students teacher;
    DatabaseReference reference;
    static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginuser1);
        submit = (Button) findViewById(R.id.userbtn);

        e1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        e2 = (EditText) findViewById(R.id.editTextTextPassword2);
        t1= (TextView) findViewById(R.id.frgt1);


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent2 =new Intent (loginuser1.this,frgtpass.class);
//                finish();
//                startActivity(intent2);


            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                if (!s1.isEmpty()) {
                    if (!s2.isEmpty()) {

                        readData(s1, s2);

                    } else {
                        e2.setError("Password required");
                    }
                } else {
                    e1.setError("Username required");
                }

            }
        });

    }

    private void readData(String username, String password) {
        reference = FirebaseDatabase.getInstance().getReference("teachers");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        DataSnapshot dataSnapshot = task.getResult();
                        String user= String.valueOf(dataSnapshot.child("usernm").getValue());
                        String pass = String.valueOf(dataSnapshot.child("password").getValue());
                        String phon = String.valueOf(dataSnapshot.child("phone").getValue());
                        String agee= String.valueOf(dataSnapshot.child("age").getValue());
                        String emaill= String.valueOf(dataSnapshot.child("email").getValue());
                        String datee= String.valueOf(dataSnapshot.child("date").getValue());
                        String genderr= String.valueOf(dataSnapshot.child("gender").getValue());
                        String namee= String.valueOf(dataSnapshot.child("name2").getValue());



                        Log.d("user :", user.toString());
                        //Log.d("pass", pass.toString());
                        Log.d("phone", phon.toString());
                        Log.d("age", agee.toString());
                        Log.d("email", emaill.toString());
                        Log.d("date",datee.toString());
                        Log.d("gender",genderr.toString());
                        Log.d("name2",namee.toString());

                        if (user.equals(username)) {
                            if (pass.equals(password)) {

                                userid = user;

                                /*student.setName(namee);
                                student.setAge(agee);
                                student.setDate(datee);
                                student.setUsernm(user);
                                student.setBranch(branchh);
                                student.setPhone(Long.parseLong(phon));
                                student.setPassword(pass);
                                student.setRoll(rolll);
                                student.setEmail(emaill);
                                student.setGender(genderr);*/



                                Toast.makeText(loginuser1.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),teacherpage1.class);

//                                intent.putExtra("name", namee);
//                                intent.putExtra("usernm", user);
//                                intent.putExtra("phone", phon);
//                                intent.putExtra("password", pass);
//                                intent.putExtra("age", agee);
//                                intent.putExtra("roll",rolll);
//                                intent.putExtra("email", emaill);
//                                intent.putExtra("date", datee);
//                                intent.putExtra("branch", branchh);
//                                intent.putExtra("gender", genderr);
//                                  intent.putExtra("year",  yearr);

                                ProfileData3.setName2(namee);
                                ProfileData3.setAge2(agee);
                                ProfileData3.setDob2(datee);
                                ProfileData3.setGender2(genderr);
                                ProfileData3.setUserName2(user);
                                ProfileData3.setEmail_id2(emaill);
                                ProfileData3.setPhoneNumber2(phon);



                                finish();
                                startActivity(intent);

                            } else {
                                Toast.makeText(loginuser1.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(loginuser1.this, "Enter Valid Username", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(loginuser1.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}