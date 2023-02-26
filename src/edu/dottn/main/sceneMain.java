package edu.dottn.main;


import edu.dottn.entities.Association;
import edu.dottn.services.AssociationServices;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class sceneMain extends Application {

    AssociationServices as = new AssociationServices();
    Association loggedInAssociation = new Association();

    @Override
    public void start(Stage primaryStage) throws Exception {
        File lockFile = new File(".lock");
        if (lockFile.exists()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("App is already running");
            alert.showAndWait();
             System.exit(0);
            
        }else {
            lockFile.createNewFile();   
        }
        loggedInAssociation = as.getLoggedInAssociation();
        if (loggedInAssociation == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/FXML.fxml"));
            Parent root = loader.load();

            Image icon = new Image(getClass().getResourceAsStream("/icon.png"));

            Scene scene = new Scene(root, 1280, 700);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Troctn Desktop App ");
            scene.getStylesheets().add("styles.css");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/profile.fxml"));
        Parent root = loader.load();

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));

        Scene scene = new Scene(root, 1280, 700);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Troctn Desktop App ");
        scene.getStylesheets().add("styles.css");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.show();
        }

     
        primaryStage.setOnCloseRequest(e -> {
             lockFile.delete();
        });
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}

