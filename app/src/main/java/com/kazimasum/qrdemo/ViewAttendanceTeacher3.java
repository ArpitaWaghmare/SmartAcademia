package com.kazimasum.qrdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAttendanceTeacher3 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapterEnrollment recyclerAdapterEnrollment;

    ArrayList<Student_info> enrollments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_teacher3);

        TextView date = (TextView) findViewById(R.id.date);
        TextView sub = (TextView) findViewById(R.id.sub);

        String d = "date not set";
        String s = "subject not set";

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            d = extras.getString("Date");
            s = extras.getString("Subject");

            Log.d("tag","s :" + s);
            Log.d("tag","d :" + d);
        }

        date.setText(d);
        sub.setText(s);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        String finalS = s;
        String finalD = d;

        Log.d("mil gaya","s : " + finalS + "=== d : " + finalD);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("mil gaya","found" + snapshot.getKey());
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    Log.d("mil gaya","snapshot1 for" + snapshot1.getKey());

                    if (snapshot1.getKey().equals(finalS))
                    {
                        Log.d("mil gaya","snapsot1 if" );

                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            if(snapshot2.getKey().equals(finalD)) {
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()) {
                                    try {
                                        Log.e("tsg", snapshot3.getKey());
                                        Student_info student_info = snapshot3.getValue(Student_info.class);

                                        enrollments.add(student_info);
                                    }
                                    catch (Exception e)
                                    {
                                        Log.e("tag","exception");
                                    }

                                }
                            }



                            /*
                            Student_info student_info = snapshot2.getValue(Student_info.class);
                            Log.d("================================", "student_info : " + student_info.getSenroll());
                            enrollments.add(student_info);

                             */

                            /*
                            Log.d("mil gaya","snapshot2 for" + snapshot2.getKey());
                            if (snapshot2.getKey()==finalD)
                            {
                                Log.d("mil gaya","snapsot2 if" );
                                for (DataSnapshot snapshot3:snapshot2.getChildren())
                                {
                                   Log.d("mil gaya","snapshot3 for" + snapshot3.getKey());

                                    try{
                                        Log.d("mil gaya","tryyyyyyyy" + snapshot3.getKey());


                                        Log.d("***************mil gaya","subject" + finalS);
                                        Log.d("***************mil gaya","date" + finalD);
                                        Log.d("***************mil gaya","subject2" + snapshot1.getKey());
                                        Log.d("***************mil gaya","date2" + snapshot2.getKey());

                                        //if(snapshot1.getKey().equals(finalS) && snapshot2.equals(finalD)) {
                                            Long enroll = Long.parseLong(snapshot3.getKey());
                                            Log.d("mil gaya", "=========try========" + snapshot3.getKey());
                                            enrollments.add(snapshot3.getKey());
                                        //}
                                    }
                                    catch (Exception e){}




//                                    if(snapshot1.getKey().equals(finalS) && snapshot2.equals(finalD))
//                                    {
//                                        Log.d("mil gaya","if1 found" + snapshot2.getKey());
//                                        if (snapshot3.getKey().equals("date") || snapshot3.getKey().equals("subject") || snapshot3.getKey().equals("time")) {}
//                                        else{
//                                            Log.d("mil gaya", "if2 found" + snapshot3.getKey());
//                                            enrollments.add(snapshot3.getKey());
//                                        }
//                                    }
                                }
                            }
                            */
                        }
                    }
                }

                recyclerAdapterEnrollment.notifyDataSetChanged();

                Log.d("mil gaya ---", enrollments.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerAdapterEnrollment = new RecyclerAdapterEnrollment(this,enrollments);
        recyclerView.setAdapter(recyclerAdapterEnrollment);



    }
}