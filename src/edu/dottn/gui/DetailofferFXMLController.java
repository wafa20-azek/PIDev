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

    @FXML
    private VBox vbox;
    @FXML
    private HBox hbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox.getChildren().clear();
        int x = 0, y = 0;
        List<Offre> lo = new ArrayList<>();
        Offre o1 = new Offre();
        ServiceOffre so = new ServiceOffre();

        o1.getID_Product1();
        for (Offre o : lo) {
            HBox hbox = new HBox();
            hbox.setLayoutX(x);
            hbox.setLayoutY(y);
            Label name = new Label(o.getName());
            InputStream imgStream = getClass().getResourceAsStream("/img/tablechaise.png");
            Image img = new Image(imgStream, 25,25,false,false);
            ImageView imv = new ImageView(img);
//            imv.setOnMouseClicked(MouseEvent ->  so.getByProduct(o));
            imv.setLayoutX(x + 80);
            imv.setLayoutY(y +17);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/souris.png");
            Image img1 = new Image(imgStream1,25,25,false,false);
            ImageView imgv = new ImageView(img1);
             imv.setLayoutX(x +80);
            imv.setLayoutY(y +150);
            
            hbox.getChildren().addAll(name,imgv, imv);
            vbox.getChildren().add(hbox);
        }
    }
    }
