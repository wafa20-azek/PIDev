/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.Offre;
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
import javafx.scene.Group;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
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
    private Button btnMyProduct;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnMyOffer;

    private Button btncreationOffer;
    @FXML
    private Button createOffer;
    @FXML
    private AnchorPane feed;
//    @FXML
//    private ListView<Offre> offreListView;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane feed2;
    @FXML
    private AnchorPane feed3;

    /*

     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        offreListView.setItems(FXCollections.observableArrayList());

    }

    @FXML
    private void offerStatus(ActionEvent event) {
        vbox.getChildren().clear();
        List<Offre> listoffre = new ArrayList<>();
//        System.out.println(listoffre);
        ServiceOffre so = new ServiceOffre();
        
//        offreListView.getItems();
        listoffre = so.getBYStatus("On_Hold");
        System.out.println(listoffre);
        int x = 0, y = 0;
        for (Offre o : listoffre) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x + 14);
            an.setLayoutY(y + 17);
            Label title = new Label(o.getName());
            String s = String.valueOf(o.getDate_offre()); 
            Label date = new Label(s);
            title.setLayoutX(x + 14);
            title.setLayoutY(y + 17);
            date.setLayoutX(x + 100);
            date.setLayoutY(x + 17);
//         InputStream imgS = getClass().getResourceAsStream("/img/5starsratting.png");
//            Image img = new Image(imgS,20,20, false, false);
//            ImageView imv = new ImageView(img);
//            imv.setOnMouseClicked(MouseEvent -> sa.);
//            imv.setLayoutX(x + 336);
//            imv.setLayoutY(y + 8);
            InputStream imgStream = getClass().getResourceAsStream("/img/331913680_2489949844485663_4749868781026670821_n.png");
            Image img = new Image(imgStream, 25, 25, false, false);
            ImageView imv = new ImageView(img);
            imv.setOnMouseClicked(MouseEvent -> so.AccepterOffre(o));
            imv.setLayoutX(x + 336);
            imv.setLayoutY(y + 8);
            InputStream imgStream1 = getClass().getResourceAsStream("/img/331325598_530270349096012_5101557465361111528_n.png");
            Image img1 = new Image(imgStream1, 25, 25, false, false);
            ImageView imgv = new ImageView(img1);
            imgv.setOnMouseClicked(MouseEvent -> so.RefuserOffer(o));
            imgv.setLayoutX(x + 366);
            imgv.setLayoutY(y + 8);
            InputStream imgStream2 = getClass().getResourceAsStream("/img/modifiericon.png");
            Image imgv1 = new Image(imgStream2, 20, 20, false, false);
            ImageView imv1 = new ImageView(imgv1);
            imv1.setOnMouseClicked(MouseEvent -> {
                try {
                    so.modifierOffre(o);
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
            imv1.setLayoutX(x + 398);
            imv1.setLayoutY(y + 10);
         
            Button btnOn_Hold = new Button("On_Hold");
            an.getChildren().addAll(title, date, imv, imgv, imv1);
            feed.getChildren().addAll(an);
            vbox.getChildren().add(an);

        }

    }

    @FXML
    private void offerStatus1(ActionEvent event) {
//       vbox.getChildren().clear();
        int x = 0, y = 0;
//        ServiceOffre so = new ServiceOffre();
//        List<Offre> listoffre1 = so.getBYStatus("Accepted");
//
//        offreListView.getItems();
//       
//        System.out.println(listoffre1);
        vbox.getChildren().clear();
        List<Offre> listoffre1 = new ArrayList<>();
//        System.out.println(listoffre);
        ServiceOffre so = new ServiceOffre();
        listoffre1 = so.getBYStatus("Accepted");
//        offreListView.getItems().clear();
//        offreListView.getItems().addAll(listoffre1);
        for (Offre o : listoffre1) {
//            VBox vbox=new VBox();
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x + 14);
            an.setLayoutY(y + 17);
            Label title = new Label(o.getName());
            String s = String.valueOf(o.getDate_offre());
            Label value = new Label(s);
            title.setLayoutX(x + 14);
            title.setLayoutY(y + 17);
            value.setLayoutX(x + 130);
            value.setLayoutY(x + 17);
            InputStream imgStream2 = getClass().getResourceAsStream("/img/imprimericon.png");
            Image imgv1 = new Image(imgStream2, 15, 15, false, false);
            ImageView imv1 = new ImageView(imgv1);
            imv1.setOnMouseClicked(mouseEvent -> {
                try {
                    so.generatePDF(o);
                    File file = new File("C:/Users/bochr/Desktop/PIDev-master/offre.pdf");
                    Desktop.getDesktop().open(file);
                    System.out.println(true);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
            imv1.setLayoutX(x + 398);
            imv1.setLayoutY(y + 10);

////            offreListView.setCellFactory(param -> new ListCell<Offre>() {
////                @Override
////                protected void updateItem(Offre item, boolean empty) {
////                    super.updateItem(item, empty);
////
////                    if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
////                        setGraphic(null);
////                    } else {
////                        AnchorPane an = new AnchorPane();
////                        // Ajoutez votre code pour construire la vue de chaque offre ici
////                        setGraphic(an);
////                    }
//                }
//            });

            an.getChildren().addAll(title, value, imv1);
            feed2.getChildren().add(an);
            vbox.getChildren().add(an);
//            vbox.getChildren().add(offreListView);
        }
    }

    @FXML
    private void offerStatus2(ActionEvent event) {
            int x = 0, y = 0;
    vbox.getChildren().clear();
    List<Offre> listoffre3 = new ArrayList<>();
    ServiceOffre so = new ServiceOffre();
    listoffre3 = so.getBYStatus("Declined");
//    offreListView.getItems().clear();
//    offreListView.getItems().addAll(listoffre3);
    for (Offre o : listoffre3) {
        AnchorPane an = new AnchorPane();
        an.setLayoutX(x + 14);
        an.setLayoutY(y + 17);
        Label title = new Label(o.getName());
        String s = String.valueOf(o.getDate_offre());
        Label value = new Label(s);
        title.setLayoutX(x + 14);
        title.setLayoutY(y + 17);
        value.setLayoutX(x + 130);
        value.setLayoutY(x + 17);
//        offreListView.setCellFactory(param -> new ListCell<Offre>() {
//            @Override
//            protected void updateItem(Offre item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                    setGraphic(null);
//                } else {
//                    AnchorPane an = new AnchorPane();
//                    // Ajoutez votre code pour construire la vue de chaque offre ici
//                    setGraphic(an);
//                }
//            }
//        });
        an.getChildren().addAll(title, value);
        feed3.getChildren().add(an);
        vbox.getChildren().add(an);
    }
//        int x = 0, y = 0;
////        ServiceOffre so = new ServiceOffre();
////        List<Offre> listoffre1 = so.getBYStatus("Accepted");
////
////        offreListView.getItems();
////       
////        System.out.println(listoffre1);
//        vbox.getChildren().clear();
//        List<Offre> listoffre3 = new ArrayList<>();
////        System.out.println(listoffre);
//        ServiceOffre so = new ServiceOffre();
//        listoffre3 = so.getBYStatus("Declined ");
//        offreListView.getItems().clear();
//        offreListView.getItems().addAll(listoffre3);
//        for (Offre o : listoffre3) {
//            AnchorPane an = new AnchorPane();
//            an.setLayoutX(x + 14);
//            an.setLayoutY(y + 17);
//            Label title = new Label(o.getName());
//            String s = String.valueOf(o.getDate_offre());
//            Label value = new Label(s);
//            title.setLayoutX(x + 14);
//            title.setLayoutY(y + 17);
//            value.setLayoutX(x + 130);
//            value.setLayoutY(x + 17);
//            offreListView.setCellFactory(param -> new ListCell<Offre>() {
//                @Override
//                protected void updateItem(Offre item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                        setGraphic(null);
//                    } else {
//                        AnchorPane an = new AnchorPane();
//                        // Ajoutez votre code pour construire la vue de chaque offre ici
//                        setGraphic(an);
//                    }
//                }
//            });
//            an.getChildren().addAll(title, value);
//            feed3.getChildren().add(an);
//            vbox.getChildren().add(an);
////            vbox.getChildren().add(offreListView);
//        }
    }

//        vbox.getChildren().clear();
//            List<Offre> listoffre2 = new ArrayList<>();
////        System.out.println(listoffre);
//            ServiceOffre so = new ServiceOffre();
//            listoffre2 = so.getBYStatus("Declined");
//            ObservableList<Offre> items = offreListView.getItems();
//            items.clear();
//            items.addAll(listoffre2);
//            offreListView.setCellFactory(param -> new ListCell<Offre>() {
//                @Override
//                protected void updateItem(Offre item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                        setText(null);
//                    } else {
//                        setText(item.getName() + " - " + item.getDate_offre().toString());
//                    }
//                }
//            });
//            vbox.getChildren().add(offreListView);
//        }
    @FXML
    private void consulterOffers(ActionEvent event
    ) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheOffreFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ServiceOffre serviceOffre = new ServiceOffre();
            Offre o = new Offre();
            List<Offre> offres = serviceOffre.getAll();

        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }

        btnMyOffer.getScene().setRoot(root);

    }

    @FXML
    private void createoffer(ActionEvent event
    ) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationOfferFXML.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene nv = new Scene(root);
            Stage s = (Stage) createOffer.getScene().getWindow();
            s.setScene(nv);
            s.show();
        } catch (IOException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }

}
//    @FXML
//    private void offerStatus1(ActionEvent event) {
//    vbox.getChildren().clear();
//    int x = 0, y = 0;
//    ServiceOffre so = new ServiceOffre();
//    List<Offre> listoffre1 = so.getBYStatus("Accepted");
//
//    offreListView.getItems().setAll(listoffre1);
//    offreListView.setCellFactory(param -> new ListCell<Offre>() {
//        @Override
//        protected void updateItem(Offre item, boolean empty) {
//            super.updateItem(item, empty);
//
//            if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                setText("");
//            } else {
//                setText(item.getName() + " - " + item.getDate_offre().toString());
//            }
//        }
//    });
//
//    for (Offre o : listoffre1) {
//        AnchorPane an = new AnchorPane();
//        an.setLayoutX(x + 14);
//        an.setLayoutY(y + 17);
//
//        InputStream imgStream2 = getClass().getResourceAsStream("/img/imprimericon.png");
//        Image imgv1 = new Image(imgStream2, 15, 15, false, false);
//        ImageView imv1 = new ImageView(imgv1);
//        imv1.setOnMouseClicked(mouseEvent -> {
//            try {
//                so.generatePDF(o);
//                Desktop.getDesktop().open(new File("offre.pdf"));
//                File file = new File("offre.pdf");
//if (Desktop.isDesktopSupported()) {
//    BROWSE br = new BROWSE("https://www.google.com");
//    Desktop desktop = Desktop.getDesktop();
//    if (desktop.isSupported(Desktop.Action.BROWSE)) {
//        try {
//            desktop.browse(file.toURI());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            }}}} catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
//        imv1.setLayoutX(x + 398);
//        imv1.setLayoutY(y + 10);
//        Button btnAccepted = new Button("Accepted");
//        an.getChildren().addAll(imv1);
//        vbox.getChildren().add(an);
//    }
//}
//    private void offerStatus1(ActionEvent event) {
//        vbox.getChildren().clear();
//        List<Offre> listoffre1 = new ArrayList<>();
////        System.out.println(listoffre);
//        int x = 0, y = 0;
//        ServiceOffre so = new ServiceOffre();
//        listoffre1 = so.getBYStatus("Accepted");
//        AnchorPane an = new AnchorPane();
//        an.setLayoutX(x + 14);
//        an.setLayoutY(y + 17);
//        ObservableList<Offre> items = offreListView.getItems();
//        items.clear();
//        items.addAll(listoffre1);
//
//        offreListView.setCellFactory(param -> new ListCell<Offre>() {
//            @Override
//            protected void updateItem(Offre item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                    setText(null);
//                } else {
//                    setText(item.getName() + " - " + item.getDate_offre().toString());
//                }
//            }
//        });
//
//        for (Offre o : listoffre1) {
//
//            InputStream imgStream2 = getClass().getResourceAsStream("/img/imprimericon.png");
//            Image imgv1 = new Image(imgStream2, 15, 15, false, false);
//            ImageView imv1 = new ImageView(imgv1);
//              imv1.setOnMouseClicked(mouseEvent -> {
//        so.generatePDF(o);
//        try {
//            Desktop.getDesktop().open(new File("offre.pdf"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    });
////            imv1.setOnMouseClicked(MouseEvent -> so.generatePDF(o)).webView.getEngine().load("file:offre.pdf");
//            imv1.setLayoutX(x + 398);
//            imv1.setLayoutY(y + 10);
//            Button btnAccepted = new Button("Accepted");
//            an.getChildren().addAll(imv1);
////            feed.getChildren().addAll(an);
//       vbox.getChildren().add(an);
////            vbox.getChildren().add(offreListView);
//        }
//    }

//           offreListView.getItems().add(listoffre1);
//          
//            offreListView.setCellFactory(param -> new ListCell<Offre>() {
//                @Override
//                protected void updateItem(Offre item, boolean empty) {
//                    super.updateItem(item, empty);
//
//                    if (empty || item == null || item.getName() == null || item.getDate_offre() == null) {
//                        setText("");
//                    } else {
//                        setText(item.getName() + " - " + item.getDate_offre().toString());
//                    }
//                }
//            });

           //            Button btnAccepted = new Button("Accepted");
