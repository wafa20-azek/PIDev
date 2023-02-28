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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.transaction.TransactionRequiredException;

/**
 * FXML Controller class
 *
 * @author bochr
 */
public class CreationOfferFXMLController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Button btnsentrequest;
    @FXML
    private AnchorPane feed1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InputStream imgStream = getClass().getResourceAsStream("/img/chaise.png");
        Image img = new Image(imgStream, 45, 45, false, false);
    }

    @FXML
    private void sentRequest(ActionEvent event) {
         List<Offre> listoffre = new ArrayList<>();
        Offre o1 = new Offre();
        Offre o = new Offre();
        ServiceOffre so = new ServiceOffre();
        if (so.verifierOffre(o) == null) {
            so.ajouterOffre(o1);
        } else {
            System.out.println("already exist");
        }
        System.out.println(listoffre);
        int x = 0, y = 0;
        for (Offre o2 : listoffre) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);
            o2.getID_Product1();
            InputStream imgStream = getClass().getResourceAsStream("/img/tableetchaise.png");
            Image img = new Image(imgStream, 45, 45, false, false);
            ImageView imv = new ImageView(img);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/téléchargement(1).png");
            Image img1 = new Image(imgStream1, 45, 45, false, false);
            ImageView imgv = new ImageView(img1);
//            imv.setOnMouseClicked(MouseEvent -> so.getByProduct(o));
//            imgv.setOnMouseClicked(MouseEvent -> so.getByProduct(o));
            imv.setLayoutX(x +14);
            imv.setLayoutY(y +22);
            imgv.setLayoutX(x + 6);
            imgv.setLayoutY(y +119);
            Button btnsentrequest = new Button("Sent Request");
            an.getChildren().addAll(imv, imgv);
            feed1.getChildren().addAll(an);
        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
//
//        try {
//            Parent root = loader.load();
//            AfficheOffreFXMLController oc = loader.getController();
//            Scene nvScene = new Scene(root);
//            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            s.setScene(nvScene);
//            s.show();
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
    }
}
