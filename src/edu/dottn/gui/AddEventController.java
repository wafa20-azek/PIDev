/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;


import edu.dottn.entities.Event;
import edu.dottn.entities.Event.Status;
import edu.dottn.services.ServiceEvent;
import java.io.IOException;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class AddEventController implements Initializable {

    @FXML
    private WebView mapView;
    private WebEngine webEngine;
    @FXML
    private TextField tfEvent;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea taDesc;
    private JSObject window;
      private String location;
    @FXML
    private Button bEnvoyer;
    @FXML
    private Button bAnnuler;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         webEngine = mapView.getEngine();

        webEngine.setOnAlert(event -> System.out.println("JavaScript Alert: " + event.getData()));

        webEngine.loadContent("<html><head>" +
            "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.css' />" +
            "<style>html, body, #mapView { margin: 0; padding: 0; width: 100%; height: 100%; }</style>" +
            "</head><body>" +
            "<div id='mapView'></div>" +
            "<script src='https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.js'></script>" +
            "<script>" +
            "var mymap = L.map('mapView').setView([33.8869, 9.5375], 13);" +
            "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {" +
            "    attribution: 'Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> contributors'," +
            "    maxZoom: 18" +
            "}).addTo(mymap);" +
            "var marker;" +
            "mymap.on('click', function(event) {" +
            "    if (marker) {" +
            "        mymap.removeLayer(marker);" +
            "    }" +
            "    marker = L.marker(event.latlng).addTo(mymap);" +
            "    var location = event.latlng.lat + ',' + event.latlng.lng;" +
            "    window.locationCallback.setLocation(location);" +
            "});" +
            "</script></body></html>");

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                window = (JSObject) webEngine.executeScript("window");
                window.setMember("locationCallback", this);
            }
        });
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @FXML
    private void saveEv(ActionEvent event) {
        Status s;
        String nom = tfEvent.getText();
        String desc = taDesc.getText();
        LocalDate localDate = dpDate.getValue();
         Date date =  Date.valueOf(localDate);
         String loc = getLocation();
         if (localDate.isBefore(LocalDate.now())){
            s = Status.completed;
         }else{
             s = Status.ongoing;
         }
         if(nom.isEmpty()||desc.isEmpty()||dpDate.getValue()==null||loc.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("tous les champs doit être remplie");
            alert.showAndWait();
        }else{
             Event ev = new Event();
             ev.setName(nom);
             ev.setDescription(desc);
             ev.setEventDate(date);
             ev.setLocation(loc);
             ev.setStatus(s);
             ServiceEvent se = new ServiceEvent();
             se.ajouter(ev);
         }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventFeed.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();

    }

   

   
}

