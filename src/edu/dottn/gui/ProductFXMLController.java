/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.entities.User;
import edu.dottn.services.ProductServices;
import edu.dottn.services.SubCategoryServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class ProductFXMLController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label value;
    @FXML
    private Button btnoffer;
    @FXML
    private Label category;
    @FXML
    private Label subcategory;
    @FXML
    private ImageView image;
    User user = new User();
    CreationOfferFXMLController o = new CreationOfferFXMLController();
    Product p1 = new Product();
    ProductServices ps= new ProductServices();
    @FXML
    private ComboBox<String> translate;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnoffer.setText("make offer");
        btnoffer.setVisible(false);
        translate.getItems().addAll("ar","fr","en");
        // TODO
    }

    void setproduct(Product p) {

        p1 = new Product(p.getId(), p.getName(), p.getDescription(), p.getImage(), p.getPrice(), p.getSubCategory().getId(), user.getIdUser());

        title.setText(p.getName());
        description.setText(p.getDescription());
        description.setWrapText(true);
        value.setText(Float.toString(p.getPrice()));
        category.setText(p.getSubCategory().getCategory().getName());
        subcategory.setText(p.getSubCategory().getName());
        Image image1 = new Image("file:src/assets/" + p.getImage());
        image.setImage(image1);
         translate.setOnAction(e -> {
                try {
                    System.out.println("hello");
                    if (translatedescription(description.getText(),translate.getValue())!=null){
                    description.setText(translatedescription(description.getText(),translate.getValue()));}
                    
                        

                } catch (IOException | JSONException ex) {
                    System.out.println(ex.getMessage());
                }
            });

    }

    public void setInformation(User u) {
        user = u;
    }

    @FXML
    private void makeoffer(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("CreationOfferFXML.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreationOfferFXMLController o = loader.getController();
        o.setproductinfo(p1);
        title.getScene().setRoot(root);
    }

    void setvisibility(Boolean b) {
        btnoffer.setVisible(b);
        
    }
     private String translatedescription(String description,String l) throws IOException, JSONException {
        
        return ps.translate(description,l);
    }
    

}
