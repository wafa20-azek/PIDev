/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Comment;
import edu.dottn.entities.Post;
import edu.dottn.entities.User;
import edu.dottn.services.MessageServices;
import edu.dottn.services.ServiceComment;
import edu.dottn.util.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class PostInformationsUserController implements Initializable {

    @FXML
    private ImageView postImage;
    @FXML
    private Label postTitle;
    @FXML
    private Text postDescription;
    @FXML
    private ImageView likeActive;
    @FXML
    private ImageView dislkeActive;
    @FXML
    private TextArea postComment;
    @FXML
    private AnchorPane anchorComment;
    @FXML
    private VBox commentArea;
    @FXML
    private Button donatebtn;
    @FXML
    private ImageView backToFeed;
    
    private static int idPost;
    private List<Comment> comments;
    private static Post post;
    ServiceComment sc = new ServiceComment();
    @FXML
    private TextField sendfield;
    
    MessageServices messageServices = new MessageServices();
    UserSession us1 = new UserSession();
    User us = us1.getUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       postComment.setOnKeyPressed(event->{
             if (event.getCode() == KeyCode.ENTER) {
                 Comment c = new Comment(post,postComment.getText(),new Timestamp(System.currentTimeMillis()));
                 sc.ajouterComment(c, post.getIdPost());
                
                 postComment.clear();
                 commentArea.getChildren().clear();
                 displayComment(comments);
             }
        });
       sendfield.setOnKeyPressed(event->{
             if (event.getCode() == KeyCode.ENTER) {
                 
                messageServices.sendUserMessage(String.valueOf(us.getIdUser()), String.valueOf(post.getAssociation().getId()), sendfield.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Thanks for contacting us! reach us from your messages area");
            alert.showAndWait();
            return; 
             }
        });
       
       
    }  
    
    
    public void setIdPost(Post item){
//        Association as = item.getAssociation();
//       // System.out.println(as.getAssocName());
//        System.out.println(item.getAssociation().getId());
//        if (as.getId()!=4){
//            deletepostbtn.setVisible(false);
//            deletepostbtn.disableProperty();
//        }
        idPost=item.getIdPost();
        comments=sc.getCommentsByPostId(idPost);
        displayComment(comments);
    }
    
    public void setPostInformations(String title , String description , Image img ,Post p){
        postTitle.setText(title);
        postDescription.setText(description);
        postImage.setImage(img);
        post=p;
        //Association a = as.getLoggedInAssociation();
        //System.out.println(p.getAssociation().getId());
       // System.out.println(p.getAssociation().getId());
       
        
    }

    @FXML
    private void donatenow(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donate.fxml"));
            //  Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
            Parent root = loader.load();
            DonateController dn = loader.getController();
             dn.setPost(post);
            Scene scene = new Scene(root, 643, 750);
            stage.setScene(scene);
            stage.setTitle("Troctn Desktop App ");
            scene.getStylesheets().add("styles.css");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PostInformationsUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backToFeed(MouseEvent event) {
         try {
                    Stage stage = (Stage) backToFeed.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("feeddonation.fxml"));
                   // Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
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
    
     public void displayComment(List<Comment> comments){
        Stage stage =new Stage();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        
        for (Comment comment : comments){
            String contenu = comment.getContenu();
            Timestamp times = comment.getDateComment();
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String time = timeFormat.format(times.getTime());
            
            Label label = new Label(contenu+"           "+time);
            label.getStyleClass().add("message-bubble");
            HBox com = new HBox(label);
            commentArea.getChildren().add(com);
        }
    }
    
}
