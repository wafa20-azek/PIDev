<<<<<<< HEAD

package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;



public class ProfileController implements Initializable {

    private AssociationServices associationServices = new AssociationServices();
    private Association loggedInAssociation;
     
    @FXML
    private Label hellouserText;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAssociation = associationServices.getLoggedInAssociation();
        hellouserText.setText("Hello, "+loggedInAssociation.getAssocName());
    }  
    

=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class ProfileController implements Initializable {

    @FXML
    private Button homebtn;
    @FXML
    private Label logouttext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void goToInventory(ActionEvent e ){
         try {
                    Stage stage = (Stage) homebtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
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
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
    
}
