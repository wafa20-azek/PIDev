/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Post;
import edu.dottn.services.ServicePost;
import edu.dottn.util.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class FeeddonationController implements Initializable {

    @FXML
    private TextField seachfield;
    @FXML
    private Label hellouserText;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox feedBox;
    private AnchorPane root;
      private ServicePost ps = new ServicePost();
    private List<Post> l= ps.getAll() ;

    UserSession us=new UserSession();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         seachfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPosts(newValue);
        });

        feedBox.setSpacing(20);
        feedBox.setPadding(new Insets(20, 0, 0, 0));
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
                        Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
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
    public AnchorPane getRoot() {
        return root;
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

}
