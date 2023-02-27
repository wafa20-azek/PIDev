/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author rajhi
 */
public class FormPostController implements Initializable {

    @FXML
    private TextArea contentTextArea;
    @FXML
    private TextArea hashtagsTextArea;
    @FXML
    private TextArea categoriesTextArea;
    @FXML
    private Button addImageButton;
    @FXML
    private Button addVideoButton;
    @FXML
    private Button publishButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
