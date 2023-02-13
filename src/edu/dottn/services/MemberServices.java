/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author WALID
 */
public class MemberServices implements UServices<User> {

    Connection cnx = MyConnection.getInstance().getConnection();

    @Override
    public void ajouterUser(User t) {
        try {

            String req = "INSERT INTO `user`(`name`, `address`, `email`, `password`, `numero`, `role`, `credit`, `salt`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getName());
            ps.setString(2, t.getAddress());
            ps.setString(3, t.getEmail());

            byte[] salt = getSalt();
            
            ps.setString(4, hashPassword(t.getPassword(), salt));

            ps.setInt(5, t.getNumero());
            ps.setString(6, t.getRole());
            ps.setInt(7, t.getCredit());
            ps.setString(8, Base64.getEncoder().encodeToString(salt));
            ps.executeUpdate();
            System.out.println("User added");
        } catch (SQLException | NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }
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
        try {
            String req = "UPDATE `user` SET `name`=?,`address`=?,`email`=?,`password`=?,`numero`=?,`role`=?,`credit`=? WHERE idUser = " + t.getIdUser();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getName());
            ps.setString(2, t.getAddress());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPassword());
            ps.setInt(5, t.getNumero());
            ps.setString(6, t.getRole());
            ps.setInt(7, t.getCredit());
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
                p = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
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
                User p = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getNString(7), rs.getInt(8));
                result.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return result;
    }

    @Override
    public User authenticateUser(User t) {
        User p = null;

        try {
            String req = "SELECT * FROM `user` WHERE email = '" + t.getEmail() + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String attemptedPassword = hashPassword(t.getPassword(), Base64.getDecoder().decode(rs.getString(9)));

                if (rs.getString(5).equals(attemptedPassword)) {
                    p = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
                    req = "INSERT INTO `sessionuser`(`id`, `username`, `useradresse`, `emailuser`, `userpassword`, `usernumero`, `userole`, `usercredit`, `usersalt`) VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setInt(1, p.getIdUser());
                    ps.setString(2, p.getName());
                    ps.setString(3, p.getAddress());
                    ps.setString(4, p.getEmail());
                    ps.setString(5, p.getPassword());
                    ps.setInt(6, p.getNumero());
                    ps.setString(7, p.getRole());
                    ps.setInt(8, p.getCredit());
                    ps.setString(9, rs.getString(9));
                    ps.executeUpdate();
                    System.out.println("successful authentication");
                } else {
                    System.out.println("failed authentication");

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public User verifSession() {
        User p = null;

        try {
            String req = "SELECT * FROM `sessionuser`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            if (rs.next()) {
                p = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
                System.out.println("Welcome " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public void logOut(int id) {
        String req = "DELETE FROM `sessionuser` WHERE id = " + id;

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
