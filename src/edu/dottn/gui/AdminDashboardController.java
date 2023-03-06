/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    @FXML
    private TextField searchId;
    int columns = 0;
    int rows = 1;
    @FXML
    private CheckBox triId;
    @FXML
    private Button btnproducts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        m1 = new MemberServices();
        CardList = m1.getAllById();

        for (int i = 0; i < CardList.size(); i++) {
            try {
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("UserCard.fxml"));
                VBox cardBox = fxml.load();

                UserCardController userCard = fxml.getController();
                userCard.nameLabel(CardList.get(i).getName());
                userCard.emailLabel(CardList.get(i).getEmail());
                userCard.getActivate(((Member) CardList.get(i)).isActivated());
                    userCard.setAdmin(P);
                if (columns == 3) {
                    columns = 0;
                    rows++;
                }
                CardGrid.add(cardBox, columns++, rows);
                GridPane.setMargin(cardBox, new Insets(10));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        searchId.textProperty().addListener((obs, oldValue, newValue) -> {

            CardGrid.getChildren().clear();
            columns = 0;
            rows = 1;
            CardList = m1.findUserByNom(newValue);
            for (int i = 0; i < CardList.size(); i++) {
                try {
                    FXMLLoader fxml = new FXMLLoader();
                    fxml.setLocation(getClass().getResource("UserCard.fxml"));
                    VBox cardBox = fxml.load();

                    UserCardController userCard = fxml.getController();
                    userCard.nameLabel(CardList.get(i).getName());
                    userCard.emailLabel(CardList.get(i).getEmail());
                    userCard.getActivate(((Member) CardList.get(i)).isActivated());
                    userCard.setAdmin(P);
                    if (columns == 3) {
                        columns = 0;
                        rows++;
                    }
                    CardGrid.add(cardBox, columns++, rows);
                    GridPane.setMargin(cardBox, new Insets(10));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        triId.selectedProperty().addListener((obs, oldValue, newValue) -> {
            CardGrid.getChildren().clear();
            columns = 0;
            rows = 1;
            if (newValue) {
                TreeSet<User> CardListTrie = m1.SortUserByNom();
                for (User i : CardListTrie) {
                    try {
                        FXMLLoader fxml = new FXMLLoader();
                        fxml.setLocation(getClass().getResource("UserCard.fxml"));
                        VBox cardBox = fxml.load();

                        UserCardController userCard = fxml.getController();
                        userCard.nameLabel(i.getName());
                        userCard.getId(i.getIdUser());
                            userCard.getActivate(((Member) i).isActivated());
                    userCard.setAdmin(P);
                        if (columns == 3) {
                            columns = 0;
                            rows++;
                        }
                        CardGrid.add(cardBox, columns++, rows);
                        GridPane.setMargin(cardBox, new Insets(10));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                CardList = m1.getAllById();

                for (int i = 0; i < CardList.size(); i++) {
                    try {
                        FXMLLoader fxml = new FXMLLoader();
                        fxml.setLocation(getClass().getResource("UserCard.fxml"));
                        VBox cardBox = fxml.load();

                        UserCardController userCard = fxml.getController();
                        userCard.nameLabel(CardList.get(i).getName());
                        userCard.emailLabel(CardList.get(i).getEmail());
                        userCard.getActivate(((Member) CardList.get(i)).isActivated());
                    userCard.setAdmin(P);
                        if (columns == 3) {
                            columns = 0;
                            rows++;
                        }
                        CardGrid.add(cardBox, columns++, rows);
                        GridPane.setMargin(cardBox, new Insets(10));
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });
    }

    public void setInformation(User o) {
        P = o;
        System.out.println(P);
       // nameAdmin.setText(P.getName());
    }

    @FXML
    private void adminLogout(MouseEvent event) {
     
        NavigationController.changeLoginPage(event, "LoginPage.fxml");
    }

    @FXML
    private void gotoproducts(ActionEvent event) {
        NavigationController.changeProductdashboradPage(event, P, "dashboardproducFXML.fxml");
    }

}
