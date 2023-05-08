/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Config;
import edu.dottn.entities.Category;
import edu.dottn.entities.Product;
import edu.dottn.entities.SubCategory;
import edu.dottn.entities.User;
import edu.dottn.services.CategoryServices;
import edu.dottn.services.ProductServices;
import edu.dottn.services.SubCategoryServices;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.AnchorPane;
import org.apache.commons.lang3.RandomStringUtils;

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
    private Button btnlistproduct;
    private Button btnhome;
  User user = new User();
    @FXML
    private AnchorPane captchaContainer;
    @FXML
    private TextField captchaField;
    DefaultKaptcha captcha;
    String randomWord = RandomStringUtils.randomAlphanumeric(6);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        captcha = new DefaultKaptcha();
        captcha.setConfig(new Config(new Properties()));

        BufferedImage image = captcha.createImage(randomWord);
        Image fxImage = SwingFXUtils.toFXImage(image, null);
        ImageView imageView = new ImageView(fxImage);
        imageView.setY(25);
        imageView.setX(25);
        captchaContainer.getChildren().add(imageView);
        
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
 public void setInformation(User u) {
         user = u;
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
        }
        else if ( !randomWord.equals(captchaField.getText())){
           inputcontrol.setText("wrong captcha!");
        }else
        
       
        { 
        List<SubCategory> l = (List<SubCategory>) scs.getByName(subcategory.getValue());
        float value=Float.parseFloat(tfValue.getText());
       
        Product p = new Product(tfName.getText(), tfDescription.getText(), imagename,value , l.get(0),user);
        System.out.println(p);
        ProductServices ps = new ProductServices();
        if (ps.exist(p)==null){
        ps.addProduct(p);

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Product added !", ButtonType.FINISH);
        a.showAndWait();
      NavigationController.changeMyproductsPage(event, user, "listproductFXML.fxml");
}
        else {inputcontrol.setText("Product already exists!");}
        
        }
    }

    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            imagename = selectedFile.getName().toString();
            File newFile = new File("C:/xampp/htdocs/img/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Image image1 = new Image("file:C:/xampp/htdocs/img/" + imagename);
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

   

}
