/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import edu.dottn.entities.Event;
import edu.dottn.entities.User;
import edu.dottn.services.ServiceEvent;
import edu.dottn.util.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class EventDetailController implements Initializable {

    @FXML
    private TextField tfEvent;
    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfStatut;
    @FXML
    private DatePicker dpDate;
    @FXML
    private WebView mappView;
    @FXML
    private Button bParticiper;
    private WebEngine webEngine;
    Event a = new Event();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     *
     * @param ev
     */
    public void setEvents(Event ev) {
        a = ev;
        if (ev != null) {
            tfEvent.setText(ev.getName());
            tfDesc.setText(ev.getDescription());
            tfStatut.setText(String.valueOf(ev.getStatus()));
            dpDate.setValue(ev.getEventDate().toLocalDate());
            String location = ev.getLocation();

            String[] locationParts = location.split(",");
            double latitude = Double.parseDouble(locationParts[0]);
            double longitude = Double.parseDouble(locationParts[1]);
            webEngine = mappView.getEngine();

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
                    + "</script></body></html>");

        }

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventFeed.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }

    /**
     *
     * @param ev
     */
    @FXML
    private void part(ActionEvent event) throws IOException, WriterException {
        ServiceEvent se = new ServiceEvent();
        UserSession us=new UserSession();
        se.participer(a, us.getUser().getIdUser());
        System.out.println(a.getIdEvent());
        String path = "D:\\PIDEV V0\\PIDev\\src\\Quote.png";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQRcode(us.getUser().getName()+" est enregistrer dans la liste des participant a l'event : "+a.getName(), path, charset, hashMap, 400, 400);
        System.out.println("QR Code created successfully.");  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Qr.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();

    }

    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {
//the BitMatrix class represents the 2D matrix of bits  
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
}