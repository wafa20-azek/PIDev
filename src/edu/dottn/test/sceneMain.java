/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author rajhi
 */
public class sceneMain  extends Application{
    public void start (Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/addpost.fxml"));
        Parent root = loader.load();

      //  Image icon = new Image(getClass().getResourceAsStream("/icon.png"));

        Scene scene = new Scene(root, 1280, 700);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Troctn Desktop App ");
        scene.getStylesheets().add("styles.css");
        primaryStage.setResizable(false);
      // primaryStage.getIcons().add(icon);
        primaryStage.show();
        
    }
    
    public static void main (String [] args ){
        launch(args);
    }
    
    
  

}
