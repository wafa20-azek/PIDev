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
public class InscriptionController implements Initializable {

    @FXML
    private TextField nameId;
    @FXML
    private PasswordField passwordId;
    @FXML
    private TextField addressId;
    @FXML
    private TextField emailId;
    @FXML
    private PasswordField repeatpassId;
    @FXML
    private TextField numberId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSignUp(ActionEvent event) {
        try {
            if (!(nameId.getText().isEmpty() || addressId.getText().isEmpty() || emailId.getText().isEmpty() || numberId.getText().isEmpty() || passwordId.getText().isEmpty() || repeatpassId.getText().isEmpty())) {
                if (!passwordId.getText().equals(repeatpassId.getText())) {
                    passwordId.setStyle("-fx-border-color: red;");
                    repeatpassId.setStyle("-fx-border-color: red;");
                    Alert repeatpass = new Alert(Alert.AlertType.ERROR, "verif your password", ButtonType.CLOSE);
                    repeatpass.showAndWait();
                } else if (!emailId.getText().contains("@")) {
                    emailId.setStyle("-fx-border-color: red;");
                    Alert email = new Alert(Alert.AlertType.ERROR, "email doesn't contain @ ", ButtonType.CLOSE);
                    email.showAndWait();
                } else {
                    //convertir la chaine en number
                    
                    if (!numberId.getText().matches("\\d{8}")) {
                        numberId.setStyle("-fx-border-color: red;");
                        Alert b = new Alert(Alert.AlertType.ERROR, "Only 8 digit", ButtonType.CLOSE);
                        b.showAndWait();
                    } else {
                        int numberid = Integer.parseInt(numberId.getText());
                        User p1 = new Member(nameId.getText(), addressId.getText(), emailId.getText(), passwordId.getText(), numberid, 0);
                        MemberServices m1 = new MemberServices();
                        boolean add = m1.ajouterUser(p1);
                        if (add) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION, nameId.getText(), ButtonType.OK);
                            Optional<ButtonType> result = a.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                NavigationController.changeLoginPage(event, "LoginPage.fxml");
                            }
                        } else {
                            emailId.setStyle("-fx-border-color: red;");
                            
                        }
                    }
                }
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
                if (numberId.getText().isEmpty()) {
                    numberId.setStyle("-fx-border-color: red;");
                } else {
                    numberId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (passwordId.getText().isEmpty()) {
                    passwordId.setStyle("-fx-border-color: red;");
                } else {
                    passwordId.setStyle("-fx-border-color: #c0c0c0;");
                }
                if (repeatpassId.getText().isEmpty()) {
                    repeatpassId.setStyle("-fx-border-color: red;");
                } else {
                    repeatpassId.setStyle("-fx-border-color: #c0c0c0;");
                }
            }
        } catch (NumberFormatException e) {
            Alert b = new Alert(Alert.AlertType.ERROR, "Enter only number in field", ButtonType.CLOSE);
            b.showAndWait();
        }
    }

    @FXML
    private void btnSignIn(ActionEvent event) {
        NavigationController.changeLoginPage(event, "LoginPage.fxml");

    }

}
