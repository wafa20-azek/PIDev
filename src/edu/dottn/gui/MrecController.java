/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Reclamation;
import edu.dottn.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class MrecController implements Initializable {

    private Reclamation reclamation;
    @FXML
    private Label Description;
    @FXML
    private Text dateRec;
    @FXML
    private Label Status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Reclamation r) {
        // Set the data for the Reclamation
        Description.setText(r.getDescription());
        dateRec.setText(String.valueOf(r.getDateRec().toLocalDateTime()));
        Status.setText(r.getStatus());
        reclamation = r;
    }

    @FXML
    private void handleClick(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailRec.fxml"));
        try {
            Parent root = loader.load();

            DetailRecController RepCont = loader.getController();
            RepCont.setReclamations(reclamation);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


 
    @FXML
    private void delete(ActionEvent event) {
          ServiceReclamation sr = new ServiceReclamation();
        sr.supprimer(reclamation.getIdRec());
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRec.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
        
    }

}
