package com.example.naim.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("EXTRA_MESSAGE");
                try {

                    JSONObject json_data = new JSONObject(newString);
                    TextView txtviewid=(TextView)findViewById(R.id.textViewid);
                    txtviewid.setText(json_data.getString("_id"));
                    EditText txtviewname=(EditText)findViewById(R.id.textedname);

                    txtviewname.setText(json_data.getString("name"));

                    EditText txtviewdescription=(EditText)findViewById(R.id.textdescription);
                    txtviewdescription.setText(json_data.getString("description"));

                    EditText txtstart_time=(EditText)findViewById(R.id.start_timestamp);
                    txtstart_time.setText(json_data.getString("start_timestamp"));
                    EditText txtend_time=(EditText)findViewById(R.id.end_timestamp);
                    txtend_time.setText(json_data.getString("end_timestamp"));
                    EditText txtviewlocation=(EditText)findViewById(R.id.location);
                    Log.d("myval",json_data.getString("location"));
                    txtviewlocation.setText(json_data.getString("location"));




                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("EXTRA_MESSAGE");
            Toast.makeText(getApplicationContext(), newString, Toast.LENGTH_SHORT).show();
        }
        Button btnupdate=(Button)findViewById(R.id.buttonupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtviewid=(TextView)findViewById(R.id.textViewid);
                String id=txtviewid.getText().toString();
                EditText txtviewname=(EditText)findViewById(R.id.textedname);
                String name=txtviewname.getText().toString();
                EditText txtviewdescription=(EditText)findViewById(R.id.textdescription);
                String description=txtviewname.getText().toString();
                Log.d("EdDescription",description);
                EditText txtstart_time=(EditText)findViewById(R.id.start_timestamp);
                String starttime=txtstart_time.getText().toString();
                EditText txtend_time=(EditText)findViewById(R.id.end_timestamp);
                String endtime=txtend_time.getText().toString();
                EditText txtviewlocation=(EditText)findViewById(R.id.location);
                String location=txtviewlocation.getText().toString();

                Toast.makeText(getApplicationContext(), httppost.putdata(id, name, description, starttime, endtime, location), Toast.LENGTH_SHORT).show();

            }
        });
        Button deletebutton=(Button)findViewById(R.id.buttondelete);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtviewid=(TextView)findViewById(R.id.textViewid);
                String id=txtviewid.getText().toString();
                Toast.makeText(getApplicationContext(),httppost.deletedata(id),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
