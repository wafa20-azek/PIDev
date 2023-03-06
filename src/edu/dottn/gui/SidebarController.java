/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;


import edu.dottn.services.AssociationServices;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
<<<<<<< HEAD
 * @author Saif
=======
 * @author rajhi
>>>>>>> 3e170621cf1e7404842b9b005b236f27c073096e
 */
public class SidebarController implements Initializable {

    @FXML
    private Button homebtn;
    @FXML
    private Label logouttext;
    @FXML
    private Button messagesbtn;

    @FXML
    private Button Profile;
    @FXML
    private Button Inventory;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


     @FXML
    private void logout(MouseEvent event) {
        AssociationServices as = new AssociationServices();
        if (as.logoutAssociation()){
            try {
                    Stage stage = (Stage) logouttext.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Have A Good Day");
            alert.setHeaderText("See you soon my friend");
            alert.showAndWait();
           
        }
    }
    
  

    @FXML
    private void goToInventory(ActionEvent event) {
        try {
                    Stage stage = (Stage) homebtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
//                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};

                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
    }

    @FXML

    private void goToMessages(ActionEvent event) {
        try {
                    Stage stage = (Stage) homebtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("messages.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
    }

    @FXML
    private void goToProfile(MouseEvent event) {
        try {
                    Stage stage = (Stage) homebtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("userprofile.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
    }

    @FXML
    private void goToFeed(ActionEvent event) {
         try {
                    Stage stage = (Stage) homebtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }

}
}
