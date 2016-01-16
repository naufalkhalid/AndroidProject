package com.example.naim.myfirstapplication;

import android.Manifest;
import android.app.usage.UsageEvents;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.security.PublicKey;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarImport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_import);
        getEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    ArrayList<String> events = new ArrayList<String>();
    ArrayList<Events> importeventdetails = new ArrayList<Events>();
    private void getEvents() {
        ListView listevents = (ListView) findViewById(R.id.listViewcalendarimport);
        String[] event_projection = new String[]{
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DESCRIPTION,
                CalendarContract.Events.EVENT_LOCATION,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
        };

        Uri uri = CalendarContract.Events.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        String selection = "(("
                + CalendarContract.Events.DTSTART + ">=" + Calendar.getInstance().getTimeInMillis() + "))";
        //String selection = "Calendars._id=" + calendar_id;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor cur = cr.query(uri, event_projection, selection, null, "DTSTART ASC");


        while (cur.moveToNext()) {

            Events myevent = new Events(cur.getString(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4));
            myevent.myeventname = cur.getString(0);
            myevent.description = cur.getString(1);

            myevent.location = cur.getString(2);
            myevent.start_timestamp = cur.getString(3);
            myevent.end_timestamp = cur.getString(4);


            events.add(myevent.myeventname);
             // Creating a new object

            importeventdetails.add(myevent); // Adding it to the list



        }
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, events);
        listevents.setAdapter(mArrayAdapter);
        listevents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(CalendarImport.this,ImportEventDatails.class);

                intent.putExtra("eventname", importeventdetails.get(position).myeventname);
                intent.putExtra("eventdescription",importeventdetails.get(position).description);
                intent.putExtra("eventlocation",importeventdetails.get(position).location);
                intent.putExtra("eventstart_timestamp",importeventdetails.get(position).start_timestamp);
                intent.putExtra("eventend_timestamp",importeventdetails.get(position).end_timestamp);

                startActivity(intent);
            }
        });
    }
}
