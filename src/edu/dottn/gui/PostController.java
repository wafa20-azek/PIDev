package edu.dottn.gui;

import edu.dottn.entities.Post;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
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
    //@FXML
   //private Label assocpost;
    
   // @FXML
   // private ImageView associmgpost;
    
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
    
public void setData(String titlePost, String description, Timestamp date_created ,String imagepath) throws MalformedURLException {
    File file = new File(imagepath);
    URL url = file.toURI().toURL();
    Image image = new Image(url.toString());
    SimpleDateFormat date = new SimpleDateFormat("ddMMyyyy");
    String dateString = date.format(date_created.getTime());
    int day = Integer.parseInt(dateString.substring(0, 2));
    int month = Integer.parseInt(dateString.substring(2, 4));
    int year = Integer.parseInt(dateString.substring(4, 8));
    String dateStr = String.format("%02d/%02d/%04d", day, month, year);
    // Set the data for the post
    Label titleLabel = (Label) root.lookup("#titlepost");
    Label descriptionLabel = (Label) root.lookup("#detailslabel");
    Text datepost = (Text) root.lookup("#datepost");
    ImageView imgpost = (ImageView) root.lookup("#imgpost");

    titleLabel.setText(titlePost);
    descriptionLabel.setText(description);
    datepost.setText(dateStr);
    imgpost.setImage(image);
}


}
