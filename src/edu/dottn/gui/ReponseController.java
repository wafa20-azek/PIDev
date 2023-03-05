/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Reclamation;
import edu.dottn.entities.Reponse;
import edu.dottn.services.ServiceReclamation;
import edu.dottn.services.ServiceReponse;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class ReponseController implements Initializable {

    Reclamation r = null;
    @FXML
    private Button bAnnuler;
    @FXML
    private Button bEnvoyer;
    @FXML
    private TextArea taReclamation;
    @FXML
    private TextArea taReponse;
    @FXML
    private TextField taID;
    @FXML
    private TextField tfStatut;
    @FXML

    private DatePicker dpDate;

    /**
     *  
     * @param reclamation
     */
    public void setReclamations(Reclamation reclamation) {
        if (reclamation != null) {
            taReclamation.setText(reclamation.getDescription());

            taID.setText(String.valueOf(reclamation.getIdRec()));
            tfStatut.setText(reclamation.getStatus());
            LocalDate dateRec = reclamation.getDateRec().toLocalDateTime().toLocalDate();
            dpDate.setValue(dateRec);
        } else {
            System.out.println("Reclamation Null");
        }
    }

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void sendReponse(ActionEvent event) {
        ServiceReclamation srec = new ServiceReclamation();
        Reponse rep = new Reponse();
        if (taReponse.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Reponse doit être remplie");
            alert.showAndWait();
        } else {
            rep.setReclamation(srec.getOneById(Integer.parseInt(taID.getText())));
            rep.setRep(taReponse.getText());
            rep.setDateRep(rep.getDateRep());
            ServiceReponse sr = new ServiceReponse();
            sr.ajouter(rep);
            Reclamation rec = srec.getOneById(Integer.parseInt(taID.getText()));
            rec.setStatus("Fixed");
            srec.modifier(rec);
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationFeed.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();

    }

}
