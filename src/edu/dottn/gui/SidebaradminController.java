/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class SidebaradminController implements Initializable {

    @FXML
    private Button btnuser;
    @FXML
    private Button btnproducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotouser(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
        btnuser.getScene().setRoot(root);
    }

    @FXML
    private void gotoproducts(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboardproducFXML.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
        btnproducts.getScene().setRoot(root);
    }
    
}
