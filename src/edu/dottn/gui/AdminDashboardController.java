/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private GridPane CardGrid;
    private List<User> CardList = new ArrayList();
    MemberServices m1;
    User P = null;
    @FXML
    private ImageView image;
    @FXML
    private Label nameAdmin;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         m1 = new MemberServices();
        CardList = m1.getAllById();
        int columns = 0;
        int rows = 1;
        for (int i = 0; i < CardList.size(); i++) {
            try {
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("UserCard.fxml"));
                VBox cardBox = fxml.load();
                
                UserCardController userCard= fxml.getController();
                userCard.nameLabel(CardList.get(i).getName());
                userCard.emailLabel(CardList.get(i).getEmail());
                if(columns == 3){
                  columns=0;
                  rows++;
                }
                CardGrid.add(cardBox, columns++, rows);
                GridPane.setMargin(cardBox, new Insets(10));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    public void setInformation(User o){
        P = o;
        nameAdmin.setText(P.getName());
    }

    @FXML
    private void adminLogout(MouseEvent event) {
        m1.logOut(P.getIdUser());
        NavigationController.changeLoginPage(event,"LoginPage.fxml");
    }

}
