/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceOffre;
import java.io.IOException;
import java.net.URL;
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
    private Parent fxml;
    @FXML
    private AnchorPane root;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void sentRequest(ActionEvent event) {
        Offre o1 = new Offre();
        Offre o = new Offre();
        ServiceOffre so = new ServiceOffre();
        if (so.verifierOffre(o) == null) {
            so.ajouterOffre(o1);
        } else {
            System.out.println("already exist");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));

        try {
            Parent root = loader.load();
            AfficheOffreFXMLController oc = loader.getController();
            Scene nvScene = new Scene(root);
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.setScene(nvScene);
            s.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
