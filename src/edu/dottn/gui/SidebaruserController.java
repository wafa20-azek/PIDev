/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import edu.dottn.util.UserSession;
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
public class SidebaruserController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button explore;
    @FXML
    private Button inventory;
UserSession us=new UserSession();
    @FXML
    private ImageView logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotofeed(ActionEvent event) {
        NavigationController.changeFeedPage(event, us.getUser(), "feedproductFXML.fxml");
    }

    @FXML
    private void gotosearch(ActionEvent event) {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("feedproductFXML.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FeedproductFXMLController moc = loader.getController();
        moc.showfilters(event);
        explore.getScene().setRoot(root);
    }

    @FXML
    private void gotomyproducts(ActionEvent event) {
        NavigationController.changeMyproductsPage(event, us.getUser(), "listproductFXML.fxml");
    }

    @FXML
    private void logoutapp(MouseEvent event) {
          try {
            Preferences prefs = Preferences.userNodeForPackage(TwoFactorAuthenticationController.class);
            MemberServices m1 = new MemberServices();
            m1.logOut(us.getUser().getIdUser());
            prefs.clear();
            NavigationController.changeLoginPage(event,"LoginPage.fxml");
        } catch (BackingStoreException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
}
