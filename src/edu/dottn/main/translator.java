
package edu.dottn.main;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;


public class translator {
    
    public static void main (String args []) throws IOException{
            OkHttpClient client = new OkHttpClient();

           RequestBody body = new FormBody.Builder()
            .add("q", "why we are here ?")
            .add("target", "fr")
            .build();

           Request request = new Request.Builder()
           .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
           .post(body)
           .addHeader("content-type", "application/x-www-form-urlencoded")
           .addHeader("Accept-Encoding", "application/gzip")
           .addHeader("X-RapidAPI-Key", "f98f86c89dmsh48bfe779900149fp11590cjsnc20d08d3dad0")
           .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
           .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            String translatedText = json.getJSONObject("data")
                .getJSONArray("translations")
                .getJSONObject(0)
                .getString("translatedText");
            System.out.println(translatedText); 
            } else {
            System.out.println("Request failed");
            }        
        }
    
    }
