/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class ProfilePageController implements Initializable {

    @FXML
    private TextField nameId;
    @FXML
    private TextField addressId;
    @FXML
    private PasswordField newPassId;
    @FXML
    private PasswordField oldPassId;
    @FXML
    private TextField emailId;
    @FXML
    private TextField numeroId;
    private User P;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setInformation(1);

    }

    @FXML
    private void saveChanges(ActionEvent event) {
        MemberServices m = new MemberServices();
        try {
            if (!(nameId.getText().isEmpty() || addressId.getText().isEmpty() || emailId.getText().isEmpty() || numeroId.getText().isEmpty() || newPassId.getText().isEmpty() || oldPassId.getText().isEmpty())) {
                if (!emailId.getText().contains("@")) {
                    emailId.setStyle("-fx-border-color: red;");
                    Alert email = new Alert(Alert.AlertType.ERROR, "email doesn't contain @ ", ButtonType.CLOSE);
                    email.showAndWait();
                }else { //convertir la chaine en number
                    if (!numeroId.getText().matches("\\d{8}")) {
                    numeroId.setStyle("-fx-border-color: red;");
                    Alert b = new Alert(Alert.AlertType.ERROR, "Only 8 digit", ButtonType.CLOSE);
                    b.showAndWait();}
                    else if(m.UpdatePassword(P.getEmail(), newPassId.getText(),P.getPassword(), oldPassId.getText()).isEmpty()){
                         Alert b = new Alert(Alert.AlertType.ERROR, "Old Password incorrect", ButtonType.CLOSE);
                          b.showAndWait();
                    }
                    else {
                     int numberid = Integer.parseInt(numeroId.getText());
                     P.setAddress(addressId.getText());
                     P.setEmail(emailId.getText());
                     P.setName(nameId.getText());
                     P.setNumero(numberid);
                     P.setPassword(m.UpdatePassword(P.getEmail(), newPassId.getText(),P.getPassword(), oldPassId.getText()));
                      MemberServices m1 = new MemberServices();
                      m1.modifierUser(P);
                      //if (add) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, nameId.getText(), ButtonType.OK);
                        Optional<ButtonType> result = a.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            NavigationController.changeHomePage(event,P ,"HomePage.fxml");

                        }
                      //} else {
                        //emailId.setStyle("-fx-border-color: red;"); }
                }}
            } else {
                Alert name = new Alert(Alert.AlertType.ERROR, "More or one Field is empty", ButtonType.CLOSE);
                name.showAndWait();
                if (nameId.getText().isEmpty()) {
                    nameId.setStyle("-fx-border-color: red;");

                } else {
                    nameId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (addressId.getText().isEmpty()) {
                    addressId.setStyle("-fx-border-color: red;");
                } else {
                    addressId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (emailId.getText().isEmpty()) {
                    emailId.setStyle("-fx-border-color: red;");
                } else {
                    emailId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (numeroId.getText().isEmpty()) {
                    numeroId.setStyle("-fx-border-color: red;");
                } else {
                    numeroId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (newPassId.getText().isEmpty()) {
                    newPassId.setStyle("-fx-border-color: red;");
                } else {
                    newPassId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (oldPassId.getText().isEmpty()) {
                    oldPassId.setStyle("-fx-border-color: red;");
                } else {
                    oldPassId.setStyle("-fx-border-color: #c0c0c0;");
                }
            }
        } catch (NumberFormatException e) {
            Alert b = new Alert(Alert.AlertType.ERROR, "Enter only number in field", ButtonType.CLOSE);
            b.showAndWait();
        }
    }

    public void setInformation(int id) {
        MemberServices m = new MemberServices();
        P = m.getOneById(id);
        if (P != null) {
            nameId.setText(P.getName());
            addressId.setText(P.getAddress());
            emailId.setText(P.getEmail());
            numeroId.setText(Integer.toString(P.getNumero()));

        }

    }

}
