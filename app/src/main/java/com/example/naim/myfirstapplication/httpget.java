package com.example.naim.myfirstapplication;

import android.app.Activity;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Naim on 11/24/2015.
 */
public class httpget {

    public static String Gethttprequest() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
            HttpClient httpclient = new DefaultHttpClient();
            String url=utility.HostAdress;
            HttpGet request = new HttpGet(url+"events");
            // replace with your url

            HttpResponse response;
            try {
                response = httpclient.execute(request);
 String s= EntityUtils.toString(response.getEntity());

                return s;
            }

            catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;

            }


    }
}
