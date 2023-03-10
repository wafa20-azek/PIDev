package edu.dottn.gui;

import edu.dottn.entities.Product;
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
import javafx.scene.layout.AnchorPane;


public class ProductfordonationController implements Initializable {

    @FXML
    private ImageView productImage;
    @FXML
    private Label labelproduct;
    
    @FXML
    private AnchorPane anchorproduct;
     private static AnchorPane seletcAnchorPane;
 public static Product selectedProduct;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            anchorproduct.setOnMouseClicked(event -> {
          if (seletcAnchorPane != null) {
              
               //System.out.println(selectedProduct);
              seletcAnchorPane.setStyle(""); // reset style of previously selected AnchorPane
          }
          
          seletcAnchorPane = anchorproduct; // set the current AnchorPane as the selected one
          anchorproduct.setStyle("-fx-background-color: #BF5CFF;"); // set background color of selected AnchorPane
          
          selectedProduct  = (Product) seletcAnchorPane.getUserData();
      });
        // System.out.println(seletcAnchorPane.getChildren().get(1));
             
    }   
    
    public void setCard(Product p) throws MalformedURLException{
        File file = new File(p.getImage());
    
    Image image = new Image("file:src/assets/"+p.getImage());
    
   productImage.setImage(image);
       labelproduct.setText(p.getName());
      anchorproduct.setUserData(p);
    }
    
    public Product getProduct(){
        return selectedProduct;
    }
       
}
