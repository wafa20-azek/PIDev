/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.services.ProductServices;
import java.awt.event.MouseEvent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class ListproductFXMLController implements Initializable {

    @FXML
    private AnchorPane feed;
    private ProductServices ps = new ProductServices();
    @FXML
    private Button btnaddproduct;
    @FXML
    private Button btnhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         float x=20,y=20;
        List<Product> l = new ArrayList<>();
        l = ps.getByIdUser(1);
        for (Product p:l) {
            AnchorPane anchorpane=new AnchorPane();
            Image image = new Image("file:src/assets/" +p.getImage(),200,200,false,false);
            ImageView iv= new ImageView(image);
            
            Label title = new Label(p.getName());
            Label Description = new Label(p.getDescription());
            String s = String.valueOf(p.getPrice());
            Label value = new Label(s);
            anchorpane.setLayoutX(x); 
            iv.setLayoutY(y);
            title.setLayoutY(y+210);    
            
            value.setLayoutY(y+240);
           
            Description.setLayoutY(y+260);
             Button btndelete = new Button("delete");
              Button btnupdate = new Button("update");
              
               btndelete.setOnMouseClicked(MouseEvent->{
                ondeleteclick(p);
            });
            btnupdate.setOnMouseClicked(MouseEvent->{
                
                onupdateclick(p);
                
            });
             btnupdate.setLayoutY(y+290);
             btndelete.setLayoutY(y+320);              
            anchorpane.getChildren().addAll(iv,title,Description,value,btndelete,btnupdate);
            feed.getChildren().addAll(anchorpane);
            x+=300;       
        }
    }    

     private void ondeleteclick(Product p1) {
          Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product", ButtonType.YES,ButtonType.NO);
        a.showAndWait().ifPresent(type -> {
        if (type == ButtonType.YES) {
            ps.removeProduct(p1.getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/listproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnaddproduct.getScene().setRoot(root);
        } else if (type == ButtonType.NO) {
            a.close();
        } ;
});
 
         
     }
    @FXML
    private void goaddproduct(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/addproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnaddproduct.getScene().setRoot(root);
    }

    @FXML
    private void home(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/feedproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnhome.getScene().setRoot(root);
    }

    private void onupdateclick(Product pu) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/updateproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UpdateproductFXMLController upc = loader.getController();
        upc.setproduct(pu);
        
        btnaddproduct.getScene().setRoot(root);
    }
    
}
