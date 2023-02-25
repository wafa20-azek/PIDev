
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
    

    
}
