
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.File;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ProfileController implements Initializable {

    private AssociationServices associationServices = new AssociationServices();
    private Association loggedInAssociation;
     
    @FXML
    private Label hellouserText;
    @FXML
    private ImageView userimage;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAssociation = associationServices.getLoggedInAssociation();
        hellouserText.setText("Hello, "+loggedInAssociation.getAssocName());
        try{
        String imagePath = loggedInAssociation.getImage();
        File file = new File(imagePath);
         url = file.toURI().toURL();
        Image image = new Image(url.toString());
        userimage.setImage(image);
        } catch (MalformedURLException ex) {
        }
    }  
    

    
}
