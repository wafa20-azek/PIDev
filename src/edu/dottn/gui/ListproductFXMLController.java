/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.entities.User;
import edu.dottn.services.ProductServices;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class ListproductFXMLController implements Initializable {

    @FXML
    private AnchorPane feed;
    private ProductServices ps = new ProductServices();
    private Button btnaddproduct;
    User user = new User();
    List<Product> l = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setInformation(User u) {
        user = u;
        System.out.println(user);
        l = ps.getByIdUser(user.getIdUser());
        System.out.println(l.size());
        loadproducts(l);
    }

    public void loadproducts(List<Product> l) {
        float x = 20, y = 20;
        BorderStroke borderStroke = new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1, 1, 1, 1));
        Border border = new Border(borderStroke);

        for (Product p : l) {
            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:src/assets/" + p.getImage(), 200, 200, false, false);
            ImageView iv = new ImageView(image);

            Label title = new Label(p.getName());
            Label Description = new Label(p.getDescription());
            String s = String.valueOf(p.getPrice());
            Label value = new Label(s);
           
            anchorpane.setOnMouseClicked(MouseEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("productFXML.fxml"));
                    Parent product = loader.load();
                    ProductFXMLController prod = loader.getController();
                    prod.setproduct(p);
                    prod.setvisibility(Boolean.FALSE);
                    Scene secondScene = new Scene(product);
                    Stage secondStage = new Stage();
                    secondStage.setScene(secondScene);
                    secondStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DashboardproducFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            Description.setLayoutY(y + 260);
            Button btndelete = new Button("delete");
            Button btnupdate = new Button("update");

            btndelete.setOnMouseClicked(MouseEvent -> {
                ondeleteclick(p);
            });
            btnupdate.setOnMouseClicked(MouseEvent -> {

                onupdateclick(p);

            });
             
            iv.setLayoutY(y);
            title.setLayoutY(y + 210);
            value.setLayoutY(y + 240);
            btnupdate.setLayoutY(y + 290);
            btnupdate.setLayoutX(150);
            btndelete.setLayoutY(y + 290);
            AnchorPane.setLeftAnchor(iv, 10.0);
            AnchorPane.setLeftAnchor(title, 10.0);
            AnchorPane.setLeftAnchor(Description, 10.0);
            AnchorPane.setLeftAnchor(value, 10.0);
            AnchorPane.setLeftAnchor(btndelete, 10.0);
            anchorpane.setLayoutX(x);
            anchorpane.setPadding(new Insets(20, 20, 20, 20));
            anchorpane.setBorder(border);
            anchorpane.setPrefSize(260, 300);
            anchorpane.getChildren().addAll(iv, title, Description, value, btndelete, btnupdate);
            feed.setPrefHeight(500);
            feed.getChildren().addAll(anchorpane);
            x += 300;
        }
    }

    private void ondeleteclick(Product p1) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product", ButtonType.YES, ButtonType.NO);
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
            };
        });

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
