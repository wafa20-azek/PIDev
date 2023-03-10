package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Comment;
import edu.dottn.entities.Post;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServiceComment;
import edu.dottn.services.ServicePost;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
    @FXML
    private ImageView deletepostbtn;
    
    AssociationServices as = new AssociationServices();
    @FXML
    private ImageView updatepostbtn;
   
  
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
        Association a = as.getLoggedInAssociation();
        System.out.println(p.getAssociation().getId());
        System.out.println(p.getAssociation().getId());
        if (a.getId()!=p.getAssociation().getId()){
            deletepostbtn.setVisible(false);
            deletepostbtn.disableProperty();
            updatepostbtn.setVisible(false);
            deletepostbtn.disableProperty();
        }
        
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
        Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Delete Post");
    alert.setContentText("Are you sure you want to delete this post?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
       
        sp.supprimerParId(idPost);
        backToFeed();
    }
        
    }

    @FXML
    private void updatepost(MouseEvent event) {
        
      
    // Créer une boîte de dialogue pour modifier le post
    Dialog<Post> dialog = new Dialog<>();
    dialog.setTitle("Modifier le post");

    // Créer les boutons "Enregistrer" et "Annuler"
    ButtonType enregistrerButtonType = new ButtonType("Enregistrer", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(enregistrerButtonType, ButtonType.CANCEL);

    // Créer les champs pour éditer les informations du post
    // Remplacez les labels et les champs par les informations de votre post
    Label titleLabel = new Label("Titre :");
    TextField titleField = new TextField(post.getTitlePost());
    Label contentLabel = new Label("Contenu :");
    TextArea contentField = new TextArea(post.getDescription());
   // ImageView imageView = new ImageView(post.getPhotos()); // Display the current image
    Button chooseImageButton = new Button("Choisir une image");

    VBox vbox = new VBox(10, titleLabel, titleField, contentLabel, contentField, chooseImageButton);
    dialog.getDialogPane().setContent(vbox);

    // Configure the button to open a file chooser to select the image
//    chooseImageButton.setOnAction(e -> {
//        FileChooser fileChooser = new FileChooser();
//        File selectedFile = fileChooser.showOpenDialog(dialog.getOwner());
//
//        if (selectedFile != null) {
//            // Update the image view with the selected image
//            Image selectedImage = new Image(selectedFile.toURI().toString());
//            imageView.setImage(selectedImage);
//
//            // Update the post object with the new image
//            post.setPhotos(selectedImage.toString());
//        }
//    });

    // Attendre que l'utilisateur appuie sur un bouton
    dialog.setResultConverter((ButtonType dialogButton) -> {
        if (dialogButton == enregistrerButtonType) {
            // Créer un nouveau post avec les nouvelles informations
            Post modifiedPost = new Post(idPost,as.getLoggedInAssociation(),titleField.getText(), contentField.getText(), "test");
           // modifiedPost.setIdPost(idPost);
            // Appeler la méthode de ServicePost pour modifier le post
            ServicePost sp = new ServicePost();
            sp.modifier(modifiedPost);

            // Retourner le post modifié pour affichage ultérieur
          
            backToFeed();
              return modifiedPost;
        }
        return null;
    });

    // Afficher la boîte de dialogue et attendre que l'utilisateur appuie sur un bouton
    Optional<Post> result = dialog.showAndWait();
    result.ifPresent(modifiedPost -> {
        // Afficher le post modifié dans l'interface utilisateur
        // Remplacez les labels et les champs par les informations de votre post
        
        post.setTitlePost(modifiedPost.getTitlePost());
        post.setDescription(modifiedPost.getDescription());
        post.setPhotos(modifiedPost.getPhotos());
        System.out.println(post.getIdPost());
        titleLabel.setText("Titre : " + post.getTitlePost());
        contentLabel.setText("Contenu : " + post.getDescription());
       // imageView.setImage(new Image(post.getPhotos()));
    });
}
        
   
}
