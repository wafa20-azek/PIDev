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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class ReclamationFeedController implements Initializable {

    private ListView<Reclamation> fxList;
    @FXML
    private VBox recContainerList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> reclamationList = sr.getAll();
        for (Reclamation r : reclamationList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
            try {
            Node recContainer = loader.load();
            ReclamationController recController = loader.getController();
            recController.setData(r);
            recContainerList.getChildren().add(recContainer);
        } catch (IOException ex) {
                System.err.println(ex.getMessage());
        }
    }
}
        }
        
                
            
        
            
         
       
    
    
   

