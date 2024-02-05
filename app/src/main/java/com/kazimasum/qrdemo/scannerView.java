package com.kazimasum.qrdemo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scannerView extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
   ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                     permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        //ScanQRCode.scantext.setText(rawResult.getText());
        onBackPressed();
        Long end_tm=null, start_tm = null;
        long currentTimeMillis = 0;

        //String student = MainActivity2.uname.getText().toString();
        String student = loginuser2.userid;
        String scannedData = rawResult.getText();
        String[] dataSplit = scannedData.split("-");
        String subject = dataSplit[0];
        Log.e("Subject", subject);
        Log.e("Start Time", dataSplit[1]);
        Log.e("End TIme", dataSplit[2]);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String currentdate = simpleDateFormat.format(calendar.getTime());
        String currenttime = DateFormat.getTimeInstance().format(calendar.getTime());


        //start time converted into milliseconds

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");

        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");


        String currentdate1 = sdf.format(calendar.getTime());
        String startdate = currentdate1 +" "+ dataSplit[1] +":00";
        Log.e("-------- startdate", startdate);
        try {
            Date date = sdf2.parse(startdate);
            calendar.setTime(date);
            //start_tm = calendar.getTimeInMillis();
            start_tm = date.getTime();
            Log.e("-------- startdate2", date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //end time converted into milliseconds

        String currentdate2 = sdf.format(calendar.getTime());
        String endDate = currentdate2 +" "+ dataSplit[2] +":00" ;
        Log.e("-------- endDate", endDate);
        try {
            Date date = sdf2.parse(endDate);
            calendar.setTime(date);
            //end_tm = calendar.getTimeInMillis();
            end_tm = date.getTime();
            Log.e("-------- enddate2", date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //current time in milliseconds

        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        //String currentdate3 = sdf.format(calendar.getTime());
        //String currentTime = currentdate3 + dataSplit[2] + 00;
        try {
            //Date date = sdf.parse(currentTime);
            Date date = new Date();
            //calendar.setTime(date);
            String currentdateTime = sdf3.format(date);
            Log.e("-------- currentdatetime", currentdateTime);
            Log.e("-------- currentdatetime2", date.toString());
            calendar.setTime(date);
            currentTimeMillis = calendar.getTimeInMillis();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //long currentTimeMillis = System.currentTimeMillis();

        Log.e("start time ", start_tm.toString());
        Log.e("end time ", end_tm.toString());
        Log.e("current time ", String.valueOf(currentTimeMillis));


        if (start_tm <= currentTimeMillis && currentTimeMillis <= end_tm) {

            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("Attendance");

            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Boolean flag = false;

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        Log.d("data", "in 1st for loop");
                        if (snapshot.getKey().equals(subject)) {
                            Log.d("data", "in 1st if");
                            Log.d("data", snapshot.getKey());
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                                Log.d("data", "in 2nd for loop");
                                if (snapshot1.getKey().equals(currentdate)) {
                                    Log.d("data", "in 2nd if");

                                    Student_info student_info = new Student_info(student);
                                    Task<Void> reference = FirebaseDatabase.getInstance().getReference("Attendance").child(subject).child(currentdate).
                                            child(student).setValue(student_info);

                                    Toast.makeText(scannerView.this, "Attendance Marked for Subject " + subject, Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else
        {
            Toast.makeText(com.kazimasum.qrdemo.scannerView.this, "Time limit exceeded", Toast.LENGTH_SHORT).show();
        }
//       else {
//           Toast.makeText(com.kazimasum.qrdemo.scannerView.this,"Invalid QR code",Toast.LENGTH_SHORT).show();
//       }


        //}
    }
        @Override
        protected void onPause () {
            super.onPause();
            scannerView.stopCamera();
        }

        @Override
        protected void onResume () {
            super.onResume();
            scannerView.setResultHandler(this);
            scannerView.startCamera();
        }

}