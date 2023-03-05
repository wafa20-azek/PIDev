
package edu.dottn.gui;

import edu.dottn.entities.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;


public class DonateController implements Initializable {

   //// @FXML
   ///private FlowPane productArea;
    @FXML
    private Button donateBtn;
    @FXML
    private TilePane tilePane;
    
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           List<Product> product=new ArrayList<>();
     //  productArea.setSpacing(15);
    //   productArea.setMaxWidth(300.0);
       Product p1 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p2 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p3 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p4 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p5 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p6 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p7 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p8 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       Product p9 = new Product("TSHIRT","C:\\Users\\rajhi\\OneDrive\\Desktop\\aeropostale.jpg");
       
       // System.out.println(p1.getImage());
       product.add(p1);
      //  System.out.println(p1);
      product.add(p2);
      product.add(p3);
       product.add(p4);
      product.add(p5);
       product.add(p6);
      product.add(p7);
       product.add(p8);
        product.add(p9);
  //  product.add("C:Users/rajhi/OneDrive/Desktop/aeropostale.jpg");
     //   System.out.println(product);
       
     
  for (Product item:product){
           try {
               File file = new File(item.getImage());
               URL url1 = file.toURI().toURL();
               Image image = new Image(url1.toString());
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("productfordonation.fxml"));
               Node postNode = loader.load();
               
               ProductfordonationController productController = loader.getController();
               productController.setCard(item.getName(),item.getImage());
               //   postController.setData(item.getTitlePost());
               
               AnchorPane feed1 = new AnchorPane(postNode);
               
              // postNode.getStyleClass().add("post");
               AnchorPane.setBottomAnchor(feed1, 10.0);
               tilePane.getChildren().add(feed1);
              // productArea.getChildren().add(tilePane);
           } catch (IOException ex) {
           }
       }
    }    
    
}
