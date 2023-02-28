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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class FeedproductFXMLController implements Initializable {

    @FXML
    private Button addproduct;
    @FXML
    private AnchorPane feed;
    @FXML
    private AnchorPane filter;

    private ProductServices ps = new ProductServices();
    CategoryServices cs = new CategoryServices();
    SubCategoryServices scs = new SubCategoryServices();
    @FXML
    private Button btnlistproduct;

    @FXML
    private TextField tfserchbyname;

    List<Product> l = new ArrayList<>();
    ComboBox<String> category = new ComboBox<>();

    ComboBox<String> subcategory = new ComboBox<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        l = ps.getAll();
        loadproducts(l);

    }

    @FXML
    private void initialiateSubCategory(String categoryname) {
        List<String> l2 = new ArrayList<>();
        subcategory.setItems(FXCollections.observableArrayList(l2));

        int id = cs.getByName(categoryname).get(0).getId();
        List<SubCategory> l1 = scs.getAllByIdCategory(id);

        for (int i = 0; i < l1.size(); i++) {

            l2.add(l1.get(i).getName());

        }

        subcategory.setItems(FXCollections.observableArrayList(l2));
    }

    @FXML
    private void gotoaddproduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/addproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addproduct.getScene().setRoot(root);

    }

    @FXML
    private void listproducts(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/listproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addproduct.getScene().setRoot(root);
    }

    @FXML
    private void searchbyname(ActionEvent event) {
        l.clear();
        l = ps.getByName(tfserchbyname.getText());
        loadproducts(l);
    }

    @FXML
    private void searchbycategory(ActionEvent event) {
        initialiateSubCategory(category.getValue());
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

    public void loadproducts(List<Product> l) {
        feed.getChildren().clear();
        float x = 20, y = 20;
        for (int i = 0; i < l.size(); i++) {
            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:src/assets/" + l.get(i).getImage(), 200, 200, false, false);
            ImageView iv = new ImageView(image);

            Label title = new Label(l.get(i).getName());
            Label Description = new Label(l.get(i).getDescription());
            Label Translate = new Label("translate");
            String s = String.valueOf(l.get(i).getPrice());
            Label value = new Label(s);
            anchorpane.setLayoutX(x);
            iv.setLayoutY(y);
            title.setLayoutY(y + 210);

            value.setLayoutY(y + 240);
            Description.setLayoutY(y + 260);
            Translate.setLayoutY(y + 280);
            String t=l.get(i).getDescription();
            
            Translate.setOnMouseClicked((MouseEvent MouseEvent) -> {
                try {
                    System.out.println("hello");
                    if (translatedescription(t)!=null){
                    Description.setText(translatedescription(t));}
                    
                        

                } catch (IOException | JSONException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            anchorpane.getChildren().addAll(iv, title, Description, value,Translate);
            feed.getChildren().addAll(anchorpane);
            x += 300;

           

        }

    }

    @FXML
    public void showfilters(ActionEvent event) {

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
        subcategory.setLayoutX(300);
        subcategory.setPromptText("Subcategory");
        AnchorPane anchorpane = new AnchorPane();
        anchorpane.getChildren().addAll(category, subcategory);
        filter.getChildren().addAll(anchorpane);
        category.setOnAction(e -> searchbycategory(event));
        subcategory.setOnAction(e -> searchbysubcategory(event));
    }

    private String translatedescription(String description) throws IOException, JSONException {
        
        return ps.translate(description);
    }

}
