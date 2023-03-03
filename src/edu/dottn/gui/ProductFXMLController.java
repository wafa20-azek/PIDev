/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Product;
import edu.dottn.entities.User;
import edu.dottn.services.SubCategoryServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setproduct(Product p) {
        p = new Product(p.getId(), p.getName(), p.getDescription(), p.getImage(), p.getPrice(), p.getSubCategory().getId(), user.getIdUser());

        title.setText(p.getName());
        description.setText(p.getDescription());
        value.setText(Float.toString(p.getPrice()));
        category.setText(p.getSubCategory().getCategory().getName());
        subcategory.setText(p.getSubCategory().getName());
         Image image1 = new Image("file:src/assets/" + p.getImage());
         image.setImage(image1);
    }
     public void setInformation(User u) {
         user = u;
    }


}
