
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.stream.FileImageInputStream;



public class UserprofileController implements Initializable {

    AssociationServices associationServices = new AssociationServices();
    Association loggedInAssociation;
    
    @FXML
    private Label nametext;
    @FXML
    private ImageView imageuser;
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField emailfield;
    
    boolean isFirstClick = true;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField passwordfield;
    @FXML
    private TextField repeatpassword;
    @FXML
    private TextField addressfield;
    @FXML
    private TextField phonefield;
    
    public static String imageurl;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageuser.setFitWidth(120);
        imageuser.setFitHeight(120);
        loggedInAssociation = associationServices.getLoggedInAssociation();
        nametext.setText(loggedInAssociation.getAssocName());
        imageurl=loggedInAssociation.getImage();
        System.out.println(loggedInAssociation.getImage());
        try {
       String imagePath = loggedInAssociation.getImage();
        File file = new File(imagePath);
         url = file.toURI().toURL();
        Image image = new Image(url.toString());
        imageuser.setImage(image);

        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
        }

      
        imageuser.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageuser.setEffect(new DropShadow());
            }
        });

        imageuser.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageuser.setEffect(null);
            }
        });
        imageuser.setOnMouseClicked(event -> {
            // Handle the click event
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                System.out.println(selectedFile.toURI().toString());
                imageurl = selectedFile.toURI().toString();
                imageuser.setImage(newImage);
                imageuser.setPreserveRatio(true);
                imageuser.setSmooth(true);
                imageuser.setFitWidth(120); 
                imageuser.setFitHeight(120 * newImage.getHeight() / newImage.getWidth());
                Rectangle clip = new Rectangle(imageuser.getFitWidth(), imageuser.getFitHeight());
                clip.setArcWidth(50);
                clip.setArcHeight(50);
                imageuser.setClip(clip);
            }
        });
        
        usernamefield.setPromptText(loggedInAssociation.getUsername());
        
        usernamefield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && usernamefield.getText().isEmpty()) {
                usernamefield.setPromptText(usernamefield.getPromptText());
            } 
        });
        
        emailfield.setPromptText(loggedInAssociation.getEmail());
        
        emailfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && emailfield.getText().isEmpty()) {
                emailfield.setPromptText(emailfield.getPromptText());
              
            } 
        });
        
        addressfield.setPromptText(loggedInAssociation.getLocation());
        
        addressfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && addressfield.getText().isEmpty()) {
                addressfield.setPromptText(addressfield.getPromptText());
               
            } 
        });
        
         phonefield.setPromptText(String.valueOf(loggedInAssociation.getNumber()));
        
        phonefield.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && phonefield.getText().isEmpty()) {
                phonefield.setPromptText(phonefield.getPromptText());
               
            } 
        });
        

        passwordfield.focusedProperty().addListener((observable, oldValue, newValue) -> {
             repeatpassword.setPromptText("Please confirm your password");
            if (oldValue && passwordfield.getText().isEmpty()) {
                passwordfield.setPromptText(passwordfield.getPromptText()); 
                repeatpassword.setPromptText("");
            } 
        });
   
    }    

    @FXML
    private void updateassociation(ActionEvent event) {
        Association a = new Association();
       
        a.setId(loggedInAssociation.getId());
        a.setUsername(usernamefield.getText());
        a.setEmail(emailfield.getText());
        if (imageurl.startsWith("file:/")) {
            String img = imageurl.substring("file:/".length());
             a.setImage(img);
        }else{
            a.setImage(imageurl);
        }
       
         

        System.out.println(a);
        associationServices.update(a);
        
    }
    
}
