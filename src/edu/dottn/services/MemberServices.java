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
import java.io.IOException;

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
import java.util.Random;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.UUID;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WALID
 */
public class MemberServices implements UServices<User> {

    Connection cnx = MyConnection.getInstance().getConnection();

    @Override
    public boolean ajouterUser(User t) {
        Member p = (Member) t;
        boolean add = false;
        try {
            // on va chercher si membre existe deja dans la base de donneés
            String req = "SELECT * FROM `user` WHERE email = '" + p.getEmail() + "'";
            Statement stt = cnx.createStatement();
            ResultSet rss = stt.executeQuery(req);
            if (!rss.next()) {
                // if n'existe pas on va l'insérer
                req = "INSERT INTO `user`(`name`, `address`, `email`, `password`, `numero`, `role`, `credit`, `salt`,`activation_code`,`activated`,`code`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
                // generate unique activation code
                String activationCode = UUID.randomUUID().toString();
                ps.setString(9, activationCode);
                ps.setBoolean(10, false);
                ps.setInt(11, 0);

                int n = ps.executeUpdate();
                System.out.println("Member added");
                if (n == 1) {
                    ActivationEmail.sendActivationEmail(p.getEmail(), activationCode);
                    add = true;
                }
            } else {
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

    // activate user account with activation code
    public boolean activateAccount(String activationCode) {
        boolean result = false;
        try {

            // select user information by activation code
            String req = "SELECT * FROM `user` WHERE activation_code = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, activationCode);

            // execute SQL statement to select user information by activation code
            ResultSet rs = ps.executeQuery();

            // if user information was found
            if (rs.next()) {
                // update user account activation status
                req = "UPDATE `user` SET `activated` = true WHERE activation_code = ?";
                ps = cnx.prepareStatement(req);
                ps.setString(1, activationCode);

                // execute SQL statement to update user account activation status
                int rowsUpdated = ps.executeUpdate();

                // check if user account activation status was updated
                if (rowsUpdated > 0) {
                    result = true;
                }
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "code incorrect", ButtonType.CLOSE);
                b.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
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
        Member p = (Member) t;
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

    public String UpdatePassword(int id, String NewPass, String OldPass, String Pass) {
        try {
            String salt = "";
            String req = "SELECT * FROM `user` WHERE idUser = '" + id + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                salt = rs.getString(9);
            }
            String attemptedPassword = hashPassword(Pass, Base64.getDecoder().decode(salt));
            if (OldPass.equals(attemptedPassword)) {
                return hashPassword(NewPass, Base64.getDecoder().decode(salt));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    @Override
    public User getOneById(int id) {
        User p = null;
        try {
            String req = "SELECT * FROM `user` WHERE idUser = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString(7).equals("Member")) {
                    p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8), rs.getBoolean(11));
                } else {
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
                if (rs.getString(7).equals("Member")) {
                    User p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8), rs.getBoolean(11));
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return result;
    }

    public User authenticateUser(String email, String password) {
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
                    if (rs.getBoolean(11)) {
                        if (rs.getString(7).equals("Member")) {
                            p = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(8), rs.getBoolean(11));
                        } else {
                            p = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));

                        }
                        // ajouter session dans la DB si le password est correcte
                        
                        System.out.println("successful authentication");
                    } else {
                        Alert activemsg = new Alert(Alert.AlertType.ERROR, "Le compte est desactivé", ButtonType.CLOSE);
                        activemsg.showAndWait();
                    }
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

  

    public void sendCodeAuth(String email, int number) {
        try {
            Random random = new Random();
            int code = random.nextInt(900000) + 100000;
            AuthenticationTwoFactor.TwoFactorSendVerification(number, code);
            String req = "UPDATE `user` SET `code`=? WHERE email = '" + email + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, code);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean verifCodeAuth(int code, int id) {
        try {
            String req = "SELECT * FROM `user` WHERE idUser = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("codeBase "+rs.getInt(12));
                if (rs.getInt(12) == code) {
                    return true;
                } else {
                    return false;

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void PasswordResetEmail(String email) {
        String password = generatePassword(8);
        try {
            // chercher et recuperer utlisateur par email
            String req = "SELECT * FROM `user` WHERE email = '" + email + "'";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            //si utlisateur existe 
            while (rs.next()) {
                String attemptedPassword = hashPassword(password, Base64.getDecoder().decode(rs.getString(9)));
                ForgetPassword.sendPasswordResetEmail(email, password);
                String requ = "UPDATE `user` SET `password` = ? WHERE salt = ?" ;
                PreparedStatement ps = cnx.prepareStatement(requ);
                ps.setString(1, attemptedPassword);
                ps.setString(2, rs.getString(9));
                
                ps.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static String generatePassword(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
// chercher l'utlisateur avec le nom passer en parametre

    public List<User> findUserByNom(String name) {

        List<User> result = getAllById().stream().filter((p) -> p.getName().contains(name))
                .collect(Collectors.toList());

        return result;
    }

    public TreeSet<User> SortUserByNom() {

        TreeSet<User> users = getAllById().stream().collect(Collectors
                .toCollection(() -> new TreeSet<User>((a, b) -> a.getName().compareTo(b.getName()))));

        return users;
    }
public void banUser(int id, boolean etat) {
        try {

            // update user account activation status
            String req = "UPDATE user SET activated = ? WHERE idUser = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setBoolean(1, etat);
            ps.setInt(2, id);

            // execute SQL statement to update user account activation status
            int rowsUpdated = ps.executeUpdate();

            // check if user account activation status was updated
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
