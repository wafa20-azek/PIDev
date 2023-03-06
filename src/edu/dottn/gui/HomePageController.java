/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.prefs.BackingStoreException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.util.prefs.Preferences;
/**
 * FXML Controller class
 *
 * @author WALID
 */
public class HomePageController implements Initializable {

    @FXML
    private Text labelWelcome;
    User P = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void btnLogOut(ActionEvent event) {
        try {
            Preferences prefs = Preferences.userNodeForPackage(TwoFactorAuthenticationController.class);
            MemberServices m1 = new MemberServices();
            m1.logOut(P.getIdUser());
            prefs.clear();
            NavigationController.changeLoginPage(event,"LoginPage.fxml");
        } catch (BackingStoreException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setInformation(User o){
        P = o;
    labelWelcome.setText(labelWelcome.getText()+" "+o.getName());}

    @FXML
    private void changeToProfile(ActionEvent event) {
        System.out.println(P);
        //NavigationController.changeProfilePage(event,P,"ProfilePage.fxml");
    }
}
