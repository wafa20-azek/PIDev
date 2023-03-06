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
import edu.dottn.util.UserSession;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
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
    @FXML
    private ScrollPane scroll;
UserSession us=new UserSession();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        l = ps.getAll();
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

    public void loadproducts(List<Product> l) {

        BorderStroke borderStroke = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(0, 0, 1, 0));
        Border border = new Border(borderStroke);
        feed.getChildren().clear();

        int k = 0;
//        AnchorPane head = new AnchorPane();
//        Label headimg = new Label("image");
//        Label headtitle = new Label("name");
//        Label headvalue = new Label("value");
//        Label headdescription = new Label("description");
//        headimg.setLayoutX(30);
//        headtitle.setLayoutX(170);
//        headvalue.setLayoutX(250);
//        headdescription.setLayoutX(200);
//        head.setBorder(border);
//        head.setLayoutY(10);
//        head.setLayoutX(10);
//        head.setMinWidth(700);
//        head.getChildren().addAll(headimg, headtitle, headvalue, headdescription);
//        feed.getChildren().addAll(head);
        scroll.setFitToWidth(true);
        float x = 20, y = 50;
        for (int i = 0; i < l.size(); i++) {

            AnchorPane anchorpane = new AnchorPane();
            Image image = new Image("file:src/assets/" + l.get(i).getImage(), 100, 100, false, false);
            ImageView iv = new ImageView(image);
//            Image image1 = new Image("file:src/assets/3405244.png", 30, 30, false, false);
//            ImageView iv1 = new ImageView(image1);
            Label title = new Label(l.get(i).getName());
            Label Description = new Label(l.get(i).getDescription());
            String s = String.valueOf(l.get(i).getPrice());
            Label value = new Label(s);
            anchorpane.setLayoutY(y);
            anchorpane.setBorder(border);
            anchorpane.setMinWidth(870);
            iv.setLayoutX(x+10);
            iv.setLayoutY(20);
            title.setLayoutX(x + 220);
            value.setLayoutX(x + 325);
            Description.setLayoutX(x + 500);
            Description.setMaxWidth(200);
            //Description.setWrapText(true);
            
            int id = l.get(i).getId();
            Button btndelete = new Button("delete");
            btndelete.setLayoutX(x + 720);
            btndelete.setOnMouseClicked((MouseEvent MouseEvent) -> {
                ps.removeProduct(id);
                   Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource("dashboardproductFXML.fxml"));
            root = loader.load();
            DashboardproducFXMLController dashboard = loader.getController();
           
           Scene scene = new Scene(root);
            Stage primaryStage = (Stage)((Node) MouseEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                
            });
            Product p=l.get(i);
            anchorpane.setOnMouseClicked(MouseEvent -> {
               
                  
                try {
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("productFXML.fxml"));
                    Parent product = loader.load();
                    ProductFXMLController prod=loader.getController();
                    prod.setproduct(p);
                    prod.setvisibility(Boolean.FALSE);
                     Scene secondScene = new Scene(product);
                Stage secondStage = new Stage();
                secondStage.setScene(secondScene);
                secondStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(DashboardproducFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }            
            });
           
            y += 150;
            anchorpane.getChildren().addAll(iv, title, value, Description, btndelete);

            feed.getChildren().addAll(anchorpane);
            

        }

    }
    
 @FXML
    private void searchbyname(KeyEvent event) {
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
