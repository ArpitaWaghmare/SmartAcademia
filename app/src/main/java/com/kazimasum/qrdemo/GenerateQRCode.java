package com.kazimasum.qrdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateQRCode extends AppCompatActivity {

    Spinner etInput, timeStart, timeEnd;
    //EditText etInput;
    Button btGenerate;
    ImageView ivOutput;


    DatabaseReference reff;
    Attendance attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);

        etInput = findViewById(R.id.et_input);
        timeStart = findViewById(R.id.spinner3);
        timeEnd = findViewById(R.id.spinner4);
        btGenerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);


        Spinner spinnerBranch = findViewById(R.id.et_input);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Subject, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerBranch.setAdapter(adapter1);

        Spinner spinnerStartTime = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.StartTime, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerStartTime.setAdapter(adapter2);

        Spinner spinnerEndTime = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.EndTime, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerEndTime.setAdapter(adapter3);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String currentdate = simpleDateFormat.format(calendar.getTime());
        String currenttime = DateFormat.getTimeInstance().format(calendar.getTime());



        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get input from edit text
                String sText = etInput.getSelectedItem().toString();
                String stTime = timeStart.getSelectedItem().toString();
                String edTime = timeEnd.getSelectedItem().toString();

                if(sText.equals("Select Subject"))
                {
                    Toast.makeText(GenerateQRCode.this,"Please select Subject", Toast.LENGTH_LONG).show();
                }
                else if(!sText.isEmpty())
                {
                    //Initialize multi format writer
                    MultiFormatWriter writer = new MultiFormatWriter();

                    try {
                        //Initialize bit matrix
                        BitMatrix matrix = writer.encode(sText.toUpperCase() + "-" + stTime + "-" + edTime, BarcodeFormat.QR_CODE,
                                350, 350);
                        //Initialize barcode  encoder
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        //Initialize bitmap
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        //set bitmap on imageview
                        ivOutput.setImageBitmap(bitmap);
                        //initialize input manager
                        InputMethodManager manager = (InputMethodManager) getSystemService(
                                Context.INPUT_METHOD_SERVICE
                        );
                        //Hide soft Keyboard
                        manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),
                                0);


                        reff = FirebaseDatabase.getInstance().getReference().child("Attendance");

                        attendance = new Attendance(currentdate, currenttime, sText.toUpperCase());
                        reff.child(sText.toUpperCase()).child(currentdate).setValue(attendance);

                        Toast.makeText(GenerateQRCode.this, "QR code Generated", Toast.LENGTH_SHORT).show();

                        //etInput.setEnabled(false);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }
        }
    });
}
}