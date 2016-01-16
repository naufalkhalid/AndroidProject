package com.example.naim.myfirstapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONException;
import org.apache.http.HttpResponse;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.ref.SoftReference;

/**
 * Created by Naim on 11/24/2015.
 */
public class httppost {
    public static String postdata(String name,String description ,String startdate,String enddate,String location) {
        String result = "";
        try

        {
            JSONObject jobj = new JSONObject();

            try

            {



                jobj.put("location", location);
                jobj.put("name", name);

                jobj.put("start_timestamp", startdate);
                jobj.put("end_timestamp", enddate);
                jobj.put("description", description);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpClient http = new DefaultHttpClient();

            String url = utility.HostAdress;
            HttpPost http_post = new HttpPost(url+"events");


            String json = "";
            Context context=null;
            Log.d("myTag", jobj.toString() );

            json = jobj.toString();


            // 5. Set json to StringEntity
            StringEntity it = new StringEntity(json);

            // 6. Set http post Entity
            http_post.setEntity(it);

            // 7. Set some headers to the server Inform about the type of the content
            http_post.setHeader("Accept", "application/json");
            http_post.setHeader("Content-type", "application/json");

            // 8. Execute a POST request to the given URL
            HttpResponse http_response = http.execute(http_post);

            // 9 receive response as
            InputStream input_Stream = http_response.getEntity().getContent();

            // 10. convert InputStream to a string
            if (input_Stream != null)
                result = convertInputStreamToString(input_Stream);
            else
                result = "Did notwork !";




            // 11 return result
         return  result;

        } catch (Exception e) {
return  null;
        }
    }
    public static String deletedata(String id) {
        String result = "";
        try

        {

            HttpClient http = new DefaultHttpClient();

            String url = utility.HostAdress;
            HttpDelete http_post = new HttpDelete(url+"events/"+id);




            // 7. Set some headers to the server Inform about the type of the content
            http_post.setHeader("Accept", "application/json");
            http_post.setHeader("Content-type", "application/json");

            // 8. Execute a POST request to the given URL
            HttpResponse http_response = http.execute(http_post);

            // 9 receive response as
            InputStream input_Stream = http_response.getEntity().getContent();

            // 10. convert InputStream to a string
            if (input_Stream != null)
                result = convertInputStreamToString(input_Stream);
            else
                result = "Did notwork !";




            // 11 return result
            return  result;

        } catch (Exception e) {
            return  null;
        }
    }
    public static String putdata(String id,String name,String description ,String startdate,String enddate,String location) {
        String result = "";
        try

        {
            JSONObject jobj = new JSONObject();

            try

            {

                jobj.put("id", id);
                jobj.put("name", name);
                jobj.put("description", description);
                jobj.put("start_timestamp", startdate);
                jobj.put("end_timestamp", enddate);
                jobj.put("location",location);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpClient http = new DefaultHttpClient();

            String url = utility.HostAdress;
            HttpPut http_post = new HttpPut(url+"events/"+id);


            String json = "";

            Log.d("myapp",json);
            json = jobj.toString();

            Log.d("myapp",jobj.toString());
            // 5. Set json to StringEntity
            StringEntity it = new StringEntity(json);

            // 6. Set http post Entity
            http_post.setEntity(it);

            // 7. Set some headers to the server Inform about the type of the content
            http_post.setHeader("Accept", "application/json");
            http_post.setHeader("Content-type", "application/json");

            // 8. Execute a POST request to the given URL
            HttpResponse http_response = http.execute(http_post);

            // 9 receive response as
            InputStream input_Stream = http_response.getEntity().getContent();

            // 10. convert InputStream to a string
            if (input_Stream != null)
                result = convertInputStreamToString(input_Stream);
            else
                result = "Did notwork !";




            // 11 return result
            return  result;

        } catch (Exception e) {
            return  null;
        }
    }
    private static String convertInputStreamToString (InputStream inputStream) throws IOException
    {
        BufferedReader BufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        String line = "";
        String result = "";
        while ( (line= BufferedReader.readLine ()) != null)
            result += line;

        inputStream.close ();
        return result;

    }

    }

