/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Produit;
import edu.dottn.entities.Reclamation;
import edu.dottn.services.ServiceProduit;
import edu.dottn.services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class AddReclamationController implements Initializable {

    @FXML
    private ComboBox<Produit> fxProduit;
    @FXML
    private TextArea fxReclamation;
    @FXML
    private Button fxButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceProduit sp = new ServiceProduit();
    List<Produit> produits = sp.getall();
    ObservableList<Produit> observableProduits = FXCollections.observableList(produits);
    fxProduit.setItems(observableProduits);
    fxProduit.getSelectionModel().select(0);
    }    

    @FXML
    private void SaveRec(ActionEvent event) {
        String rec =fxReclamation.getText();
        int produit= fxProduit.getValue().getId() ;
        if(rec.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Reclamation doit être remplie");
            alert.showAndWait();
        }else{
        
        Reclamation recl= new Reclamation();
        recl.setCustomer(1);
        recl.setDateRec(recl.getDateRec());
        recl.setProduct(produit);
        recl.setDescription(rec);
        recl.setStatus("ongoing");
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(recl);
    }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRec.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();

    }
}