/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Event;
import edu.dottn.services.ServiceEvent;
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
public class MyEventController implements Initializable {
 ServiceEvent se = new ServiceEvent();
    @FXML
    private VBox eventListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        se.deletePastEvents();
        ///// *****************Current User***/////
        List<Event> eventList = se.getById(3);
        for (Event e : eventList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEv.fxml"));
            try {
                Node eContainer = loader.load();
                MyEvController ec = loader.getController();
                ec.setData(e);
                eventListContainer.getChildren().add(eContainer);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        // TODO
    }    

    @FXML
    private void nav(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }
    
}
