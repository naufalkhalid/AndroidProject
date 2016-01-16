package com.example.naim.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCalendarEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar_event);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button createeventbutton =(Button)findViewById(R.id.CreateEvent);
        createeventbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                EditText ename = (EditText) findViewById(R.id.editName);
                String name = ename.getText().toString();
                EditText edescription = (EditText) findViewById(R.id.editDescription);
                String description = edescription.getText().toString();
                EditText estdate = (EditText) findViewById(R.id.editstart_timestamp);
                String startdate = estdate.getText().toString();
                EditText eenddate = (EditText) findViewById(R.id.editend_timestamp);
                String enddate = eenddate.getText().toString();
                EditText elocation = (EditText) findViewById(R.id.editlocation);
                String location = elocation.getText().toString();
                Toast.makeText(getApplicationContext(), httppost.postdata(name, description, startdate, enddate, location), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
