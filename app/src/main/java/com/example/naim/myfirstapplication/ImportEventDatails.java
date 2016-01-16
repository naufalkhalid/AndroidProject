package com.example.naim.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ImportEventDatails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_event_datails);
    }
    TextView importname;
    TextView importdesc;
    TextView importstartdate;
    TextView importenddate;
    TextView importlocation;
    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();

                String Name = extras.getString("eventname");
        String Description = extras.getString("eventdescription");
        String location = extras.getString("eventlocation");
        String eventstart_timestamp = extras.getString("eventstart_timestamp");
        String eventend_timestamp = extras.getString("eventend_timestamp");
        Long time=Long.parseLong(extras.getString("eventend_timestamp"));
        GregorianCalendar myendcalendar=new GregorianCalendar();

       myendcalendar.setTimeInMillis(time);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
      String newendtime= formatter.format(myendcalendar.getTime());
        Long timestart=Long.parseLong(extras.getString("eventend_timestamp"));
        GregorianCalendar myendcalendar2=new GregorianCalendar();

        myendcalendar2.setTimeInMillis(timestart);
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String newstarttime= formatter2.format(myendcalendar.getTime());
        Log.d("mynewenddate",newendtime);
     importname=(TextView)findViewById(R.id.textView1);
    importdesc=(TextView)findViewById(R.id.textView2);
    importstartdate=(TextView)findViewById(R.id.textView3);
   importenddate=(TextView)findViewById(R.id.textView4);
     importlocation=(TextView)findViewById(R.id.textView5);
      importname.setText(Name);
    importdesc.setText(Description);
      importstartdate.setText(newstarttime);
       importenddate.setText(newendtime);
       importlocation.setText(location);

        Button importevent=(Button)findViewById(R.id.buttonimport);
        importevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), httppost.postdata(importname.getText().toString(), importdesc.getText().toString(), importstartdate.getText().toString(), importenddate.getText().toString(), importlocation.getText().toString()), Toast.LENGTH_LONG).show();
            }
        });






    }
}
