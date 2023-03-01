package edu.dottn.services;

import edu.dottn.entities.Association;

import edu.dottn.entities.user;
import static edu.dottn.services.MessageServices.displayMessageBySender;
import edu.dottn.util.MyConnection;


import static edu.dottn.services.MessageServices.displayMessageBySender;
import edu.dottn.util.MyConnection;


import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AssociationServices implements IController<Association> {

    Scanner scanner = new Scanner(System.in);
    MyConnection cnx = MyConnection.getInstance();
    List L = new ArrayList();

    
    public boolean Signup(Association a ) {

        Connection conn = cnx.getConnection();

       
        String assocName = a.getAssocName();
        
        String username = a.getUsername();
        try {
            String sql = "SELECT username FROM association where username=?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, username);
            ResultSet result = pr.executeQuery();
            if (result.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username already exists");
                alert.setContentText("Please choose a different username");
                alert.showAndWait();
                return false;
    }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }

        String password;
            
        password = a.getPassword();
 
        
        String email = a.getEmail();
        
        String location = a.getLocation();
       
        int number = a.getNumber();

        System.out.println("Inserting a new association into the table...");

        // Hash the password using bcrypt
        
        
        try {
            byte[] salt = getSalt();
            String role = "association";
            String hashedPassword = hashPassword(password, salt);
            String sql = "INSERT INTO association (role ,assoName, username, password ,email ,assoAddress,assoPhone,salt) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, role);
            pr.setString(2, assocName);
            pr.setString(3, username);
            pr.setString(4, hashedPassword);
            pr.setString(5, email);
            pr.setString(6, location);
            pr.setInt(7, number);
            pr.setString(8, Base64.getEncoder().encodeToString(salt));
            pr.executeUpdate();
            System.out.println("Association ajoutee avec succees");
        } catch (SQLException | NoSuchAlgorithmException sqlEx) {
            System.out.println(sqlEx.getMessage());
            return false;
        }
        return true;
    }
 
    private static String hashPassword(String password, byte[] salt) {
          try {
            int iterations = 10000;
            char[] chars = password.toCharArray();
            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
          } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
          }
        }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    
    @Override
    public void delete(int id) {
        Connection conn = cnx.getConnection();
        try{
            String req ="DELETE from association where idAssociation = ?";
            String req1 ="ALTER TABLE association AUTO_INCREMENT = 1"; 
            PreparedStatement pr = conn.prepareStatement(req);
            PreparedStatement pr1 = conn.prepareStatement(req1);
            pr.setInt(1,id);
            pr.executeUpdate();
            pr1.executeUpdate();
            System.out.println("Association with id = "+id+" was deleted now !");         
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
            }
    }
    
    

    
    @Override
    public Association Signin(String username,String password) {
        Connection conn = cnx.getConnection();
//        System.out.println("Enter your username = ");
//        String username  = scanner.nextLine();
//        System.out.println("Enter your password = ");
//        String password  = scanner.nextLine();
        
        try {
        String sql = "SELECT * FROM association WHERE username = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String storedPasswordHash = result.getString("password");
            String Salt = result.getString("salt");
            byte[] storedSalt = Base64.getDecoder().decode(Salt);
            
            String enteredPasswordHash = hashPassword(password, storedSalt);
           
            
            if (storedPasswordHash.equals(enteredPasswordHash)) {
                Association loggedInAssociation = new Association();
                loggedInAssociation.setId(result.getInt("idAssociation"));
                loggedInAssociation.setAssocName(result.getString("AssoName"));
                
                String insertSql = "INSERT INTO session (idUser, loginTime) VALUES (?,?)";
                PreparedStatement insertStatement = conn.prepareStatement(insertSql);
                insertStatement.setInt(1, loggedInAssociation.getId());
                insertStatement.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
                insertStatement.executeUpdate();
                   
                System.out.println("Welcome to TrocTn "+ loggedInAssociation.getAssocName());
                return loggedInAssociation;
            } else {
                System.out.println("Incorrect password");
            }
        } else {
            System.out.println("Association not found");
        }
    } catch (SQLException sqlEx) {
        System.out.println(sqlEx.getMessage());
    }
       
        return null;
    }
        
    
   @Override
   public void update(Association association) {
    Connection conn = cnx.getConnection();
    
    // Initialize update query
    String updateQuery = "UPDATE association SET ";

    // Check if Association Name is provided and add to update query
    if (association.getAssocName() != null) {
        updateQuery += "assoName = ?, ";
    }

    // Check if username is provided and add to update query
    if (!association.getUsername().isEmpty()) {
        updateQuery += "username = ?, ";
    }

    // Check if email is provided and add to update query
    if (!association.getEmail().isEmpty()) {
        updateQuery += "email = ?, ";
    }

    // Check if location is provided and add to update query
    if (association.getLocation() != null) {
        updateQuery += "assoAddress = ?, ";
    }

    // Check if number is provided and add to update query
    if (association.getNumber() != 0) {
        updateQuery += "assoPhone = ?, ";
    }
    
    if (!association.getImage().isEmpty()) {
        updateQuery += "image = ?, ";
    }

    // Remove trailing comma and space from update query
    updateQuery = updateQuery.substring(0, updateQuery.length() - 2);
    updateQuery += " WHERE idAssociation = ?";

    try (PreparedStatement pr = conn.prepareStatement(updateQuery)) {
        int i = 1;
        if (association.getAssocName() != null) {
            pr.setString(i++, association.getAssocName());
        }

        if (!association.getUsername().isEmpty()) {
            pr.setString(i++, association.getUsername());
        }

        if (!association.getEmail().isEmpty()) {
            pr.setString(i++, association.getEmail());
        }

        if (association.getLocation() != null) {
            pr.setString(i++, association.getLocation());
        }

        if (association.getNumber() != 0) {
            pr.setInt(i++, association.getNumber());
        }
        
        if (!association.getImage().isEmpty()) {
            pr.setString(i++, association.getImage());
        }

        pr.setInt(i++, association.getId());

        int result = pr.executeUpdate();
        if (result > 0) {
            System.out.println("Association updated successfully");
        } else {
            System.out.println("Association not updated. No rows affected.");
        }
    } catch (SQLException sqlEx) {
        System.out.println(sqlEx.getMessage());
    }
}

   
    
   public List<Association> getAll(){
       Connection conn = cnx.getConnection();
       try{
           String sql ="SELECT * FROM association";
           PreparedStatement pr =  conn.prepareStatement(sql);
           ResultSet result = pr.executeQuery();
           while (result.next()){
               Association a = new Association(result.getString("assoName"),result.getString("username"),result.getString("email"),result.getString("assoAddress"),result.getInt("assoPhone"));
               L.add(a);
           }
           
       }catch(SQLException sqlEx){
           System.out.println(sqlEx.getMessage());
           System.out.println("Aucune associaiton est trouvee !");
       }
       return L;
   }
   
   
   public Association getById(int id ){
       Connection conn = cnx.getConnection();
       try{
           String sql ="SELECT * FROM association WHERE idAssociation=?";
           PreparedStatement pr =  conn.prepareStatement(sql);
           pr.setInt(1, id);
           ResultSet result = pr.executeQuery();
           while (result.next()){
               Association a = new Association(result.getInt("idAssociation"),result.getString("assoName"),result.getString("username"),result.getString("email"),result.getString("assoAddress"),result.getInt("assoPhone"));
               return a;
           }
           
       }catch(SQLException sqlEx){
           System.out.println(sqlEx.getMessage());
           
       }
       System.out.println("Aucune associaiton est trouvee !");
       return null ;
       
   }
   
   public void runAssociationApp(){
       AssociationServices as = new AssociationServices();
        Scanner sc = new Scanner(System.in);
        Connection conn =MyConnection.getInstance().getConnection();
        Association loggedInAssociation = null;

        loggedInAssociation= getLoggedInAssociation();
        

            try  {
                String selectSessionSql = "SELECT * FROM session";
                PreparedStatement selectSessionStatement = conn.prepareStatement(selectSessionSql);
                ResultSet sessionResult = selectSessionStatement.executeQuery();
                if (sessionResult.next()) {
                    String selectAssociationSql = "SELECT * FROM association WHERE idAssociation = ?";
                    PreparedStatement selectAssociationStatement = conn.prepareStatement(selectAssociationSql);
                    selectAssociationStatement.setInt(1, sessionResult.getInt("idUser"));
                    ResultSet associationResult = selectAssociationStatement.executeQuery();
                    if (associationResult.next()) {
                        loggedInAssociation = new Association();
                        loggedInAssociation.setId(associationResult.getInt("idAssociation"));
                        loggedInAssociation.setUsername(associationResult.getString("username"));
                        loggedInAssociation.setAssocName(associationResult.getString("assoName"));
                        loggedInAssociation.setEmail(associationResult.getString("email"));
                        loggedInAssociation.setLocation(associationResult.getString("assoAddress"));
                        loggedInAssociation.setNumber(associationResult.getInt("assoPhone"));
                        
                    }
                    
                } 
            } catch (SQLException sqlEX ) {
                System.out.println("No session present for now please Login: ");
            }


            if (loggedInAssociation != null) {
                System.out.println("Welcome back, " + loggedInAssociation.getAssocName());
                
               
               System.out.println("Choose 1 to Update your informations.");
               System.out.println("Choose 2 to check your messages.");
                System.out.println("Choose 3 to Logout from your session.");
                int choice = sc.nextInt();
                if(choice ==3){
                    
                    int id = loggedInAssociation.getId();
                     try {
                        String clearSession = "DELETE FROM session WHERE idUser = ?";
                        PreparedStatement statement = conn.prepareStatement(clearSession);
                        statement.setInt(1, id);
                        statement.executeUpdate();
                        loggedInAssociation = null;
                        
            } catch (SQLException sqlEx) {
                System.out.println("Error clearing session: " + sqlEx.getMessage());
            }

                    System.out.println("You have been logged out.");
                    
                }else if (choice ==1){
                     //as.update(loggedInAssociation.getId());
                }else if(choice ==2){
                displayMessageBySender(loggedInAssociation.getId());
            }else{
                    System.out.println("Incorrect choice !");
                }
               
            }
   }
   

   
   public Association getLoggedInAssociation() {
       Connection conn =MyConnection.getInstance().getConnection();
    Association loggedInAssociation = null;
    try  {
        String selectSessionSql = "SELECT * FROM session";
        PreparedStatement selectSessionStatement = conn.prepareStatement(selectSessionSql);
        ResultSet sessionResult = selectSessionStatement.executeQuery();
        if (sessionResult.next()) {
            String selectAssociationSql = "SELECT * FROM association WHERE idAssociation = ?";
            PreparedStatement selectAssociationStatement = conn.prepareStatement(selectAssociationSql);
            selectAssociationStatement.setInt(1, sessionResult.getInt("idUser"));
            ResultSet associationResult = selectAssociationStatement.executeQuery();
            if (associationResult.next()) {
                loggedInAssociation = new Association();
                loggedInAssociation.setId(associationResult.getInt("idAssociation"));
                loggedInAssociation.setUsername(associationResult.getString("username"));
                loggedInAssociation.setAssocName(associationResult.getString("assoName"));
                loggedInAssociation.setEmail(associationResult.getString("email"));
                loggedInAssociation.setLocation(associationResult.getString("assoAddress"));
                loggedInAssociation.setPassword(associationResult.getString("password"));
                loggedInAssociation.setNumber(associationResult.getInt("assoPhone"));
                loggedInAssociation.setImage(associationResult.getString("image"));
            }
        } 
    } catch (SQLException sqlEX ) {
        System.out.println("No session present for now please Login: ");
    }
    return loggedInAssociation;
}

   
   

   public boolean logoutAssociation (){
       Connection conn =MyConnection.getInstance().getConnection();
       try {
           String clearSession = "SELECT * FROM session";
           PreparedStatement statement = conn.prepareStatement(clearSession);             
           ResultSet rs = statement.executeQuery();  
           
           if(rs.next()){
               try {
                 String req = "DELETE FROM session WHERE idUser = ?";
                 PreparedStatement pr = conn.prepareStatement(req);
                        pr.setInt(1, rs.getInt("idUser"));
                        pr.executeUpdate();   
                        return true;
            } catch (SQLException sqlEx) {
                System.out.println("Error clearing session: " + sqlEx.getMessage());
            }
           }
       }catch (SQLException sqlEx) {
                System.out.println("Error clearing session: " + sqlEx.getMessage());
            }
       
          return false;         
   }
   

      public user getOneById(int id) {
          Connection conn = cnx.getConnection();
        user p = null;
        try {
            String req = "SELECT * FROM `user` WHERE idUser = " + id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              
                   p = new user(rs.getInt(1), rs.getString(2));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
   

    
}

