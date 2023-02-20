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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author WAFA
 */
public class UpdateproductFXMLController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfValue;
    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<String> subcategory;
    @FXML
    private Button btnaddproduct;
    @FXML
    private Button btnaddimage;
    @FXML
    private Label inputcontrol;
    @FXML
    private Button btnlistproduct;
    @FXML
    private Button btnhome;
    @FXML
    private ImageView image;
    CategoryServices cs = new CategoryServices();
    SubCategoryServices scs = new SubCategoryServices();
    ProductServices ps = new ProductServices();
    Product p=new Product();
    String imagename;
    @FXML
    private Button btncancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Category> l = cs.getAll();
        List<String> l1 = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getName());

        }

        category.setItems(FXCollections.observableArrayList(l1));
        

    }    

    public void setproduct(Product product){
          p=new Product(product.getId(),product.getName(),product.getDescription(),product.getImage(),product.getPrice(),product.getSubCategory().getId(),product.getIduser());
          System.out.println(p);
          inputcontrol.setText("");
        System.out.println(p);
        tfName.setText(p.getName());
        tfDescription.setText(p.getDescription());
        tfValue.setText(String.valueOf(p.getPrice()));
        Category c =cs.getById(p.getSubCategory().getCategory().getId());
        category.setValue(c.getName());
        subcategory.setValue(p.getSubCategory().getName());
        Image image1 = new Image("file:src/assets/" + p.getImage());
            image.setImage(image1);
            imagename=p.getImage();
       
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
    private void updateproduct(ActionEvent event) {
       
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
        p.setName(tfName.getText());
        p.setDescription(tfDescription.getText());
        p.setPrice(value);
        p.setImage(imagename);
        p.setSubcategory(l.get(0));
        System.out.println(p);
        
        ps.modifyProduct(p);

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Product modified !", ButtonType.FINISH);
        a.showAndWait();
        gotolistproduct(event);
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

    @FXML
    private void cancel(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/feedproductFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btncancel.getScene().setRoot(root);
    }

    
}
