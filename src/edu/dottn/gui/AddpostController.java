/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Post;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServicePost;
import edu.dottn.test.Example;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rajhi
 */
public class AddpostController implements Initializable {

    ServicePost ps = new ServicePost();
    Example exem = new Example();
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

    AssociationServices as = new AssociationServices();
    Association a = as.getById(2);
    String image = urlcheck;

    ServicePost sp = new ServicePost();

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
            Post p = new Post(a, titlepost.getText(), descpost.getText(), image);
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
           Post p = new Post(a, titlepost.getText(), descpost.getText(), "D:/emna/PIDev-donation/src/default.jpg");
            ps.ajouter(p);
       }else{
           Post p = new Post(a, titlepost.getText(), descpost.getText(), urlcheck);

        ps.ajouter(p);
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

}
