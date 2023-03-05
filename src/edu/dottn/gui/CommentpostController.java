/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Comment;
import edu.dottn.services.ServiceComment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rajhi
 */
public class CommentpostController implements Initializable {

    @FXML
    private Button homebtn;
    @FXML
    private Label logouttext;
    @FXML
    private ImageView backToPost;
    @FXML
    private Label postTitle;
    @FXML
    private TextArea commentText;
    @FXML
    private Button postCommentBtn;
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView backToInventory;
    ServiceComment sc = new ServiceComment();

    /**
     * Initializes the controller class.
     */
    private Button myButton;
    @FXML
    private TableView<?> commentsTable;
    @FXML
    private TableColumn<?, ?> authorColumn;
    @FXML
    private TableColumn<?, ?> commentColumn;

    public void initialize() {
        myButton.setCursor(Cursor.HAND);
    }

    @FXML
    private void backToPost(MouseEvent event) {
    }

    @FXML
    private void postComment(ActionEvent event) {
    }

    @FXML
    private void backToInventory(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
     private void commentPost (ActionEvent event) {
        //Comment c = new Comment();
      //  sc.ajouterComment(c);
    }
    

}
