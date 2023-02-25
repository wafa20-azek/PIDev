
package edu.dottn.gui;


<<<<<<< HEAD
=======

>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
import edu.dottn.entities.Association;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import edu.dottn.services.AssociationServices;
<<<<<<< HEAD
=======
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.input.KeyCode;
=======
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
import javafx.stage.Stage;


public class FXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signinbtn; 
    
    @FXML
    private Button signupbtn;
    @FXML
    private Label forgottext;
<<<<<<< HEAD
    
    private ActionEvent event;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                signinn(this.event);
            }
        });
        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                signinn(this.event);
            }
        });
    }  
    
    public void goToSignup(ActionEvent event){
=======
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
        public void goToSignup(ActionEvent event){
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
            
                try {
                    Stage stage = (Stage) signinbtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("SignupAssociation.fxml"));
                    Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 1280, 700);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException ex) {
<<<<<<< HEAD
                    System.out.println(ex.getMessage());    
=======
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
                }
        
        }
    
    @FXML
<<<<<<< HEAD
    public void signinn(ActionEvent event){
=======
       public void signinn(ActionEvent event){
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
           
            String username1 = this.username.getText();
            String password2 = this.password.getText();
            Association authenticatedAssociation = authenticate(username1, password2);
             if (authenticatedAssociation != null) {
                try {
                    Stage stage = (Stage) signinbtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Username or Password");
            alert.showAndWait();
        }
            
        
       }
      
<<<<<<< HEAD
    public Association authenticate(String username, String password) {
=======
      public Association authenticate(String username, String password) {
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
            AssociationServices as = new AssociationServices();
            Association association = as.Signin(username, password);
            return association;
}

    
}
