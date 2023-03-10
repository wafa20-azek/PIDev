
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Post;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServicePost;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javafx.stage.Stage;


public class AddpostController implements Initializable {

    ServicePost ps = new ServicePost();
    
    @FXML
    private Label urlImage;

    String imageurl;
    String urlcheck;
    ActionEvent e;
    @FXML
    private Button addpostbtn;
    @FXML
    private TextField titlepost;
    @FXML
    private TextArea descpost;
    @FXML
    private Button uploadbtn;
    @FXML
    private ImageView sharebtn;
    @FXML
    private Label sharetxt;
    
    public static Post p;
    
  

    AssociationServices as = new AssociationServices();
    Association loggedInAssociation = as.getLoggedInAssociation();
   // Association a = as.getById(2);
    String image = urlcheck;

    ServicePost sp = new ServicePost();
    @FXML
    private ImageView backInventory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sharebtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sharebtn.setEffect(new DropShadow());
                sharetxt.setStyle("-fx-text-fill: #BF5CFF;");
                sharetxt.opacityProperty().set(1.0);
            }
        });
        sharebtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sharebtn.setEffect(null);
            }
        });
        sharebtn.setOnMouseClicked(e -> {
            Post p = new Post(loggedInAssociation, titlepost.getText(), descpost.getText(), image);
            try {
                sp.ajouter(p);
                sp.shareOnPage(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Post Published");
                alert.setHeaderText("Your post have been published !");
                alert.showAndWait();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    @FXML
    private void addpost(ActionEvent event) {

       if(urlcheck==null){
          p = new Post(loggedInAssociation, titlepost.getText(), descpost.getText(), "D:/emna/PIDev-donation/src/default.jpg");
           System.out.println(p);
            ps.ajouter(p);
       }else{
           p = new Post(loggedInAssociation, titlepost.getText(), descpost.getText(), urlcheck);
           System.out.println(p);
           sharebtn.setOnMouseClicked(e->{
               try {
                   ps.shareOnPage(p);
               } catch (IOException ex) {
                   System.out.println(ex.getMessage());
               }
           });
        ps.ajouter(p);
       // ps.sendMessage(loggedInAssociation.getAssocName(), p.getTitlePost());
        
        titlepost.clear();
        descpost.clear();
        urlImage.setText("choose your picture ...");
           backToInventory();
       }
        
        
    }

    @FXML
    private String uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        System.out.println(selectedFile.getName());
        if (selectedFile != null) {
            imageurl = selectedFile.toURI().toString();

            urlImage.setText(selectedFile.getName());
            urlcheck = imageurl.substring("file:/".length());
        }
        return urlcheck;
    }

    @FXML
    private void backToInventory() {
        try {
                    Stage stage = (Stage) backInventory.getScene().getWindow();
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
