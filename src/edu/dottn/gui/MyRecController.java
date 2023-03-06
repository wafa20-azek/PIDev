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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class MyRecController implements Initializable {

    @FXML
    private VBox recList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceReclamation sr = new ServiceReclamation();
        ///*******CurrentUserrrr*******
        List<Reclamation> reclamationList = sr.myRec(1);
        for (Reclamation r : reclamationList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Mrec.fxml"));
            try {
            Node recContainer = loader.load();
            MrecController recController = loader.getController();
            recController.setData(r);
            recList.getChildren().add(recContainer);
        } catch (IOException ex) {
                System.err.println(ex.getMessage());
        }
    }
    }    

    @FXML
    private void goTo(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReclamation.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }
    
}
