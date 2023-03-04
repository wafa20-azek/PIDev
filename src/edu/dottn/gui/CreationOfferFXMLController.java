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
import javafx.scene.control.Alert;
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
    private AnchorPane sentpart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void sentRequest(ActionEvent event) {

        List<Offre> listoffre = new ArrayList<>();
        Offre o1 = new Offre();
        Offre o = new Offre();
        ServiceOffre so = new ServiceOffre();
//        if (so.verifierOffre(o) == null) {
//            so.ajouterOffre(o1);
//        } else {
//            System.out.println("already exist");
//        }
//        System.out.println(listoffre);
        int x = 0, y = 0;
        for (Offre o2 : listoffre) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);
            o2.getID_Product1();
            InputStream imgStream = getClass().getResourceAsStream("/img/tableetchaise.png");
            Image img = new Image(imgStream, 45, 45, false, false);
            ImageView imv4 = new ImageView(img);
//            imv4.setOnMouseClicked(MouseEvent -> so.getByProduct(o2));
            imv4.setLayoutX(x + 14);
            imv4.setLayoutY(y + 22);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/téléchargement(1).png");
            Image img1 = new Image(imgStream1, 45, 45, false, false);
            ImageView imgv = new ImageView(img1);
//            imgv.setOnMouseClicked(MouseEvent -> so.getByProduct(o2));
            imgv.setLayoutX(x + 6);
            imgv.setLayoutY(y + 119);

//            // Ajouter la logique pour enregistrer l'offre envoyée par l'utilisateur connecté
//            if (User1 != null) {
//                // Mettre à jour l'état de l'offre à "ONhold"
//                o1.setState(Offre.ON_HOLD);
//
//                // Ajouter l'utilisateur connecté à l'offre
//                o1.setUser(currentUser);
//
//                // Ajouter l'offre à la liste des offres envoyées par l'utilisateur connecté
//                userSentOffers.add(o1);
//            }
//                // Mettre à jour le bouton "Sent Request" pour indiquer que l'offre a été envoyée
            if (so.verifierOffre(o) == null) {
                so.ajouterOffre(o1);
                btnsentrequest.setText("Offer Sent");
                btnsentrequest.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Offer already exists");
                alert.setContentText("The offer you are trying to add already exists.");

                alert.showAndWait();
            }
            Button btnsentrequest = new Button("Sent Request");
            System.out.println(listoffre);
//        if (so.verifierOffre(o) == null) {
//            so.ajouterOffre(o1);
//        } else{
//            System.out.println("already exist");
//        }
//        System.out.println(listoffre);
            an.getChildren().addAll(imv4, imgv);
            sentpart.getChildren().addAll(an);
//  
        }
    }

    @FXML
    private void backtomyoffer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnback.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
