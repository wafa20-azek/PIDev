/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.util.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class NavbarController implements Initializable {

    @FXML
    private Button products;
    @FXML
    private Button addproduct;
    @FXML
    private Button offers;
    @FXML
    private Label user;
    UserSession us=new UserSession();
    @FXML
    private AnchorPane profile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user.setText(us.getUser().getName());
        
    }   
    

    @FXML
    private void gotomyproducts(ActionEvent event) {
        NavigationController.changeMyproductsPage(event, us.getUser(), "listproductFXML.fxml");
    }

    @FXML
    private void gotoaddproduct(ActionEvent event) {
        NavigationController.changeAddproductPage(event, us.getUser(), "addproductFXML.fxml");
    }

    @FXML
    private void gotomyoffers(ActionEvent event) {
        NavigationController.changemyoffersPage(event, us.getUser(), "AfficheOffreFXML.fxml");
    }

    @FXML
    private void gotoprofile(MouseEvent event) {
        NavigationController.changeProfilePage(event, us.getUser(), "ProfilePage.fxml");
    }
    
}
