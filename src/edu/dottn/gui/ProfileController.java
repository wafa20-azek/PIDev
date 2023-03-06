
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;

import edu.dottn.entities.Post;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServicePost;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;

import java.time.LocalDate;

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

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;


import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.stage.Stage;




public class ProfileController implements Initializable {


    private AssociationServices associationServices = new AssociationServices();

    
    private ServicePost ps = new ServicePost();
    //private AssociationServices associationServices = new AssociationServices();

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

    @FXML
    private DatePicker timeDate;

    
    
    public AnchorPane getRoot() {
        return root;
    }
    @FXML
    private ImageView userimage;
    

private List<Post> l= ps.getAll() ;
  

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

    
        
      
        
       loggedInAssociation = associationServices.getLoggedInAssociation();
        hellouserText.setText("Hello, "+loggedInAssociation.getAssocName());

        System.out.println(l);
            
            for (Post item : l) {

             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("post.fxml"));
            Node postNode = loader.load();

            PostController postController = loader.getController();

           // postController.setData(item);

            postController.setData(item.getTitlePost(),item.getAssociation(),item.getDescription(),item.getDate_created(),item.getPhotos());
         //   postController.setData(item.getTitlePost());


            AnchorPane feed1 = new AnchorPane(postNode);
            
            postNode.getStyleClass().add("post");
            AnchorPane.setBottomAnchor(feed1, 10.0);
            
            
            feedBox.getChildren().add(feed1);
            allpostbtn.setOnAction(event -> {

           
                try {
                    refreshPosts();
                } catch (MalformedURLException ex) {
                }
            });
            myfeedbtn.setOnAction(event -> {
                try {
                    refreshPosts();
                } catch (MalformedURLException ex) {
                }
            });
            feed1.setOnMouseClicked(event->{
                try {
                    File file = new File(item.getPhotos());
                    URL url1 = file.toURI().toURL();
                    Image img = new Image(url1.toString());
                    try {
                        Stage stage = (Stage) feed1.getScene().getWindow();
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("postInformations.fxml"));
                        AnchorPane root1 = loader1.load();
                        PostInformationsController controller = loader1.getController();
                        controller.setPostInformations(item.getTitlePost(), item.getDescription(), img,item);
                        controller.setIdPost(item);
                        // Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                        Scene scene = new Scene(root1, 1280, 700);
                        stage.setScene(scene);
                        stage.setTitle("Troctn Desktop App ");
                        scene.getStylesheets().add("styles.css");
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                    }
                } catch (MalformedURLException ex) {
                }
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


        boolean match = title.toLowerCase().startsWith(searchText.toLowerCase());

        feed1.setVisible(match);
        feed1.setManaged(match);   
        }
    }


    private void refreshPosts() throws MalformedURLException {
    feedBox.getChildren().clear(); 
    //LocalDate selectedDate = timeDate.getValue(); 
    for (Post item : l) {
       

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("post.fxml"));
            Node postNode = loader.load();

            PostController postController = loader.getController();
           // postController.setData(item);
            postController.setData(item.getTitlePost(),item.getAssociation(),item.getDescription(),item.getDate_created(),item.getPhotos());

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
            
           feedBox.getChildren().add(feed1);
           feed1.setOnMouseClicked(event->{
                try {
                    File file = new File(item.getPhotos());
                    URL url1 = file.toURI().toURL();
                    Image img = new Image(url1.toString());
                    try {
                        Stage stage = (Stage) feed1.getScene().getWindow();
                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("postInformations.fxml"));
                        AnchorPane root1 = loader1.load();
                        PostInformationsController controller = loader1.getController();
                        controller.setPostInformations(item.getTitlePost(), item.getDescription(), img,item);
                        controller.setIdPost(item);
                        // Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                        Scene scene = new Scene(root1, 1280, 700);
                        stage.setScene(scene);
                        stage.setTitle("Troctn Desktop App ");
                        scene.getStylesheets().add("styles.css");
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException ex) {
                    }
                } catch (MalformedURLException ex) {
                }
            }); 
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
    }
}




}

