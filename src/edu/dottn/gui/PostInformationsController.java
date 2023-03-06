package edu.dottn.gui;

import edu.dottn.entities.Comment;
import edu.dottn.entities.Post;
import edu.dottn.services.ServiceComment;
import edu.dottn.services.ServicePost;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class PostInformationsController implements Initializable {
    
    

    @FXML
    private ImageView postImage;
    @FXML
    private Label postTitle;
    @FXML
    private Text postDescription;
    @FXML
    private ImageView like;
    @FXML
    private ImageView dislike;
    @FXML
    private TextArea postComment;
   
    @FXML
    private ImageView backToFeed;
    private int i =0;
    @FXML
    private ImageView likeActive;
    @FXML
    private ImageView dislkeActive;
    
    private static Post post;
    
    private static int idPost;
    
    private List<Comment> comments;
    
    ServiceComment sc = new ServiceComment();
    @FXML
    private AnchorPane anchorComment;
    @FXML
    private VBox commentArea;
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dislkeActive.setCursor(Cursor.HAND);
        likeActive.setCursor(Cursor.HAND);
        dislike.setCursor(Cursor.HAND);
        like.setCursor(Cursor.HAND);
        backToFeed.setCursor(Cursor.HAND);
        
        like.setOnMouseClicked(e->{
            if(dislkeActive.isVisible()){
                dislike.setVisible(true);
                dislkeActive.setVisible(false);
            }
            likeActive.setVisible(true);    
            like.setVisible(false);
            
        });
        likeActive.setOnMouseClicked(e->{
           
         like.setVisible(true);
         likeActive.setVisible(false);
        });

        like.setOnMouseEntered(e->{
            like.setCursor(Cursor.HAND);
            like.setScaleX(1.1);
            like.setScaleY(1.1);
            
        });
         like.setOnMouseExited(e->{
            like.setScaleX(1);
            like.setScaleY(1);
            
        });
         
         dislike.setOnMouseClicked(e->{
            if(likeActive.isVisible()){
                likeActive.setVisible(false);
                like.setVisible(true);
            }
            dislkeActive.setVisible(true);
            dislike.setVisible(false);
        });
        dislkeActive.setOnMouseClicked(e->{
           
         dislike.setVisible(true);
         dislkeActive.setVisible(false);
        });

        dislike.setOnMouseEntered(e->{
            
            dislike.setScaleX(1.1);
            dislike.setScaleY(1.1);
            
        });
         dislike.setOnMouseExited(e->{
            dislike.setScaleX(1);
            dislike.setScaleY(1);
            
        });
         
        
         
         postComment.setOnKeyPressed(event->{
             if (event.getCode() == KeyCode.ENTER) {
                 Comment c = new Comment(post,postComment.getText(),new Timestamp(System.currentTimeMillis()));
                 sc.ajouterComment(c, post.getIdPost());
                
                 postComment.clear();
                 commentArea.getChildren().clear();
                 displayComment(comments);
             }
        });
         
        
        // comments=;
       
         //System.out.println(sc.getCommentsByPostId(post.getIdPost()));
         //displayComment();
         
    }    

    private void donate() throws IOException {
             Stage stage = new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("donate.fxml"));
                   // Image icon = new Image(getClass().getResourceAsStream("/icon.png")) {};
                    Scene scene = new Scene(root, 643, 750);
                    stage.setScene(scene);
                    stage.setTitle("Troctn Desktop App ");
                     scene.getStylesheets().add("styles.css");
                    stage.setResizable(false);
                    stage.showAndWait();
                 //   stage.show();
             
    }

    @FXML
    private void backToFeed() {
        try {
                    Stage stage = (Stage) backToFeed.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
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
    
    public void setPostInformations(String title , String description , Image img ,Post p){
        postTitle.setText(title);
        postDescription.setText(description);
        postImage.setImage(img);
        post=p;
        
    }
    public void setIdPost(Post item){
        idPost=item.getIdPost();
        comments=sc.getCommentsByPostId(idPost);
        displayComment(comments);
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

    @FXML
    private void delete(MouseEvent event) {
        ServicePost sp = new ServicePost();
        sp.supprimerParId(idPost);
        backToFeed( );
    }
    
    
    
}
