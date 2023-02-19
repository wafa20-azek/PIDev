/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nameId;
    @FXML
    private PasswordField passwordId;
    @FXML
    private TextField addressId;
    @FXML
    private TextField emailId;
    @FXML
    private PasswordField repeatpassId;
    @FXML
    private TextField numberId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSignUp(ActionEvent event) {
         try {
             
//            if(!(nameId.getText().isEmpty() && addressId.getText().isEmpty() && emailId.getText().isEmpty() && numberId.getText().isEmpty() && passwordId.getText().isEmpty() && repeatpassId.getText().isEmpty())){
//              if(!nameId.getText().isEmpty()){
//               if(!addressId.getText().isEmpty()){
//                if(!emailId.getText().isEmpty()){
//                 if(!numberId.getText().isEmpty()){
//                  if(!passwordId.getText().isEmpty()){
//                   if(!repeatpassId.getText().isEmpty()){
//                    if(passwordId.getText().equals(repeatpassId.getText())){
//                      int numberid = Integer.parseInt(numberId.getText());
//                      User p1 = new Member(nameId.getText(), addressId.getText(), emailId.getText(), passwordId.getText(), numberid, 0);
//                      MemberServices m1 = new MemberServices();
//                      User p2 = m1.ajouterUser(p1);
//                       if (p2 != null){
////                       Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.FINISH);
////                       a.showAndWait();
//                         NavigationController.changeHomePage(event, p2, "HomePage.fxml");
//            }  
//            }   
//            }   
//            }  
//            }    
//            }
//            } 
//            }
//            } 
//            if(nameId.getText().isEmpty() && addressId.getText().isEmpty() && emailId.getText().isEmpty() && numberId.getText().isEmpty() && passwordId.getText().isEmpty() && repeatpassId.getText().isEmpty() ){
//                Alert field = new Alert(Alert.AlertType.ERROR, "ALL Field is empty", ButtonType.CLOSE);
//                field.showAndWait();
//            }
            if(nameId.getText().isEmpty()){
                nameId.setStyle("-fx-border-color: red;");
                Alert name = new Alert(Alert.AlertType.ERROR, "name Field is empty", ButtonType.CLOSE);
                name.showAndWait();
            }
            else{nameId.setStyle("-fx-border-color: #c0c0c0;");}
            if(addressId.getText().isEmpty()){
                addressId.setStyle("-fx-border-color: red;");
                Alert address = new Alert(Alert.AlertType.ERROR, "address Field is empty", ButtonType.CLOSE);
                address.showAndWait();
            }
            else{addressId.setStyle("-fx-border-color: #c0c0c0;");}
            if(emailId.getText().isEmpty()){
                emailId.setStyle("-fx-border-color: red;");
                Alert email = new Alert(Alert.AlertType.ERROR, "email Field is empty", ButtonType.CLOSE);
                email.showAndWait();}
            else{emailId.setStyle("-fx-border-color: #c0c0c0;");}
            if(numberId.getText().isEmpty()){
                numberId.setStyle("-fx-border-color: red;");
                Alert number = new Alert(Alert.AlertType.ERROR, "number Field is empty", ButtonType.CLOSE);
                number.showAndWait();}
            else{numberId.setStyle("-fx-border-color: #c0c0c0;");}
            if(passwordId.getText().isEmpty()){
                passwordId.setStyle("-fx-border-color: red;");
                Alert password = new Alert(Alert.AlertType.ERROR, "password Field is empty", ButtonType.CLOSE);
                password.showAndWait();}
            else{passwordId.setStyle("-fx-border-color: #c0c0c0;");}
            if(repeatpassId.getText().isEmpty()){
                repeatpassId.setStyle("-fx-border-color: red;");
                Alert repeatpass = new Alert(Alert.AlertType.ERROR, "repeatpassword Field is empty", ButtonType.CLOSE);
                repeatpass.showAndWait();}
            else{repeatpassId.setStyle("-fx-border-color: #c0c0c0;");}
            
            
            if(!(nameId.getText().isEmpty() || addressId.getText().isEmpty() || emailId.getText().isEmpty() || numberId.getText().isEmpty() || passwordId.getText().isEmpty() || repeatpassId.getText().isEmpty())){
                if(!passwordId.getText().equals(repeatpassId.getText())){
                passwordId.setStyle("-fx-border-color: red;");
                repeatpassId.setStyle("-fx-border-color: red;");
                Alert repeatpass = new Alert(Alert.AlertType.ERROR, "verif your password", ButtonType.CLOSE);
                repeatpass.showAndWait();}
                else if(!emailId.getText().contains("@")){
                   emailId.setStyle("-fx-border-color: red;");
                   Alert email = new Alert(Alert.AlertType.ERROR, "email doesn't contain @ ", ButtonType.CLOSE);
                      email.showAndWait();
                }
                else{
                passwordId.setStyle("-fx-border-color: #c0c0c0;");
                repeatpassId.setStyle("-fx-border-color: #c0c0c0;");} 
               int numberid = Integer.parseInt(numberId.getText());
               User p1 = new Member(nameId.getText(), addressId.getText(), emailId.getText(), passwordId.getText(), numberid, 0);
               MemberServices m1 = new MemberServices();
               User p2 = m1.ajouterUser(p1);
               if (p2 != null){
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Personne added !", ButtonType.FINISH);
//                a.showAndWait();
                  NavigationController.changeHomePage(event, p2, "HomePage.fxml");
                  }
               else{
                   emailId.setStyle("-fx-border-color: red;");
                   Alert email = new Alert(Alert.AlertType.ERROR, "email exist ", ButtonType.CLOSE);
                      email.showAndWait(); 
               }
            }        
        } catch (NumberFormatException e) {
            Alert b = new Alert(Alert.AlertType.ERROR, "Enter only number in field", ButtonType.CLOSE);
            b.showAndWait();
        }
    }
    
}
