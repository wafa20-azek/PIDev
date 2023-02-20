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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
// @FXML
// private TextField tfoffer;
// @FXML
//   private TextField tfdescription;
// 

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
//     @FXML 
//   private void btnConfirmerAction(ActionEvent event) {
//        
//// Offre o = new Offre(0, 0, name, status);
//        System.out.println(o);
//        ServiceOffre so = new ServiceOffre();
//        so.ajouterOffre(o);
//        
//        Alert a = new Alert(Alert.AlertType.ERROR, "Personne added !", ButtonType.FINISH);
//        a.showAndWait();
//    }   

    private void consulterOffer(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnMyOffer.getScene().setRoot(root);
        Offre o = new Offre(0, 0, 0, "", "");
        ServiceOffre oc = new ServiceOffre();
        oc.getAll();
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
        Offre o1 = new Offre(0, 0, "", "");
        ServiceOffre so = new ServiceOffre();
      so.getBYStatus("On_Hold");

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
        Offre o1 = new Offre(0, 0, "", "");
        ServiceOffre so = new ServiceOffre();
        so.getBYStatus("Declined");

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
        Offre o1 = new Offre(0, 0, "", "");
        ServiceOffre so = new ServiceOffre();
       so.getBYStatus("Accepted");

    }

    @FXML
    private void ajouteroffer(ActionEvent event) {
    }


}
