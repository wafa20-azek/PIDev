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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class AddproductFXMLController implements Initializable {

    @FXML
    private Button btnaddproduct;
    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfValue;
    @FXML
    private ImageView image;
    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<String> subcategory;
    CategoryServices cs = new CategoryServices();
    SubCategoryServices scs = new SubCategoryServices();
    @FXML
    private Button btnaddimage;
    String imagename;
    private Button btnshowproduct;
    @FXML
    private Label inputcontrol;
    @FXML
    private Button btnclear;
    @FXML
    private Button btnlistproduct;
    @FXML
    private Button btnhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
inputcontrol.setText("");
        List<Category> l = cs.getAll();
        List<String> l1 = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getName());

        }

        category.setItems(FXCollections.observableArrayList(l1));

    }

    @FXML
    private void initialiateSubCategory(ActionEvent event) {

        String name = category.getValue();
        int id = cs.getByName(name).get(0).getId();
        List<SubCategory> l = scs.getAllByIdCategory(id);
        List<String> l1 = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getName());

        }

        subcategory.setItems(FXCollections.observableArrayList(l1));
    }

    @FXML
    private void AddProduct(ActionEvent event) {
       
             if ((tfName.getText() == null || tfName.getText().isEmpty())) {
          inputcontrol.setText("You didn't enter a title!");
        }
             else if ((tfDescription.getText() == null || tfDescription.getText().isEmpty())) {
          inputcontrol.setText("You didn't enter a description!");
        } else 
        if ((tfValue.getText() == null || tfValue.getText().isEmpty())) {
          inputcontrol.setText("You didn't enter a value!");
        }
         else 
        if (imagename == null ) {
          inputcontrol.setText("You didn't enter an image!");
        }
         else 
        if ((category.getValue() == null || category.getValue().isEmpty())) {
          inputcontrol.setText("You didn't choose a category!");
        }    
         else 
        if ((subcategory.getValue() == null || subcategory.getValue().isEmpty())) {
          inputcontrol.setText("You didn't choose a subcategory!");
        }else
        
       
        { 
        List<SubCategory> l = (List<SubCategory>) scs.getByName(subcategory.getValue());
        float value=Float.parseFloat(tfValue.getText());
        
        Product p = new Product(tfName.getText(), tfDescription.getText(), imagename,value , l.get(0),1);
        System.out.println(p);
        ProductServices ps = new ProductServices();
        if (ps.exist(p)==null){
        ps.addProduct(p);

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Product added !", ButtonType.FINISH);
        a.showAndWait();}
        else {inputcontrol.setText("Product already exists!");}
        
        }
    }

    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:/Users/WAFA/Documents/NetBeansProjects/PIDEV/src/assets"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            imagename = selectedFile.getName().toString();
            Image image1 = new Image("file:src/assets/" + imagename);
            image.setImage(image1);

        } else {
            System.out.println("not found");
        };
    }

    private void showproduct(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../gui/deleteproductFXML.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        btnshowproduct.getScene().setRoot(root);

    
    }

    @FXML
    private void clear(ActionEvent event) {
        tfName.clear();
        tfDescription.clear();
        tfValue.clear();
        image.imageProperty().set(null);
        
    }

    @FXML
    private void gotolistproduct(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/listproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnlistproduct.getScene().setRoot(root);
    }

    @FXML
    private void home(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/feedproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnhome.getScene().setRoot(root);
    }


}
