
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class SignupAssociationController implements Initializable {

    @FXML
    private Button signupbtn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signinbtn;
    @FXML
    private TextField Assocname;
    @FXML
    private PasswordField repeatpassword;
    @FXML
    private TextField email;
    @FXML
    private TextField location;
    @FXML
    private TextField phone;
    
    AssociationServices as = new AssociationServices();
   
         public void backToSignin(ActionEvent event){
            
                try {
                    Stage stage = (Stage) signupbtn.getScene().getWindow();
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
        
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
    private void clearFields() {
    Assocname.clear();
        username.clear();
        email.clear();
        location.clear();
        phone.clear();
        password.clear();
        repeatpassword.clear();
}
    
    public boolean isPhoneNumberValid(String phoneNumber) {
    String pattern = "\\d{8}";
    return phoneNumber.matches(pattern);
}

    public void signup(ActionEvent event) {
    
        String name = Assocname.getText();
        String usernameAsso = username.getText();
        String emailAsso = email.getText();
        String addressAsso = location.getText();
        String phoneAsso = phone.getText();
        String password1 = password.getText();
        String repeatpassword1 = repeatpassword.getText();
        
        if (name.isEmpty() || usernameAsso.isEmpty() || emailAsso.isEmpty() || addressAsso.isEmpty() || phoneAsso.isEmpty() || password1.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("One or more inputs are empty");
            alert.showAndWait();
            }else if (!emailAsso.contains("@")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid email format");
                alert.showAndWait();
            }else if (!password1.equals(repeatpassword1)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Passwords do not match");
                alert.showAndWait();
            } else if (!isPhoneNumberValid(phoneAsso)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid phone number");
            alert.showAndWait();
            }else{
                
                Association a = new Association("association" ,name, usernameAsso, password1, emailAsso, addressAsso, Integer.parseInt(phoneAsso));
               
                boolean isSignUpSuccessful = as.Signup(a);
                
                
                 if (isSignUpSuccessful) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sign up successful");
                    alert.setHeaderText("Your account has been created");
                    alert.showAndWait();
        
                    clearFields();
                    }

            }
        
   
}

    
}
