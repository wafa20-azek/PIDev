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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class EventFeedController implements Initializable {
    ServiceEvent se = new ServiceEvent();
    @FXML
    private VBox eventListContainer;
    @FXML
    private TextField tfSearch;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        se.deletePastEvents();
        List<Event> eventList = se.getAll();
        for (Event e : eventList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ev.fxml"));
            try {
                Node eContainer = loader.load();
                EvController ec = loader.getController();
                ec.setData(e);
                eventListContainer.getChildren().add(eContainer);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
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

    @FXML
    private void recherche(MouseEvent event) {
       
        List<Event> s = se.search(tfSearch.getText());
        eventListContainer.getChildren().clear();
        for (Event e : s) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ev.fxml"));
            try {
                Node eContainer = loader.load();
                EvController ec = loader.getController();
                ec.setData(e);
                eventListContainer.getChildren().add(eContainer);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
   
            }
        }

    }

    @FXML
    private void Range(MouseEvent event) {
        List<Event> s=se.getEventsByDateRange(Date.valueOf(debut.getValue()),Date.valueOf( fin.getValue()));
        eventListContainer.getChildren().clear();
        for (Event e : s) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ev.fxml"));
            try {
                Node eContainer = loader.load();
                EvController ec = loader.getController();
                ec.setData(e);
                eventListContainer.getChildren().add(eContainer);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
             
            }
        }
        
    }
}
