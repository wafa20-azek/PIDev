/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class ResetPasswordAssociationController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private Button signinbtn;
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       username.focusTraversableProperty().set(false);
       
       }    

    @FXML
    private void backToLogin(MouseEvent event) {
        try {
                    Stage stage = (Stage) signinbtn.getScene().getWindow();
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

    @FXML
    private void resetPassword(ActionEvent event) throws MessagingException {
        String email = username.getText();
        if(!isValidEmail(email)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Email Format");
            alert.setContentText("Please check the entered email!");
            alert.showAndWait();
            
        }
        else {
            sendResetToken(email, generateResetToken());
        }
    }
    
     private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
     
     public static String generateResetToken() {
        byte[] bytes = new byte[32];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
     
     public static void sendResetToken(String recipient, String resetToken) throws MessagingException {
        String sender = "saif.mess@hotmail.com"; // Replace with your own email address
        String subject = "Troctn | Password Reset Request";
        String body = "Your password reset token is:  " + resetToken;

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.office365.com");
properties.setProperty("mail.smtp.port", "587");
properties.setProperty("mail.smtp.auth", "true");
properties.setProperty("mail.smtp.starttls.enable", "true"); 


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, "saifsaif002"); // Replace with your own email password
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }


    
}
