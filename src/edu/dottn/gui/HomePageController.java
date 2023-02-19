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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

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
      MemberServices m1 = new MemberServices();
      m1.logOut(P.getIdUser());
    }
    public void setInformation(User o){
        P = o;
    labelWelcome.setText(labelWelcome.getText()+" "+o.getName());}
}
