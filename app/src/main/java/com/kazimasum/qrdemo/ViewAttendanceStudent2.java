package com.kazimasum.qrdemo;

import android.os.Bundle;
import android.util.Log;
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

public class ViewAttendanceStudent2 extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentRecyclerAdapter2 studentRecyclerAdapter2;

    ArrayList<String> dates = new ArrayList<>();
    int present=0, dtsz=0;
    TextView totLec, preLec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_student2);

        TextView sub =(TextView) findViewById(R.id.date);
//
//        totLec = (TextView) findViewById(R.id.totLec);
//        preLec = (TextView) findViewById(R.id.preLec);


        String s = "Date not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            s = extras.getString("subject");
        }

        sub.setText(s);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        String finalS = s;
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
                            boolean notpresent = true;
                            Log.d("mil gaya","snapshot2 for" );
                                for (DataSnapshot snapshot3 : snapshot2.getChildren()) {
                                    try {
                                        Log.d("mil gaya","snapsot3 for" );
                                        Student_info student_info = snapshot3.getValue(Student_info.class);
                                        boolean flag = student_info.getSenroll().equals(loginuser2.userid);
                                        if(flag) {
                                            dates.add(snapshot2.getKey() + " : Present");
                                            present++;
                                            notpresent = false;
                                        }
                                    }
                                    catch (Exception e)
                                    {}


                                    if(notpresent)
                                    {
                                        dates.add(snapshot2.getKey() + " : Absent");
                                        notpresent=false;
                                    }
/*
                                    try {
                                        Log.d("mil gaya","snapsot1 if" );
                                        Student_info student_info = snapshot3.getValue(Student_info.class);
                                        boolean flag = student_info.getSenroll().equals(loginuser2.userid);
                                        if(!flag) {
                                            dates.add(snapshot2.getKey() + " : Absent");
                                            present++;
                                            ispresent = false;
                                        }
                                    }
                                    catch (Exception e)
                                    {}

 */


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

                studentRecyclerAdapter2.notifyDataSetChanged();

                Log.d("mil gaya ---", dates.toString());










                /*
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    if(snapshot1.equals(finalS))
                    {
                        for (DataSnapshot snapshot2:snapshot1.getChildren())
                        {
                            for(DataSnapshot snapshot3:snapshot2.getChildren()) {

                                if(snapshot3.getKey().equals(loginuser2.userid)) {
                                    dates.add(snapshot2.getKey() + " : Present");
                                    present++;
                                }
                                else
                                {
                                    dates.add(snapshot2.getKey() + " : Absent");
                                }
                            }
                        }
                    }
                }

                 */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        studentRecyclerAdapter2 = new StudentRecyclerAdapter2(this,dates);
        recyclerView.setAdapter(studentRecyclerAdapter2);


        /*
        totLec = findViewById(R.id.totLec);
        preLec = findViewById(R.id.preLec);

        String tot = String.valueOf(totLec.getText());
        String pre = String.valueOf(preLec.getText());

        dtsz = dates.size();
        totLec.setText(tot.concat(String.valueOf(dtsz)));
        preLec.setText(tot.concat(String.valueOf(present)));

         */

    }
}