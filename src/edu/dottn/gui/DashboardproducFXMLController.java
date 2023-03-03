/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Category;
import edu.dottn.entities.Product;
import edu.dottn.entities.SubCategory;
import edu.dottn.services.CategoryServices;
import edu.dottn.services.ProductServices;
import edu.dottn.services.SubCategoryServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class DashboardproducFXMLController implements Initializable {

    @FXML
    private AnchorPane feed;
    ProductServices ps = new ProductServices();
     CategoryServices cs = new CategoryServices();
    SubCategoryServices scs = new SubCategoryServices();

 List<Product> l = new ArrayList<>();
    @FXML
    private TextField tfserchbyname;
    @FXML
    private ComboBox<String> subcategory;
    @FXML
    private ComboBox<String> category;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        l=ps.getAll();
        loadproducts(l);
        initialzeCategory();
        
     
    }    
     private void initialiazeSubCategory(String categoryname) {
        List<String> l2 = new ArrayList<>();
        subcategory.setItems(FXCollections.observableArrayList(l2));

        int id = cs.getByName(categoryname).get(0).getId();
        List<SubCategory> l1 = scs.getAllByIdCategory(id);

        for (int i = 0; i < l1.size(); i++) {

            l2.add(l1.get(i).getName());

        }

        subcategory.setItems(FXCollections.observableArrayList(l2));
    }
    
     public void loadproducts(List<Product> l){
     
            feed.getChildren().clear();
        float x = 20, y = 20;
        int k=0;
       
        for (int i = 0; i < l.size(); i++) {
           
            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:src/assets/" + l.get(i).getImage(), 100, 100, false, false);
            ImageView iv = new ImageView(image);
            
            Label title = new Label(l.get(i).getName());
            Label Description = new Label(l.get(i).getDescription());
            String s = String.valueOf(l.get(i).getPrice());
            Label value = new Label(s);
            anchorpane.setLayoutY(y);
            
            iv.setLayoutX(x);
            
            title.setLayoutX(x + 110);

            value.setLayoutX(x + 140);
            Description.setLayoutX(x + 260);
            
            
//            Product p=l.get(i);
//             anchorpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    product(p);
//                }
//            });
           
           
            anchorpane.getChildren().addAll(iv, title,value, Description);
           
           feed.getChildren().addAll(anchorpane);
            y += 200;
          
        }        


    }
      @FXML
      private void searchbyname(ActionEvent event) {
        System.out.println(tfserchbyname.getText());
        l.clear();
        l = ps.getByName(tfserchbyname.getText());
        loadproducts(l);
    }

    @FXML
    private void searchbycategory(ActionEvent event) {
        initialiazeSubCategory(category.getValue());
        l.clear();
        l = ps.getByCategory(category.getValue());
        loadproducts(l);
    }

    @FXML
    private void searchbysubcategory(ActionEvent event) {
        l.clear();
        l = ps.getBySubCategory(subcategory.getValue());
        loadproducts(l);
    }

    public void initialzeCategory() {

        List<Category> l1 = cs.getAll();
        List<String> l2 = new ArrayList<>();

        for (int i = 0; i < l1.size(); i++) {

            l2.add(l1.get(i).getName());

        }

        category.setItems(FXCollections.observableArrayList(l2));
        category.setPromptText("Category");
        List<SubCategory> l3 = scs.getAll();
        List<String> l4 = new ArrayList<>();

        for (int i = 0; i < l3.size(); i++) {

            l4.add(l3.get(i).getName());

        }
        subcategory.setItems(FXCollections.observableArrayList(l4));
       
     }

   
  
}
