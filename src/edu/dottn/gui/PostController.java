package edu.dottn.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class PostController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label titlepost;
    @FXML
    private Text datepost;
    @FXML
    private Label assocpost;
    
    @FXML
    private ImageView associmgpost;
    
    @FXML
    private ImageView imgpost;
    @FXML
    private Label detailslabel;
    
     public AnchorPane getRoot() {
        return root;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }  
    
     public void setData(String item) {
    // Set the data for the post
    Label titleLabel = (Label) root.lookup("#titlepost");
    
    titleLabel.setText(item);
   
}
    
}
