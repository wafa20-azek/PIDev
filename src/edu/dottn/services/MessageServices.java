package edu.dottn.services;

import edu.dottn.entities.Association;
import edu.dottn.entities.Message;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MessageServices {
   
//    public String getSenderName (String senderId){
//               Connection conn = MyConnection.getInstance().getConnection();
//               String senderName=null;
//                try {
//               
//                   PreparedStatement statement = conn.prepareStatement("SELECT name FROM user WHERE idUser = ?");
//                   statement.setString(1, senderId);
//                   ResultSet result = statement.executeQuery();
//                   if (result.next()) {
//                        senderName = result.getString("name");
//                        
//                   }
//                    } catch (SQLException sqlEx) {
//                             System.out.println(sqlEx.getMessage());
//                    }
//            return senderName;  
//           }
    
    public static List<Message> retrieveAll(String loggedInAssociationId) {
        List<Message> messages = new ArrayList<>();
        Connection conn = MyConnection.getInstance().getConnection();
        AssociationServices as = new AssociationServices();
        
        try { 
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM messages WHERE isAssociation = ?");
             statement.setString(1, loggedInAssociationId);
             ResultSet result = statement.executeQuery();

          while (result.next()) {
            int id = result.getInt("idMessage");
            String senderId = result.getString("idUser");
            String receiverId = result.getString("isAssociation");
            String text = result.getString("message");
            Timestamp timestamp = result.getTimestamp("time");
            Association receiverAssociation = as.getById(Integer.parseInt(receiverId));
            Message message = new Message(id, senderId, receiverAssociation, text, timestamp);
            
            messages.add(message);
          }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }

        return messages;
      }
    
    public static void displayMessageBySender(int id){
         System.out.println("------------Messages----------------: ");
  
    
            List<Message> messages = retrieveAll(String.valueOf(id));
        Map<String, List<Message>> messagesByUser =  messages.stream()
        .collect(Collectors.groupingBy(Message::getSenderId, Collectors.toList()));
                
            for (Map.Entry<String, List<Message>> entry : messagesByUser.entrySet()) {
                System.out.println("Messages from user " + entry.getKey());
                List<Message> userMessages = entry.getValue();
                userMessages.sort(Comparator.comparing(Message::getTime));
                for (Message message : userMessages) {
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    System.out.println(message.getText()+"     "+timeFormat.format(message.getTime()));
                }
        }
    }
    
    public static void sendAssociationMessage(String id ) {
        Connection conn = MyConnection.getInstance().getConnection();
        Scanner scanner = new Scanner(System.in);
        AssociationServices as = new AssociationServices();

        System.out.print("Enter the ID of the association you want to send a message to: ");
        String associationId = scanner.next();

        // Retrieve the association with the specified ID
        Association association = as.getById(Integer.parseInt(associationId));
        System.out.println(association);
        System.out.println(association.getId());
       
        System.out.print("Enter your message: ");
        
        scanner.nextLine(); // clear scanner buffer
        String messageText = scanner.nextLine();

        // Create a new message and send it to the association
        Message message = new Message(id, association, messageText,new Timestamp(System.currentTimeMillis()));
        
        
        try{
            String sql = "INSERT INTO messages (idUser,isAssociation,message,time)values (?,?,?,?)";     
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, Integer.parseInt(message.getSenderId()));
            pr.setInt(2,association.getId());
            pr.setString(3, message.getText());
            pr.setTimestamp(4, message.getTime());
            pr.executeUpdate();
            
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
        }
    }

    
    
    
        
    

    }