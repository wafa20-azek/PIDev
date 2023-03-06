package edu.dottn.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class imageAnalyseAPI {
       
        
        public static boolean imageAnalyse(String path) throws IOException, JSONException{
            OkHttpClient client = new OkHttpClient();
            System.out.println(path);
            File file = new File(path);
            byte[] fileContent = Files.readAllBytes(file.toPath());

        RequestBody body = new MultipartBody.Builder()
	.setType(MultipartBody.FORM)
	.addFormDataPart("image",path ,
	RequestBody.create(MediaType.parse("text/plain"),fileContent ))
	.build();


        Request request = new Request.Builder()
	.url("https://nsfw3.p.rapidapi.com/v1/results")
	.post(body)
	.addHeader("X-RapidAPI-Key","f98f86c89dmsh48bfe779900149fp11590cjsnc20d08d3dad0")
	.addHeader("X-RapidAPI-Host", "nsfw3.p.rapidapi.com")
	.build();

        Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");
            JSONObject firstResult = results.getJSONObject(0);
            JSONArray entities = firstResult.getJSONArray("entities");
            JSONObject firstEntity = entities.getJSONObject(0);
            JSONObject classes = firstEntity.getJSONObject("classes");
            double sfw = classes.getDouble("sfw");
            System.out.println(sfw);
            if (sfw>0.5){
                return true;
            }
         
        return false;
        }
       
}
