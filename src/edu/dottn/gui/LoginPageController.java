/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Admin;
import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.net.URL;
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
public class LoginPageController implements Initializable {

    @FXML
    private PasswordField passwordId;
    @FXML
    private TextField emailId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSignUp(ActionEvent event) {
         NavigationController.changeSignUpPage(event,"Inscription.fxml");
    }
    @FXML
    private void btnSignIn(ActionEvent event) {

        if (!(emailId.getText().isEmpty() || passwordId.getText().isEmpty())) {
            if (!emailId.getText().contains("@")) {
                emailId.setStyle("-fx-border-color: red;");
                Alert email = new Alert(Alert.AlertType.ERROR, "email doesn't contain @ ", ButtonType.CLOSE);
                email.showAndWait();
            } else {
                MemberServices m1 = new MemberServices();
                User p1 = m1.authenticateUser(emailId.getText(), passwordId.getText());

                if (p1 != null && p1 instanceof Member) {
                    NavigationController.changeHomePage(event, p1, "HomePage.fxml");
                }
                else if(p1 != null && p1 instanceof Admin){
                    Alert admin = new Alert(Alert.AlertType.CONFIRMATION, "welcome Admin ", ButtonType.CLOSE);
                      admin.showAndWait();
                }
                else {
                    passwordId.setStyle("-fx-border-color: red;");
                    Alert password = new Alert(Alert.AlertType.ERROR, "password incorrect", ButtonType.CLOSE);
                    password.showAndWait();
                }
            }
        } else {
            Alert name = new Alert(Alert.AlertType.ERROR, "Field is empty", ButtonType.CLOSE);
            name.showAndWait();
            if (emailId.getText().isEmpty()) {
                emailId.setStyle("-fx-border-color: red;");

            } else {
                emailId.setStyle("-fx-border-color: #c0c0c0;");
            }

            if (passwordId.getText().isEmpty()) {
                passwordId.setStyle("-fx-border-color: red;");
            } else {
                passwordId.setStyle("-fx-border-color: #c0c0c0;");
            }

        }

    }
}