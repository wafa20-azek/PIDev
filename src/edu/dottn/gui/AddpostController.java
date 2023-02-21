/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Post;
import edu.dottn.services.ServicePost;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rajhi
 */
public class AddpostController implements Initializable {

    @FXML
    private Button homebtn;
    @FXML
    private Label logouttext;
    @FXML
    private ImageView backInventory;
    @FXML
    private Button uploadbtn;
    @FXML
    private TextField titlepost;
    @FXML
    private TextArea descpost;
    
    ServicePost ps = new ServicePost();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backToInventory(MouseEvent event) {
        
    }

    @FXML
    private void addpost(ActionEvent event) {
        Post p = new Post(titlepost.getText(),descpost.getText());
        ps.ajouter(p);
    }
    
}
