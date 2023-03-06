/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;


import edu.dottn.entities.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class EvController implements Initializable {
    Event ev = new Event();
    @FXML
    private Label ftName;
    @FXML
    private Text dateEvent;
    @FXML
    private Text tStatut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setData(Event e) {
        // Set the data for the Reclamation
        ftName.setText(e.getName());
        dateEvent.setText(String.valueOf(e.getEventDate().toLocalDate()));
        tStatut.setText(String.valueOf(e.getStatus()));      
        ev = e;
    }
    @FXML
    private void handleClick(MouseEvent event) {
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetail.fxml"));
        try {
            Parent root = loader.load();
       
        EventDetailController evdCont = loader.getController();
    evdCont.setEvents(ev);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show(); } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
    }  
}
