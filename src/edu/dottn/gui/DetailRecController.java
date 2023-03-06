/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Reclamation;
import edu.dottn.entities.Reponse;
import edu.dottn.services.ServiceReponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ProInfo
 */
public class DetailRecController implements Initializable {

    @FXML
    private TextField taID;
    @FXML
    private TextArea taReclamation;
    @FXML
    private TextArea taReponse;
    @FXML
    private TextField tfStatut;
    @FXML
    private DatePicker dpDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    void setReclamations(Reclamation reclamation) {
        if (reclamation != null) {
            taReclamation.setText(reclamation.getDescription());

            taID.setText(String.valueOf(reclamation.getIdRec()));
            tfStatut.setText(reclamation.getStatus());
            LocalDate dateRec = reclamation.getDateRec().toLocalDateTime().toLocalDate();
            dpDate.setValue(dateRec);
            try{
                ServiceReponse sr = new ServiceReponse();
                Reponse r = sr.getOneById(reclamation.getIdRec());
                if (r.getRep()==null){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("l'équipe va voir votre reclamation bientôt,veuillez patientezS");
            alert.showAndWait();
                }else{
                    taReponse.setText(r.getRep());
                }
            }catch (Exception ex) {
            System.err.println(ex.getMessage());
            }
        } else {
            System.out.println("Reclamation Null");
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRec.fxml"));
        Parent feedParent = loader.load();
        Scene feedScene = new Scene(feedParent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(feedScene);
        stage.show();
    }
    }
    

