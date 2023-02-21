/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Admin;
import edu.dottn.entities.Member;
import edu.dottn.entities.User;

import edu.dottn.util.MyConnection;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author WALID
 */
public class MemberServices implements UServices<User> {

    Connection cnx = MyConnection.getInstance().getConnection();

    @Override
    public boolean ajouterUser(User t) {
        Member p = (Member)t;
        boolean add = false;
        try {
            // on va chercher si membre existe deja dans la base de donneés
            String req = "SELECT * FROM `user` WHERE email = '" + p.getEmail() + "'";
            Statement stt = cnx.createStatement();
            ResultSet rss = stt.executeQuery(req);
           if(!rss.next()){
               // if n'existe pas on va l'insérer
            req = "INSERT INTO `user`(`name`, `address`, `email`, `password`, `numero`, `role`, `credit`, `salt`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2, p.getAddress());
            ps.setString(3, p.getEmail());

            byte[] salt = getSalt();
         // cryptage de mot de passe
            ps.setString(4, hashPassword(p.getPassword(), salt));
            ps.setInt(5, p.getNumero());
            ps.setString(6, "Member");
            ps.setInt(7, p.getCredit());
            ps.setString(8, Base64.getEncoder().encodeToString(salt));
            int n = ps.executeUpdate();
            System.out.println("Member added");
                    if (n==1){
                        add = true;
                             }
           }
           else{
               Alert email = new Alert(Alert.AlertType.ERROR, "email exist ", ButtonType.CLOSE);
                            email.showAndWait();
           }
            
        } catch (SQLException | NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }
        return add;
    }

    private String hashPassword(String password, byte[] salt) {
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

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    @Override
    public void deleteUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE idUser = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void modifierUser(User t) {
        Member p = (Member)t;
        try {
            String req = "UPDATE `user` SET `name`=?,`address`=?,`email`=?,`password`=?,`numero`=?,`role`=?,`credit`=? WHERE idUser = " + t.getIdUser();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2, p.getAddress());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getPassword());
            ps.setInt(5, p.getNumero());
            ps.setString(6, "Member");
            ps.setInt(7, p.getCredit());
            ps.executeUpdate();
            System.out.println("User updated");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public User getOneById(int id) {
        User p = null;
        try {
            String req = "SELECT * FROM `user` WHERE idUser = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if(rs.getString(7).equals("Member")){
                   p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8));
                }
                else{
                   p = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<User> getAllById() {
        List<User> result = new ArrayList();
        try {
            String req = "Select * from `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 if(rs.getString(7).equals("Member")){
                User p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8));
                result.add(p);
                 }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return result;
    }

    
    public User authenticateUser(String email,String password) {
        User p = null;

        try {
            // chercher et recuperer utlisateur par email
            String req = "SELECT * FROM `user` WHERE email = '" + email + "'";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            //si utlisateur existe 
            while (rs.next()) {
                String attemptedPassword = hashPassword(password, Base64.getDecoder().decode(rs.getString(9)));
                // tester le password crypté dans la base de données avec le password saisie par user
                if (rs.getString(5).equals(attemptedPassword)) {
                    if(rs.getString(7).equals("Member")){
                    p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8));
                    }
                    else{
                    p = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));

                    }
                    // ajouter session dans la DB si le password est correcte
                     addSession(p.getIdUser());
                    System.out.println("successful authentication");
                } else {
                    Alert passwordmsg = new Alert(Alert.AlertType.ERROR, "password incorrect", ButtonType.CLOSE);
                    passwordmsg.showAndWait();

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }


    public void addSession(int idUser) throws SQLException{
        String req = "INSERT INTO `session` (`idUser`, `loginTime`) VALUES (?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,idUser);
        ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
        ps.executeUpdate();
    }
// tester si l'utlisateur ne quitte pas son compte
    public User verifSession() {
        User p = null;
        try {
            String req = "SELECT * FROM `session`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()){
                
                p = getOneById(rs.getInt(2));
                System.out.println("Welcome " + p.getName());
                
                
            } else {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return p;
    }

    public void logOut(int id) {
        //on va fermer la session pour l'utilisateur par idUser
        String req = "DELETE FROM `session` WHERE idUser = " + id;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
// chercher l'utlisateur avec le nom passer en parametre

    public List<User> findUserByNom(String name) {

        List<User> result = getAllById().stream().filter((p) -> p.getName().equals(name))
                .collect(Collectors.toList());

        return result;
    }
    public TreeSet<User> SortUserByNom() {

        TreeSet<User> users = getAllById().stream().collect(Collectors
                .toCollection(()->new TreeSet<User>((a,b)->a.getName().compareTo(b.getName()))));

        return users;
    }

}
