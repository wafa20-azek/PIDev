
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;



public class ProfileController implements Initializable {

    private AssociationServices associationServices = new AssociationServices();
    private Association loggedInAssociation;
     
    @FXML
    private Label hellouserText;
    
    
    private AnchorPane root;
    @FXML
    private TextField seachfield;
    @FXML
    private Button allpostbtn;
    @FXML
    private Button myfeedbtn;
    
    
    public AnchorPane getRoot() {
        return root;
    }
    @FXML
    private ImageView userimage;
    
    private ArrayList<String> l= new ArrayList<String>();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox feedBox;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seachfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPosts(newValue);
        });

        feedBox.setSpacing(20);
        feedBox.setPadding(new Insets(20, 0, 0, 0));
    
        
        l.add("a");
        l.add("b");
        l.add("TEST");
        l.add("Saif");
        l.add("Collect dons '9ofet Romdhan'");
        
        loggedInAssociation = associationServices.getLoggedInAssociation();
        hellouserText.setText("Hello, "+loggedInAssociation.getAssocName());
        try{
        String imagePath = loggedInAssociation.getImage();
        File file = new File(imagePath);
         url = file.toURI().toURL();
        Image image = new Image(url.toString());
        userimage.setImage(image);
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(l);
            
            for (String item : l) {
             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("post.fxml"));
            Node postNode = loader.load();

            PostController postController = loader.getController();
            postController.setData(item);

            AnchorPane feed1 = new AnchorPane(postNode);
            
            postNode.getStyleClass().add("post");
            AnchorPane.setBottomAnchor(feed1, 10.0);
            
            
            feedBox.getChildren().add(feed1);
            allpostbtn.setOnAction(event -> {
                refreshPosts();
            });
            myfeedbtn.setOnAction(event -> {
                refreshPosts();
            });
           
            } catch (IOException e) {
               System.out.println(e.getMessage());
            }
         }
    }
    
    private void filterPosts(String searchText) {
    for (Node child : feedBox.getChildren()) {
        AnchorPane feed1 = (AnchorPane) child;
        Label titleLabel = (Label) feed1.lookup("#titlepost");
        
         
        String title = titleLabel.getText();
        boolean match = title.toLowerCase().contains(searchText.toLowerCase());
        feed1.setVisible(match);
        feed1.setManaged(match);
      
        
    }
    }
    
    private void refreshPosts() {
    feedBox.getChildren().clear(); // clear current posts
    for (String item : l) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("post.fxml"));
            Node postNode = loader.load();

            PostController postController = loader.getController();
            postController.setData(item);

            AnchorPane feed1 = new AnchorPane(postNode);
            postNode.getStyleClass().add("post");
            AnchorPane.setBottomAnchor(feed1, 10.0);
            
            // check which button is selected and only show relevant posts
            if (allpostbtn.isHover()) {
                feed1.setVisible(true);
                feed1.setManaged(true);
            } else if (myfeedbtn.isHover()) {
                Label titleLabel = (Label) feed1.lookup("#titlepost");
                Label associationLabel = (Label) feed1.lookup("#assocpost");
                String title = titleLabel.getText();
                String association = associationLabel.getText();
                System.out.println(association);
                boolean match = association.toLowerCase().equals(loggedInAssociation.getAssocName().toLowerCase());
                feed1.setVisible(match);
                feed1.setManaged(match);
            }

            feedBox.getChildren().add(feed1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

}

