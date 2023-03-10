
package edu.dottn.gui;

import edu.dottn.entities.Association;
import edu.dottn.entities.Message;
import edu.dottn.entities.User;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.MessageServices;
import edu.dottn.util.UserSession;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ConversationUserController implements Initializable {

    @FXML
    private Label hellouserText;
    @FXML
    private ImageView imageuser;
    @FXML
    private AnchorPane anchorMessages;
    @FXML
    private ListView<Association> conversationListView;
    @FXML
    private VBox messageArea;
    @FXML
    private TextField messageInput;
    @FXML
    private TextField searchField;
    
    private   List<Message> conversation;
    
    UserSession us = new UserSession();
    private MessageServices messageServices = new MessageServices();
    private AssociationServices associationServices = new AssociationServices();
    User user1 = us.getUser();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        hellouserText.setText("Hello "+user1.getName());
        displayConversationUsers(String.valueOf(user1.getIdUser()));
        messageInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        
    }

   

  public void displayConversationUsers(String loggedInAssociationId) {
    List<Integer> userIds = messageServices.getConversationUserIds(loggedInAssociationId);
      System.out.println(userIds);

    if (userIds.isEmpty()) {
        System.out.println("No conversations found.");
        return;
    }

    ObservableList<Association> users = FXCollections.observableArrayList();
    
    for (Integer userId : userIds) {
    Association a = associationServices.getById(userId);
    users.add(a);
    }
      System.out.println(users);

     

        FilteredList<Association> filteredUsers = new FilteredList<>(users);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredUsers.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; 
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return user.getAssocName().toLowerCase().contains(lowerCaseFilter); 
            });
        });
        searchField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                
                filteredUsers.setPredicate(user -> false);
            }
        });
      

        conversationListView.setItems(filteredUsers);
        conversationListView.setCellFactory(param -> new ListCell<Association>() {
            @Override
            protected void updateItem(Association item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setDisable(true);
                } else {
                    setDisable(false);
                    setText(item.getAssocName());
                }
            }
        });



    conversationListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    Association selectedUser = conversationListView.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        // display conversation with selectedUser
        conversation = messageServices.getConversation( user1.getIdUser(),selectedUser.getId());
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
      
        String sender = message.getSenderId().equals(String.valueOf(user1.getIdUser())) ? "User" : "Association";
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
        
        Association selectedUser = conversationListView.getSelectionModel().getSelectedItem();
     
        
        if (selectedUser == null) {
            System.out.println("NULLLLLLLLLLLl");
            return;
        }
        
       messageServices.sendUserMessage(String.valueOf(user1.getIdUser()), String.valueOf(selectedUser.getId()), messageText);

        conversation = messageServices.getConversation(user1.getIdUser(),selectedUser.getId());

        displayConversation(conversation);

        messageInput.clear();
    }}


}
