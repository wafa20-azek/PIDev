/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class HeroController implements Initializable {

    @FXML
    private AnchorPane hero;
    @FXML
    private Text userlabel;
    @FXML
    private Text associationlabel;
    @FXML
    private Text texthey;
    @FXML
    private AnchorPane useranchor;
    @FXML
    private AnchorPane associationanchor;
    @FXML
    private ImageView imageuser;
    @FXML
    private ImageView imageassociation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      associationanchor.setOnMouseClicked(e->{
                  try {
                    Stage stage = (Stage) associationanchor.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                   }
      });
      
      associationanchor.setOnMouseEntered(e->{
          associationlabel.setVisible(true);
          associationlabel.setOpacity(0.8);
          imageassociation.setOpacity(0.9);
          associationanchor.setOpacity(0.9);
          //imageassociation.setFitHeight(300.0);
          //imageassociation.setFitWidth(300.0);
          imageassociation.scaleXProperty().set(1.15);
          imageassociation.scaleYProperty().set(1.15);
       });
      
      associationanchor.setOnMouseExited(e->{
          associationlabel.setVisible(false);
          imageassociation.setOpacity(0.3);
           associationanchor.setOpacity(0.5);
            imageassociation.scaleXProperty().set(1.0);
          imageassociation.scaleYProperty().set(1.0);
      });
      
       useranchor.setOnMouseEntered(e->{
          userlabel.setVisible(true);
          userlabel.setOpacity(0.8);
          imageuser.setOpacity(0.9);
          useranchor.setOpacity(0.9);
          imageuser.scaleXProperty().set(1.15);
          imageuser.scaleYProperty().set(1.15);
      });
       
      useranchor.setOnMouseExited(e->{
          userlabel.setVisible(false);
          imageuser.setOpacity(0.3);
          useranchor.setOpacity(0.5);
          imageuser.scaleXProperty().set(1.0);
          imageuser.scaleYProperty().set(1.0);
      });
      
    }    
    
}
