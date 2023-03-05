/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author WAFA
 */
public class Main1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/dashboardproducFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
     
            primaryStage.setScene(scene);
            primaryStage.setTitle("troctn");
            
            primaryStage.show();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        };
    }

    public static void main(String[] args) {
launch(args);
    }

}
