
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Donation;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServiceDonation;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class InventoryController implements Initializable {

    @FXML
    private Button addPostbtn;
    @FXML
    private ImageView inventoryImageUser;
    
    
    
    private AssociationServices associationServices = new AssociationServices();
    private Association loggedInAssociation;
    private ServiceDonation sd = new ServiceDonation();
    @FXML
    private HBox donationFeed;
    
  private List<Donation> l= sd.getAll();
     private AnchorPane root;
    @FXML
    private Label welcomeInventoryText;
     
      public AnchorPane getRoot() {
        return root;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        donationFeed.setSpacing(15);
       // System.out.println(l);
       loggedInAssociation=associationServices.getLoggedInAssociation();
       try{
        String imagePath = loggedInAssociation.getImage();
        File file = new File(imagePath);
         url = file.toURI().toURL();
        Image image = new Image(url.toString());
        welcomeInventoryText.setText(loggedInAssociation.getAssocName()+" Inventory");
        
        inventoryImageUser.setImage(image);
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
       
      inventoryImageUser.setOnMouseEntered(e->{
           inventoryImageUser.scaleXProperty().setValue(1.3);
           inventoryImageUser.scaleYProperty().setValue(1.3);  
       });
       inventoryImageUser.setOnMouseExited(e->{
           inventoryImageUser.scaleXProperty().setValue(1);
           inventoryImageUser.scaleYProperty().setValue(1);
       });
       inventoryImageUser.setOnMouseClicked(e->{
               try {
                    Stage stage = (Stage) addPostbtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("userprofile.fxml"));
                    //Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
       });
       
       for (Donation item : l) {
            try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("donationCard.fxml"));
            Node postNode = loader.load();

            DonationCardController DonationCardController = loader.getController();
            DonationCardController.setData(item);
            

            AnchorPane donation = new AnchorPane(postNode);
            
           // postNode.getStyleClass().add("post");
            AnchorPane.setBottomAnchor(donation, 10.0);
            
            
            donationFeed.getChildren().add(donation);
           
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        }
    }    

    @FXML
    private void goToAddPost(ActionEvent event) {
         try {
                    Stage stage = (Stage) addPostbtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("addpost.fxml"));

                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};

                    //Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
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
