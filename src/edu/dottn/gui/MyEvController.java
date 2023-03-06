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
public class MyEvController implements Initializable {
    ServiceEvent se = new ServiceEvent();
    @FXML
    private Label ftName;
    @FXML
    private Text dateEvent;
    @FXML
    private Text tStatut;
    Event ev = new Event();
    @FXML
    private Text idevent;

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
        idevent.setText(String.valueOf(e.getIdEvent()));
        System.out.println(e.getIdEvent());
        ev = e;
    }

    @FXML
    private void delete(ActionEvent event) {
        se.supprimer(ev.getIdEvent());
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void update(ActionEvent event) {
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModEv.fxml"));
        try {
            Parent root = loader.load();
       
        ModEvController evdCont = loader.getController();
            System.out.println(ev.getIdEvent());
    evdCont.setEvents(ev);
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show(); } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    
        
    }

    
    
}


