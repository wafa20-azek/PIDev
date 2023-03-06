/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.entities.Reclamation;
import edu.dottn.entities.SubCategory;
import edu.dottn.services.ProductServices;

import edu.dottn.services.ServiceReclamation;
import edu.dottn.util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private ComboBox<String> fxProduit;
    @FXML
    private TextArea fxReclamation;
    @FXML
    private Button fxButton;
UserSession us=new UserSession();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    

    @FXML
    private void SaveRec(ActionEvent event) throws IOException {
        String rec =fxReclamation.getText();
        
        if(rec.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Reclamation doit être remplie");
            alert.showAndWait();
        }else{
        
        Reclamation recl= new Reclamation();
        recl.setCustomer(us.getUser());
        recl.setDateRec(Timestamp.valueOf(LocalDateTime.now()));
        recl.setDescription(rec);
        recl.setStatus("ongoing");
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(recl);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRec.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
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
