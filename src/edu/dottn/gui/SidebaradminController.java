/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private ImageView logout;
    @FXML
    private Button rec;

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

    @FXML
    private void logoutapp(MouseEvent event) throws BackingStoreException {
         Preferences prefs = Preferences.userNodeForPackage(TwoFactorAuthenticationController.class);
            prefs.clear();
            NavigationController.changeLoginPage(event,"LoginPage.fxml");
    }

    @FXML
    private void gotoreclamtion(ActionEvent event) {
        NavigationController.changerecadmin(event, "ReclamationFeed.fxml");
    }
    
}
