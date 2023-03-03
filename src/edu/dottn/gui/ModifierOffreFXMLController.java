/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceOffre;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bochr
 */
public class ModifierOffreFXMLController implements Initializable {

    @FXML
    private AnchorPane feed1;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierOffer(ActionEvent event) {
        
         List<Offre> listoffre = new ArrayList<>();
        Offre o1 = new Offre();
        Offre o = new Offre();
        ServiceOffre so = new ServiceOffre();
        int x = 0, y = 0;
        for (Offre o2 : listoffre) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);
            o2.getID_Product1();
            InputStream imgStream = getClass().getResourceAsStream("/img/tableetchaise.png");
            Image img = new Image(imgStream, 45, 45, false, false);
            ImageView imv4 = new ImageView(img);
            imv4.setOnMouseClicked(MouseEvent ->  so.getByProduct(o2));
            imv4.setLayoutX(x +14);
            imv4.setLayoutY(y +22);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/téléchargement(1).png");
            Image img1 = new Image(imgStream1, 45, 45, false, false);
            ImageView imgv = new ImageView(img1);
            imgv.setOnMouseClicked(MouseEvent ->  so.getByProduct(o));
            imgv.setLayoutX(x + 6);
            imgv.setLayoutY(y +119);
            Button btnsentrequest = new Button("Modifier");
            an.getChildren().addAll(imv4, imgv);
            feed1.getChildren().addAll(an);
    }
    }
}
