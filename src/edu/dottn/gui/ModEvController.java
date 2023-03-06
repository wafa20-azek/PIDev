/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Event;
import edu.dottn.services.ServiceEvent;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class ModEvController implements Initializable {

    @FXML
    private WebView mapView;
    @FXML
    private TextField tfEvent;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea taDesc;
    @FXML
    private Button bEnvoyer;
    @FXML
    private Button bAnnuler;
    private WebEngine webEngine;
    private JSObject window;
    private String location;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    void setEvents(Event ev) {

        if (ev != null) {
            System.out.println(ev.getIdEvent());
            tfEvent.setText(ev.getName());
            taDesc.setText(ev.getDescription());
            id.setText(String.valueOf(ev.getIdEvent()));
            dpDate.setValue(ev.getEventDate().toLocalDate());
            String location = ev.getLocation();

            String[] locationParts = location.split(",");
            double latitude = Double.parseDouble(locationParts[0]);
            double longitude = Double.parseDouble(locationParts[1]);
            webEngine = mapView.getEngine();

            webEngine.setOnAlert(event -> System.out.println("JavaScript Alert: " + event.getData()));

            webEngine.loadContent("<html><head>"
                    + "<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.css' />"
                    + "<style>html, body, #mapView { margin: 0; padding: 0; width: 100%; height: 100%; }</style>"
                    + "</head><body>"
                    + "<div id='mapView'></div>"
                    + "<script src='https://cdn.jsdelivr.net/npm/leaflet@1.7.1/dist/leaflet.js'></script>"
                    + "<script>"
                    + "var mymap = L.map('mapView').setView([" + latitude + "," + longitude + "], 8);"
                    + "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {"
                    + "    attribution: 'Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> contributors',"
                    + "    maxZoom: 18"
                    + "}).addTo(mymap);"
                    + "var marker = L.marker([" + latitude + ", " + longitude + "]).addTo(mymap);"
                    + "var markerr;"
                    + "mymap.on('click', function(event) {"
                    + "if (markerr) {"
                    + "ymap.removeLayer(markerr);}"
                    + "markerr = L.marker(event.latlng).addTo(mymap);"
                    + "var location = event.latlng.lat + ',' + event.latlng.lng;"
                    + "window.locationCallback.setLocation(location);});"
                    + "</script></body></html>");
            webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    window = (JSObject) webEngine.executeScript("window");
                    window.setMember("locationCallback", this);
                }
            });

        }

    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @FXML
    private void saveEv(ActionEvent event) throws IOException {
        Event.Status s;
        int idev= Integer.parseInt(id.getText());
        String nom = tfEvent.getText();
        System.out.println(nom);
        String desc = taDesc.getText();
        LocalDate localDate = dpDate.getValue();
        Date date = Date.valueOf(localDate);
        String loc = getLocation();
        if (localDate.isBefore(LocalDate.now())) {
            s = Event.Status.completed;
        } else {
            s = Event.Status.ongoing;
        }

        Event eve = new Event();
        if(nom.isEmpty()||desc.isEmpty()||loc.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("tous les champs doit étre a jour ");
            alert.showAndWait();
        }else{
        eve.setName(nom);
        System.out.println(eve.getName());
        eve.setIdEvent(idev);
        eve.setDescription(desc);
        eve.setEventDate(date);
        eve.setLocation(loc);
        eve.setStatus(s);
        ServiceEvent se = new ServiceEvent();
        se.modifier(eve);}

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvent.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MyEvent.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }

}
