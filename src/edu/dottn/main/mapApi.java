
package edu.dottn.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;



public class mapApi extends Application{
    public static String lat="36.8991173";
    public static String lng="10.1888711";
    public static String address="";
   public static void main(String[] args) throws IOException {
       
      
       
       OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
           .url("https://address-from-to-latitude-longitude.p.rapidapi.com/geolocationapi?lat="+lat+"&lng="+lng)
            .get()
            .addHeader("X-RapidAPI-Key", "f98f86c89dmsh48bfe779900149fp11590cjsnc20d08d3dad0")
            .addHeader("X-RapidAPI-Host", "address-from-to-latitude-longitude.p.rapidapi.com")
            .build();

        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
       JSONObject jsonObject = new JSONObject(jsonString);
       System.out.println(jsonObject);
        JSONArray results = jsonObject.getJSONArray("Results");
         address = results.getJSONObject(0).getString("address");
        String city = results.getJSONObject(0).getString("country");

        System.out.println(city+"  "+address);
       
        launch(args);
    
        
       
}
   Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
   public  void startmap(String lat , String lng , String address){
       WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.google.tn/maps/place/"+address+"/@"+lat+","+lng+",18z/");

          
        Stage stage = new Stage();
        Scene scene = new Scene(webView, 1280, 700);

        stage.setScene(scene);
        stage.setTitle("Troctn Desktop App ");
        scene.getStylesheets().add("styles.css");
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.show();
   }

    @Override
    public void start(Stage stage) throws Exception {
             startmap(lat,lng,address);
    }
    
    
}
