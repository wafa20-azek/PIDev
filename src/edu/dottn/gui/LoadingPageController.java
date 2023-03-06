/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Admin;
import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import edu.dottn.util.UserSession;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;

import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class LoadingPageController implements Initializable {

    @FXML
    private Text idText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        Timeline timeline = new Timeline();

        MemberServices m1 = new MemberServices();
        Preferences prefs = Preferences.userNodeForPackage(TwoFactorAuthenticationController.class);
        if (!prefs.get("iduser", "default").contains("default")) {
            User P = m1.getOneById(Integer.parseInt(prefs.get("iduser", "default")));
            if (P instanceof Member) {
                UserSession us=new UserSession();
                us.setUser(P);
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(2),
                        e -> NavigationController.openSession("feedproductFXML.fxml", P, idText)));
            }

         else if (P instanceof Admin) {
                UserSession us=new UserSession();
                us.setUser(P);
                System.out.println("hellooooo");
                timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(2),
                        e -> NavigationController.openSessionadmin("AdminDashboard.fxml", P, idText)));
            }

        }else
        {
            timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(2),
                    e -> {
                        try {
                             FXMLLoader loader = new FXMLLoader(getClass().getResource("hero.fxml"));
                            Parent root = loader.load();
                            idText.getScene().setRoot(root);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }));
        }
//        User P = m1.verifSession();
//        if (P != null) {
//            timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(2),
//                    e -> NavigationController.openSession("HomePage.fxml", P, idText)));
//
//        } else {
//            timeline.getKeyFrames().add(new KeyFrame(javafx.util.Duration.seconds(2),
//                    e -> {
//                        try {
//                            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource("Inscription.fxml"));
//                            Parent root = loader.load();
//                            idText.getScene().setRoot(root);
//                        } catch (IOException ex) {
//                            System.out.println(ex.getMessage());
//                        }
//                    }));
//
//        }
        timeline.play();

        }

}
