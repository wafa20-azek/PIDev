/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class UserCardController implements Initializable {

    @FXML
    private Label emailId;
    @FXML
    private Label nameId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void emailLabel(String email){
        emailId.setText(email);
    }

    
    public void nameLabel(String name){
        nameId.setText(name);
    }

}
