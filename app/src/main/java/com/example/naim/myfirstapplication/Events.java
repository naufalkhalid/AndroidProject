package com.example.naim.myfirstapplication;

import java.util.List;

/**
 * Created by Naim on 11/28/2015.
 */
public class Events {

    public String myeventname = "";
    public String description = "";
    public String location = "";
    public String start_timestamp = "";
    public String end_timestamp = "";

    public Events(String myeventname, String description, String location,String start_timestamp,String end_timestamp ){

        this.myeventname = myeventname ;
        this.description = description ;
        this.location=location;
        this.start_timestamp=start_timestamp;
        this.end_timestamp=end_timestamp;

    }

}
