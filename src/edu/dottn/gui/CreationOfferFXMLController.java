/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Member;
import edu.dottn.entities.Offre;
import edu.dottn.entities.Product;
import edu.dottn.entities.User;
import edu.dottn.services.ProductServices;
import edu.dottn.services.ServiceOffre;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.transaction.TransactionRequiredException;

/**
 * FXML Controller class
 *
 * @author bochr
 */
public class CreationOfferFXMLController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Button btnsentrequest;
    @FXML
    private AnchorPane feed1;
private User user= new Member();
//private Product product=new Product();
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label value;
    @FXML
    private Text name;
    @FXML
    private AnchorPane list;
    ProductServices ps=new ProductServices();
    Product p1=new Product();
    Product p2=new Product();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          float x = 20, y = 20;
        int k=0;
        List<Product> l = new ArrayList<>();
       l=ps.getByIdUser(user.getIdUser());
        for (int i = 0; i < l.size(); i++) {
           
            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:src/assets/" + l.get(i).getImage(), 50, 50, false, false);
            ImageView iv = new ImageView(image);
            
            Label title = new Label(l.get(i).getName());
          
            anchorpane.setLayoutY(y);
            
            iv.setLayoutX(x);
            
            title.setLayoutX(x + 70);         
            Product p=l.get(i);
             anchorpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    p1=p;
                }
            });
           
           
            anchorpane.getChildren().addAll(iv, title);
           
           list.getChildren().addAll(anchorpane);
            y += 100;
          
        } 
       
    }
     void setInformation(User u) {
         user=u;
     }
       void setproductinfo(Product p) {
           p2=p;
        Image image1 = new Image("file:src/assets/" +p.getImage());
        image.setImage(image1);
        title.setText(p.getName());
        description.setText(p.getDescription());
        value.setText(Float.toString(p.getPrice()));
       }

    @FXML
    private void sentRequest(ActionEvent event) {
       
        Offre o = new Offre(p1,user,p2,ps.getById(p2.getId()).getUser());
        ServiceOffre so = new ServiceOffre();
        if (so.verifierOffre(o) == null) {
                so.ajouterOffre(o);
                btnsentrequest.setText("Offer Sent");
                btnsentrequest.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Offer already exists");
                alert.setContentText("The offer you are trying to add already exists.");

                alert.showAndWait();
            }
            
          
      
       

    }

    @FXML
    private void backtomyoffer(ActionEvent event) {
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnback.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    
    }

  
}
