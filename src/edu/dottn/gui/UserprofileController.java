/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class UserprofileController implements Initializable {

    AssociationServices associationServices = new AssociationServices();
    Association loggedInAssociation;
    
    @FXML
    private Label nametext;
    @FXML
    private ImageView imageuser;
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField emailfield;
    
    boolean isFirstClick = true;
    @FXML
    private Button updatebtn;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageuser.setFitWidth(120);
        imageuser.setFitHeight(120);
        loggedInAssociation = associationServices.getLoggedInAssociation();
        nametext.setText(loggedInAssociation.getAssocName());
        
        imageuser.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageuser.setEffect(new DropShadow());
            }
        });

        imageuser.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageuser.setEffect(null);
            }
        });
        imageuser.setOnMouseClicked(event -> {
            // Handle the click event
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                imageuser.setImage(newImage);
                imageuser.setImage(newImage);
                imageuser.setPreserveRatio(true);
                imageuser.setSmooth(true);
                imageuser.setFitWidth(120); // set the desired width of the ImageView
                imageuser.setFitHeight(120 * newImage.getHeight() / newImage.getWidth());
                Rectangle clip = new Rectangle(imageuser.getFitWidth(), imageuser.getFitHeight());
                clip.setArcWidth(50);
                clip.setArcHeight(50);
                imageuser.setClip(clip);
            }
        });
        
        usernamefield.setPromptText(loggedInAssociation.getUsername());
        
        usernamefield.setOnMouseClicked(event ->{
            if (isFirstClick) {
                usernamefield.setText(usernamefield.getPromptText());
                isFirstClick = false;
            }
        });
        
        emailfield.setPromptText(loggedInAssociation.getEmail());
        
        emailfield.setOnMouseClicked(event ->{
            if (isFirstClick) {
                emailfield.setText(emailfield.getPromptText());
                isFirstClick = false;
            }
        });

    }    

    @FXML
    private void updateassociation(ActionEvent event) {
        Association a = new Association();
        a.setId(loggedInAssociation.getId());
        a.setUsername(usernamefield.getText());
        a.setEmail(emailfield.getText());
        associationServices.update(a);
        
    }
    
}
