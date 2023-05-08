/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
import edu.dottn.entities.Product;
import edu.dottn.entities.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author WALID
 */
public class NavigationController {
    public static void changefeeddonation(ActionEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void changefeedevent(ActionEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void changerecadmin(ActionEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void changeReclamtion(ActionEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void changemyoffersPage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            AfficheOffreFXMLController myoffers = loader.getController();
            myoffers.setInformation(P);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public static void changedetailofferPage(ActionEvent event,Offre o, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            DetailofferFXMLController myoffer = loader.getController();
            myoffer.setoffre(o);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void changeCreateofferPage(ActionEvent event,User u,Product p, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            CreationOfferFXMLController offer = loader.getController();
            //offer.setInformation(u);
            offer.setproductinfo(p);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void changeproductPage(MouseEvent event,User u,Product p,Boolean b, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            ProductFXMLController product = loader.getController();
            product.setInformation(u);
            product.setproduct(p);
            product.setvisibility(b);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void changeProductdashboradPage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            DashboardproducFXMLController dashboard = loader.getController();
           
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void changeFeedPage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            FeedproductFXMLController Feed = loader.getController();
            Feed.setInformation(P);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public static void changeAddproductPage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            AddproductFXMLController Addproduct = loader.getController();
            Addproduct.setInformation(P);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
//        public static void changeUpdateproductPage(Product p,User P, String fxmlFile){
//        Parent root = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
//            root = loader.load();
//            UpdateproductFXMLController Updateproduct = loader.getController();
//            Updateproduct.setInformation(P);
//            Updateproduct.setproduct(pu);
//           Scene scene = new Scene(root,1250,800);
//            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("TrocTn");
//            primaryStage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
         public static void changeMyproductsPage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            ListproductFXMLController Myproducts = loader.getController();
            Myproducts.setInformation(P);
           Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TrocTn");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void changeToTwoFactorAuthentication(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            TwoFactorAuthenticationController TFA = loader.getController();
            TFA.setInformation(P);
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("TwoFactorAuthPage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void changeProfilePage(MouseEvent event,User P,String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            ProfilePageController Home = loader.getController();
            Home.setInformation(P);
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("ProfilePage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void changeForgetPassword(MouseEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Forget Password");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void changeActivatePage(ActionEvent event, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("ActivateAccount");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void AdminHomePage(ActionEvent event,User P, String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            AdminDashboardController a= loader.getController();
            a.setInformation(P);
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("AdminHomePage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void changeLoginPage(ActionEvent event,String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("LoginPage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    public static void changeLoginPage(MouseEvent event,String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("LoginPage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    public static void changeSignUpPage(ActionEvent event,String fxmlFile){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            root = loader.load();
            Scene scene = new Scene(root,1280,700);
            Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("SignUpPage");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void openSession(String fxmlFile,User P,Text t){
        
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            Parent root = loader.load();
            FeedproductFXMLController feed = loader.getController();
            feed.setInformation(P);
            t.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public static void openSessionadmin(String fxmlFile,User P,Text t){
        
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlFile));
            Parent root = loader.load();
            AdminDashboardController feed = loader.getController();
            feed.setInformation(P);
            t.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
