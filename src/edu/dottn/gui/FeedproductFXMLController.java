/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.services.ProductServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class FeedproductFXMLController implements Initializable {

    @FXML
    private Button addproduct;
    @FXML
    private AnchorPane feed;

    private ProductServices ps = new ProductServices();
    @FXML
    private Button btnlistproduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         float x=20,y=20;
        List<Product> l = new ArrayList<>();
        l = ps.getAll();
        for (int i = 0; i < l.size(); i++) {
            AnchorPane anchorpane=new AnchorPane();
            Image image = new Image("file:src/assets/" +l.get(i).getImage(),200,200,false,false);
            ImageView iv= new ImageView(image);
            
            Label title = new Label(l.get(i).getName());
            Label Description = new Label(l.get(i).getDescription());
            String s = String.valueOf(l.get(i).getPrice());
            Label value = new Label(s);
            anchorpane.setLayoutX(x); 
            iv.setLayoutY(y);
            title.setLayoutY(y+210);    
            
            
            value.setLayoutY(y+240);
            Description.setLayoutY(y+260);
            anchorpane.getChildren().addAll(iv,title,Description,value);
            feed.getChildren().addAll(anchorpane);
            x+=300;
            
            if(i%3==0 && i!=0)
            {
               x=0;
               y+=300;
            }
            
            
             
           
       
        }
    }

    @FXML
    private void gotoaddproduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/addproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addproduct.getScene().setRoot(root);

    }

    @FXML
    private void listproducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/listproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addproduct.getScene().setRoot(root);
    }

}
