package edu.dottn.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ProductfordonationController implements Initializable {

    @FXML
    private ImageView productImage;
    @FXML
    private Label labelproduct;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setCard(String tst, String path) throws MalformedURLException{
        File file = new File(path);
    URL url = file.toURI().toURL();
    Image image = new Image(url.toString());
   productImage.setImage(image);
       labelproduct.setText(tst);
    }
    
}
