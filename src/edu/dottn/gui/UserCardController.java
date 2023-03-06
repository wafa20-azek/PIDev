/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class UserCardController implements Initializable {
private boolean activated;
    @FXML
    private Label emailId;
    @FXML
    private Label nameId;
    @FXML
    private Button deleteId;
    @FXML
    private Button BanId;
MemberServices m1 = new MemberServices();
public ButtonType foo = new ButtonType("delete", ButtonBar.ButtonData.OK_DONE);
     public   ButtonType bar = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
     private int idUser;
     private User P = null;
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
    public void getId(int id) {
        idUser = id;
    }
    public void setAdmin(User o){
        P = o;
    }

    @FXML
    private void onDelete(ActionEvent event) {
        Alert alert = new Alert(AlertType.WARNING,
                "Are you sure to delete",
                foo,
                bar);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.orElse(bar) == foo) {
            m1.deleteUser(idUser);
            NavigationController.AdminHomePage(event, P, "AdminDashboard.fxml");
            
        }

    }

    @FXML
    private void onClickBan(ActionEvent event) {
         if (BanId.getText().equals("Ban")) {
            m1.banUser(idUser, false);
            BanId.setText("Activate");
        } else {
            m1.banUser(idUser, true);
            BanId.setText("Ban");
        }

    }
    public void getActivate(boolean act) {

        activated = act;
        if (activated) {
            BanId.setText("Ban");
        } else {
            BanId.setText("Activate");
        }
    }

}
