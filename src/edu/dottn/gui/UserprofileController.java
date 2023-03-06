
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.imageAnalyseAPI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    imageAnalyseAPI imageAnalyse = new imageAnalyseAPI();
    
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
    
    boolean checked;
    @FXML
    private ImageView uploadgif;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dialog<Void> progressDlg = new Dialog<>();
        
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
           FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                imageurl = selectedFile.toURI().toString();
                String urlcheck = imageurl.substring("file:/".length());
                Image icon = new Image(getClass().getResourceAsStream("/gifloading.gif")) {};
                        
                uploadgif.setImage(icon);
               
                uploadgif.setVisible(true);
                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return imageAnalyse.imageAnalyse(urlcheck);
                    }

                    @Override
                    protected void succeeded() {
                        Boolean b = getValue();
                        if (b) {
                            Image newImage = new Image(selectedFile.toURI().toString());
                            imageuser.setImage(newImage);
                         Image icon = new Image(getClass().getResourceAsStream("/verif.png")) {};
                        
                        uploadgif.setImage(icon);
                        uploadgif.opacityProperty().set(1.0);
                            
                        } else {
                            Image icon = new Image(getClass().getResourceAsStream("/errorimg.png")) {};
                        
                        uploadgif.setImage(icon);
                        uploadgif.opacityProperty().set(1.0);
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error uploading new picture");
                            alert.setHeaderText("Invalid picture : The image contains inappropriate content !");
                            alert.showAndWait();
                        }
                      
                    }

                    @Override
                    protected void failed() {
                        uploadgif.setVisible(false);
                        Throwable ex = getException();
                        ex.printStackTrace();
                        // handle exception
                    }
                };
                new Thread(task).start();
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
