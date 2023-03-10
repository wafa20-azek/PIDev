
package edu.dottn.gui;

import edu.dottn.entities.Donation;
import edu.dottn.entities.Post;
import edu.dottn.entities.Product;
import edu.dottn.services.MemberServices;
import edu.dottn.services.ProductServices;
import edu.dottn.services.ServiceDonation;
import edu.dottn.util.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javax.mail.MessagingException;


public class DonateController implements Initializable {

   //// @FXML
   ///private FlowPane productArea;
    @FXML
    private Button donateBtn;
    @FXML
    private TilePane tilePane;
    
    ProductServices ps = new ProductServices();
    MemberServices ms = new MemberServices();
    ServiceDonation ds = new ServiceDonation();
    
    public static Post p ;
    UserSession user = new UserSession();
    
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        UserSession us = new UserSession();
        
        List<Product> product=ps.getByIdUser(us.getUser().getIdUser());
           // System.out.println(product);
            setPost(p);
       
     
        for (Product item:product){
           try {
               File file = new File(item.getImage());
               URL url1 = file.toURI().toURL();
               Image image = new Image(url1.toString());
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("productfordonation.fxml"));
               Node postNode = loader.load();
               
               ProductfordonationController productController = loader.getController();
               productController.setCard(item);
               //   postController.setData(item.getTitlePost());
               
               AnchorPane feed1 = new AnchorPane(postNode);
               
               postNode.getStyleClass().add("productdonation");
               AnchorPane.setBottomAnchor(feed1, 10.0);
               tilePane.getChildren().add(feed1);
              // productArea.getChildren().add(tilePane);
           } catch (IOException ex) {
           }
         
       }
        System.out.println(p);       
      
    }
    public void setPost(Post pp){
     p=pp; 
    }

    @FXML
    private void donate(ActionEvent event) throws IOException, MessagingException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("productfordonation.fxml"));
               Node postNode = loader.load();
               ProductfordonationController productController = loader.getController();
              Product prod= productController.getProduct();
              Donation d = new Donation(ms.getOneById(user.getUser().getIdUser()), prod,p);
              System.out.println(d);
              ds.ajouter(d);
              ps.updatestatus(prod);
              ds.envoyer(user.getUser());
            
    }
    
}
