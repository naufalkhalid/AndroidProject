package com.example.naim.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String Jsondata=httpget.Gethttprequest();
       ListView listView = (ListView) findViewById(R.id.listViewgetall);
        ArrayList<String> arrayList=null;
        try {
            JSONArray new_array = new JSONArray(httpget.Gethttprequest());
            arrayList = new ArrayList<String>();
            for (int i = 0; i < new_array.length(); i++) {
                JSONObject json_data = null;

                json_data = new_array.getJSONObject(i);

                String name = json_data.getString("name");
                arrayList.add(name);
            }
        }
            catch (JSONException e) {
                e.printStackTrace();
            }



        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, arrayList);
       listView.setAdapter(mArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                try {
                    JSONArray new_array = new JSONArray(httpget.Gethttprequest());
                    JSONObject json_data = null;

                    json_data = new_array.getJSONObject(position);

                    Intent intent = new Intent(MainActivity.this, CalendarDetails.class);
                    String message = json_data.toString();
                    intent.putExtra("EXTRA_MESSAGE", message);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Button btnneventintent = (Button)findViewById(R.id.buttonaddeventintent);
        btnneventintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewCalendarEvent.class);
                startActivity(intent);
            }
        });
        Button btnimport=(Button)findViewById(R.id.buttonimportcalendar);
        btnimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CalendarImport.class);
                startActivity(intent);
            }
        });
        Button btnexport=(Button)findViewById(R.id.buttonexportcalendar);
        btnexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray new_array = new JSONArray(httpget.Gethttprequest());
                    for (int i = 0; i < new_array.length(); i++) {
                        JSONObject json_data = new_array.getJSONObject(i);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


                        try {
                            Date newstartdate;
                            Date newenddate;
                            newstartdate = df.parse(json_data.getString("start_timestamp").toString());
                            GregorianCalendar mycalendar=new GregorianCalendar();
                            mycalendar.setTime(newstartdate);
                            GregorianCalendar myendcalendar=new GregorianCalendar();
                            newenddate = df.parse(json_data.getString("end_timestamp").toString());
                            myendcalendar.setTime(newenddate);
                            Intent intent = new Intent(Intent.ACTION_INSERT)


                                    .setData(CalendarContract.Events.CONTENT_URI)
                                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, mycalendar.getTimeInMillis())
                                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, myendcalendar.getTimeInMillis())
                                    .putExtra(CalendarContract.Events.TITLE, json_data.getString("name"))
                                    .putExtra(CalendarContract.Events.DESCRIPTION, json_data.getString("description"))
                                    .putExtra(CalendarContract.Events.EVENT_LOCATION, json_data.getString("location"))
                                    .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                            startActivity(intent);

                        }
                        catch (ParseException e){

                        }


                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

}
