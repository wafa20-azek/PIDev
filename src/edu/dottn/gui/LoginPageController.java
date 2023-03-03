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

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


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
    @FXML
    private Text ForgetId;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ForgetId.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
                NavigationController.changeForgetPassword(e, "ForgetPassword.fxml");
                }
            
    });}
    
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
                    m1.sendCodeAuth(p1.getEmail(), p1.getNumero());
                   
                    NavigationController.changeToTwoFactorAuthentication(event, p1, "TwoFactorAuthentication.fxml");
                    
                }
                else if(p1 != null && p1 instanceof Admin){
                   NavigationController.AdminHomePage(event, p1, "AdminDashboard.fxml");
                }
                else {
                    passwordId.setStyle("-fx-border-color: red;");
                    
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