/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Admin;
import edu.dottn.entities.Avis_Offer;
import edu.dottn.entities.Member;
import edu.dottn.entities.Offre;
import edu.dottn.entities.User;
import edu.dottn.services.ServiceAvis;
import edu.dottn.services.ServiceOffre;
import java.awt.Desktop;
import static java.awt.Desktop.Action.BROWSE;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.load;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.fxml.FXMLLoader.load;
import static javafx.fxml.FXMLLoader.load;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import org.controlsfx.control.Rating;
import sun.awt.DesktopBrowse;

/**
 * FXML Controller class
 *
 * @author bochr
 */
public class AfficheOffreFXMLController implements Initializable {

    @FXML
    private Button btnOn_Hold;
    @FXML
    private Button btnAccepted;
    @FXML
    private Button btnDeclined;
   
    @FXML
    private AnchorPane feed;
//    @FXML
//    private ListView<Offre> offreListView;
    @FXML
    private VBox vbox;
  
    private User user = new Member();


 
    /*

     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
//        offreListView.setItems(FXCollections.observableArrayList());
    }

    void setInformation(User P) {
        user = P;
    }

    @FXML
    private void offerStatus(ActionEvent event) {
        vbox.getChildren().clear();
        List<Offre> listoffre = new ArrayList<>();

        ServiceOffre so = new ServiceOffre();
        listoffre = so.getBYStatus("On_Hold", user.getIdUser());
        System.out.println(listoffre.size());
        //System.out.println(listoffre);
        int x = 0, y = 0;

        for (Offre o : listoffre) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x + 14);
            an.setLayoutY(y + 17);

            Label name = new Label(o.getUser1().getName());
            String s = String.valueOf(o.getDate_offre());
            Label date = new Label(s);
            name.setLayoutX(x + 14);
            name.setLayoutY(y + 17);
            date.setLayoutX(x + 100);
            date.setLayoutY(x + 17);
            // Avis_Offer a = new Avis_Offer(o,o1.getIdUser(),o1.getID_Product(),0);
            //listeavis.add(a);
            InputStream imgStream = getClass().getResourceAsStream("/img/331913680_2489949844485663_4749868781026670821_n.png");
            Image img = new Image(imgStream, 25, 25, false, false);
            ImageView imv = new ImageView(img);
            imv.setOnMouseClicked(MouseEvent -> so.AccepterOffre(o));
            imv.setLayoutX(x + 366);
            imv.setLayoutY(y + 17);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/331325598_530270349096012_5101557465361111528_n.png");
            Image img1 = new Image(imgStream1, 25, 25, false, false);
            ImageView imgv = new ImageView(img1);
            imgv.setOnMouseClicked(MouseEvent -> so.RefuserOffer(o));
            imgv.setLayoutX(x + 398);
            imgv.setLayoutY(y + 17);
            InputStream imgStream2 = getClass().getResourceAsStream("/img/modifiericon.png");
            Image imgv1 = new Image(imgStream2, 20, 20, false, false);
            ImageView imv1 = new ImageView(imgv1);
            imv1.setOnMouseClicked(MouseEvent -> {
                try {
                    //so.modifierOffre(o);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierOffreFXML.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });

            imv1.setLayoutX(x + 435);
            imv1.setLayoutY(y + 17);
            InputStream imgS = getClass().getResourceAsStream("/img/supprimer.png");
            Image im = new Image(imgS, 25, 25, false, false);
            ImageView iv = new ImageView(im);
            iv.setOnMouseClicked(MouseEvent -> so.supprimerOffre(o));
            iv.setLayoutX(x + 460);
            iv.setLayoutY(y + 17);
            Button btnOn_Hold = new Button("On_Hold");
            an.setOnMouseClicked(MouseEvent -> {
             NavigationController.changedetailofferPage(event, o, "DetailofferFXML.fxml");
            });

            an.getChildren().addAll(name, date, imv, imgv, imv1, iv);
            feed.getChildren().addAll(an);
            vbox.getChildren().add(an);

        }

    }

    @FXML
    private void offerStatus1(ActionEvent event) {
        int x = 0, y = 0;
        List<Avis_Offer> listeavis = new ArrayList();
        ServiceAvis sa = new ServiceAvis();
        vbox.getChildren().clear();
        List<Offre> listoffre1 = new ArrayList<>();
        ServiceOffre so = new ServiceOffre();
        listoffre1 = so.getBYStatus("Accepted", user.getIdUser());

        for (Offre o : listoffre1) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x + 14);
            an.setLayoutY(y + 17);
            Label title = new Label(o.getUser1().getName());
            String s = String.valueOf(o.getDate_offre());
            Label value = new Label(s);
            title.setLayoutX(x + 14);
            title.setLayoutY(y + 17);
            value.setLayoutX(x + 130);
            value.setLayoutY(x + 17);
            Avis_Offer a = new Avis_Offer(o, 0);
            //listeavis.add(a);
            Rating r = new Rating(3, 0);
            r.setLayoutX(x + 270);
            r.setLayoutY(y + 10);

            if (sa.getAvisOffeByIdoffre(o.getId_Offre()) == null) {
                r.ratingProperty().addListener((observable, oldValue, newValue) -> {
                   
                    a.setRatting(newValue.intValue());
                    sa.ajouterAvisOffer(a);
                });
            } else {
                r.setRating(sa.getAvisOffeByIdoffre(o.getId_Offre()).getRatting());
            }

            InputStream imgStream2 = getClass().getResourceAsStream("/img/imprimericon.png");
            Image imgv1 = new Image(imgStream2, 15, 15, false, false);
            ImageView imv1 = new ImageView(imgv1);
            imv1.setOnMouseClicked(mouseEvent -> {
                try {
                    so.generatePDF(o);
                    File file = new File("C:/Users/WAFA/Documents/NetBeansProjects/PIDEV/offre.pdf");
                    Desktop.getDesktop().open(file);
                    System.out.println(true);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
            imv1.setLayoutX(x + 398);
            imv1.setLayoutY(y + 10);
an.setOnMouseClicked(MouseEvent -> {
                try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailofferFXML.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }catch(Exception e){
                    System.out.println(e.getMessage());}
            });
            an.getChildren().addAll(title, r, value, imv1);
            feed.getChildren().add(an);
            vbox.getChildren().add(an);

        }
    }

    @FXML
    private void offerStatus2(ActionEvent event) {
        int x = 0, y = 0;
        vbox.getChildren().clear();
        List<Offre> listoffre3 = new ArrayList<>();
        ServiceOffre so = new ServiceOffre();
        listoffre3 = so.getBYStatus("Declined", user.getIdUser());

        for (Offre o : listoffre3) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x + 14);
            an.setLayoutY(y + 17);
            Label title = new Label(o.getUser1().getName());
            String s = String.valueOf(o.getDate_offre());
            Label value = new Label(s);
            title.setLayoutX(x + 14);
            title.setLayoutY(y + 17);
            value.setLayoutX(x + 130);
            value.setLayoutY(x + 17);

            an.getChildren().addAll(title, value);
            feed.getChildren().add(an);
            vbox.getChildren().add(an);
        }
    }

//    private void consulterOffers(ActionEvent event
//    ) {
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
//        Parent root = null;
//        try {
//            root = loader.load();
//            ServiceOffre serviceOffre = new ServiceOffre();
//            Offre o = new Offre();
//            List<Offre> offres = serviceOffre.getAll();
//
//        } catch (IOException e) {
//            System.out.println("Error :" + e.getMessage());
//        }
//
//        btnMyOffer.getScene().setRoot(root);
//
//    }

}
