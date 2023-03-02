/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;


import edu.dottn.entities.Association;
import edu.dottn.entities.Message;
import edu.dottn.entities.user;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.MessageServices;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class MessagesController implements Initializable {

    @FXML
    private ListView<user> conversationListView;

    private Association loggedInAssociation;

    private AssociationServices associationServices = new AssociationServices();

    private MessageServices messageServices = new MessageServices();

    @FXML
    private AnchorPane anchorMessages;
    @FXML
    private Label hellouserText;
    @FXML
    private VBox messageArea;
    
    private   List<Message> conversation;
    @FXML
    private TextField messageInput;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView imageuser;
    @FXML
    private TextField searchMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInAssociation = associationServices.getLoggedInAssociation();
        hellouserText.setText(loggedInAssociation.getAssocName());
        displayConversationUsers(String.valueOf(loggedInAssociation.getId()));
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        try{
        String imagePath = loggedInAssociation.getImage();
        File file = new File(imagePath);
         url = file.toURI().toURL();
        Image image = new Image(url.toString());
        imageuser.setImage(image);
        } catch (MalformedURLException ex) {
        }
        
        
        
        
        
        
    }

    public void setLoggedInAssociation(Association association) {
        this.loggedInAssociation = association;
    }

  public void displayConversationUsers(String loggedInAssociationId) {
    List<Integer> userIds = messageServices.getConversationUserIds(loggedInAssociationId);

    if (userIds.isEmpty()) {
        System.out.println("No conversations found.");
        return;
    }

    ObservableList<user> users = FXCollections.observableArrayList();
    
    for (Integer userId : userIds) {
    user user = associationServices.getOneById(userId);
    users.add(user);
    }

     

        FilteredList<user> filteredUsers = new FilteredList<>(users);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredUsers.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; 
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return user.getName().toLowerCase().contains(lowerCaseFilter); 
            });
        });
        searchField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                
                filteredUsers.setPredicate(user -> false);
            }
        });
      

        conversationListView.setItems(filteredUsers);
        conversationListView.setCellFactory(param -> new ListCell<user>() {
            @Override
            protected void updateItem(user item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setDisable(true);
                } else {
                    setDisable(false);
                    setText(item.getName());
                }
            }
        });



    conversationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    user selectedUser = conversationListView.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        // display conversation with selectedUser
        conversation = messageServices.getConversation( loggedInAssociation.getId(),selectedUser.getIdUser());
        displayConversation(conversation);
    
    
    }
});


}

private void displayConversation(List<Message> messages) {
    Stage stage = new Stage();
    VBox root = new VBox();
    root.setPadding(new Insets(10));
    root.setSpacing(10);
    messageArea.getChildren().clear();
    
   

    for (Message message : messages) {
      
        String sender = message.getSenderId().equals(String.valueOf(loggedInAssociation.getId())) ? "Association" : "User";
        String text = message.getText();
        Timestamp timestamp = message.getTime();
        System.out.println(message.getText()+"    "+message.getSenderId() + "      "+sender);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        String time = timeFormat.format(message.getTime());

        Label label = new Label(text+"    "+time);
        label.getStyleClass().add("message-bubble");
        label.getStyleClass().add(sender.toLowerCase());

        HBox messageBox = new HBox(label);
        messageBox.setAlignment(sender.equals("User") ? Pos.CENTER_LEFT : Pos.CENTER_RIGHT);
        messageBox.setPadding(new Insets(5));
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete");
        contextMenu.getItems().add(deleteItem);

        messageArea.getChildren().add(messageBox);
        
        messageBox.setOnContextMenuRequested(event ->{
            contextMenu.show(label, event.getScreenX(), event.getScreenY());
        });
   
    }
   

}

    public void sendMessage() {
    String messageText = messageInput.getText();
    if (!messageText.isEmpty()) {
        
        user selectedUser = conversationListView.getSelectionModel().getSelectedItem();
        
        if (selectedUser == null) {
            System.out.println("NULLLLLLLLLLLl");
            return;
        }
        
        messageServices.sendAssociationMessage(String.valueOf(loggedInAssociation.getId()), String.valueOf(selectedUser.getIdUser()), messageText);

        conversation = messageServices.getConversation(loggedInAssociation.getId(),selectedUser.getIdUser());

        displayConversation(conversation);

        messageInput.clear();
    }}


}

