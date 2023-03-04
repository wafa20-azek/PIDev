/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceAvis;
import edu.dottn.services.ServiceOffre;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bochr
 */


public class DetailofferFXMLController implements Initializable {

    private VBox vbox;
    @FXML
    private Label user1;
    @FXML
    private Label user2;
    @FXML
    private Label title1;
    @FXML
    private Label title2;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;

    //Offre o=new Offre();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void setoffre(Offre o){
        user1.setText(o.getUser1().getName());
        user2.setText(o.getUser2().getName());
        title1.setText(o.getProduct1().getName());
        title2.setText(o.getProduct2().getName());
         Image image = new Image("file:src/assets/" + o.getProduct1().getImage());
         image1.setImage(image);
          Image img = new Image("file:src/assets/" + o.getProduct2().getImage());
         image2.setImage(img);
    
    }
}