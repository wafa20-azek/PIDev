/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceOffre;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bochr
 */
public class AfficheOffreFXMLController implements Initializable {

    @FXML
    private Button btnOn_Hold;
    @FXML
    private Button btnAccepted;
    @FXML
    private Button btnDeclined;
    @FXML
    private Button btnMyProduct;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnMyOffer;

    private Button btncreationOffer;
    @FXML
    private Button createOffer;

// @FXML
// private TextField tfoffer;
// @FXML
//   private TextField tfdescription;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void offerStatus(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnOn_Hold.getScene().setRoot(root);
        Offre o1 = new Offre();
        ServiceOffre so = new ServiceOffre();
        so.getBYStatus("On_Hold");

    }

    @FXML
    private void offerStatus1(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnAccepted.getScene().setRoot(root);
        Offre o1 = new Offre();
        ServiceOffre so = new ServiceOffre();
        so.getBYStatus("Accepted");

    }

    @FXML
    private void offerStatus2(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnDeclined.getScene().setRoot(root);
        Offre o1 = new Offre();
        ServiceOffre so = new ServiceOffre();
        so.getBYStatus("Declined");

    }

    @FXML
    private void consulterOffers(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ServiceOffre serviceOffre = new ServiceOffre();
            Offre o = new Offre();
            List<Offre> offres = serviceOffre.getAll();

        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnMyOffer.getScene().setRoot(root);

    }

    @FXML
    private void createoffer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationOfferFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene nv = new Scene(root);
            Stage s = (Stage) createOffer.getScene().getWindow();
            s.setScene(nv);
            s.show();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }
    
@FXML
private void Acceptedoffer(MouseEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
    Parent root = null;
    try {
        root = loader.load();
    } catch (IOException e) {
        System.out.println("Error :" + e.getMessage());
    }


    Offre o1 = new Offre();
    ServiceOffre so = new ServiceOffre();
    so.AccepterOffre(o1);
}

private void handleButtonClicked(MouseEvent event) {
    if (event.getButton() == MouseButton.PRIMARY) {
        // code à exécuter lorsque le bouton est cliqué avec le bouton gauche de la souris
        Acceptedoffer(event);
    }
}

//    @FXML
//    private void offerStatus1(MouseEvent event) {
//          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//
//        btnDeclined.getScene().setRoot(root);
//        Offre o1 = new Offre();
//        ServiceOffre so = new ServiceOffre();
//        so.AccepterOffre(o1);
//    }
//     
//
//    @FXML
//    private void offerStatus2(MouseEvent event) {
//          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//
//        btnDeclined.getScene().setRoot(root);
//        Offre o1 = new Offre();
//        ServiceOffre so = new ServiceOffre();
////        so.
//    }

    

}
