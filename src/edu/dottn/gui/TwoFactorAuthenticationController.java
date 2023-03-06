/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import edu.dottn.util.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.util.prefs.Preferences;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class TwoFactorAuthenticationController implements Initializable {

    @FXML
    private TextField codeId;
    User P = null;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void returnSignIn(ActionEvent event) {
        NavigationController.changeLoginPage(event, "LoginPage.fxml");
    }

    @FXML
    private void verifCodeUser(ActionEvent event) {
        Preferences prefs = Preferences.userNodeForPackage(TwoFactorAuthenticationController.class);
        try {
            if (!(codeId.getText().isEmpty())) {
                if (!codeId.getText().matches("\\d{6}")) {
                    codeId.setStyle("-fx-border-color: red;");
                    Alert b = new Alert(Alert.AlertType.ERROR, "Only 6 digit", ButtonType.CLOSE);
                    b.showAndWait();
                } else {
                    int numberid = Integer.parseInt(codeId.getText());
                    MemberServices m1 = new MemberServices();
                    System.out.println("code"+numberid);
                    System.out.println("id"+P.getIdUser());
                    if (m1.verifCodeAuth(numberid, P.getIdUser())) {
                         prefs.put("iduser", Integer.toString(P.getIdUser()));
                         UserSession us=new UserSession();
                         us.setUser(P);
                        NavigationController.changeFeedPage(event, P, "feedproductFXML.fxml");
                    } else {
                        Alert b = new Alert(Alert.AlertType.ERROR, "Code Incorrect", ButtonType.CLOSE);
                        b.showAndWait();
                    }
                }
            } else {
                Alert name = new Alert(Alert.AlertType.ERROR, "Field is empty", ButtonType.CLOSE);
                name.showAndWait();
                codeId.setStyle("-fx-border-color: red;");

            }
        } catch (NumberFormatException e) {
            Alert b = new Alert(Alert.AlertType.ERROR, "Enter only number in field", ButtonType.CLOSE);
            b.showAndWait();
        }

    }

    public void setInformation(User o) {
        P = o;
    }
}
