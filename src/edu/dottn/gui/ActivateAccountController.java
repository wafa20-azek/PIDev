/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.services.MemberServices;
import java.net.URL;
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
public class ActivateAccountController implements Initializable {

    @FXML
    private TextField codeId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void activateAcc(ActionEvent event) {
        MemberServices m1 = new MemberServices();
        if (!codeId.getText().isEmpty()) {
            if(m1.activateAccount(codeId.getText())){
                System.out.println("verrified");
            }
        } else {
            Alert b = new Alert(Alert.AlertType.ERROR, "Field is empty", ButtonType.CLOSE);
            b.showAndWait();
        }
    }

}
