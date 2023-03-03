/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;


import edu.dottn.services.MemberServices;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class ForgetPasswordController implements Initializable {

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
    private void resetPass(ActionEvent event) {
        if (!(emailId.getText().isEmpty())) {
            if (!emailId.getText().contains("@")) {
                emailId.setStyle("-fx-border-color: red;");
                Alert email = new Alert(Alert.AlertType.ERROR, "email doesn't contain @ ", ButtonType.CLOSE);
                email.showAndWait();
            } else {
                MemberServices m1 = new MemberServices();
                m1.PasswordResetEmail(emailId.getText());
                Alert a = new Alert(Alert.AlertType.INFORMATION,"password sent to your email", ButtonType.OK);
                            Optional<ButtonType> result = a.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                NavigationController.changeLoginPage(event, "LoginPage.fxml");
                               
                            }
            }
        } else {
            Alert name = new Alert(Alert.AlertType.ERROR, "Field is empty", ButtonType.CLOSE);
            name.showAndWait();
            
                emailId.setStyle("-fx-border-color: red;");

            
    }
    
}

    @FXML
    private void returnSignIn(ActionEvent event) {
        NavigationController.changeLoginPage(event, "LoginPage.fxml");
    }
}
