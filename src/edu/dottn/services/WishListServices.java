/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Wishlist;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author WALID
 */
public class WishListServices implements WLServices<Wishlist>{
     Connection cnx = MyConnection.getInstance().getConnection();
    @Override
    public void ajouter(Wishlist t) {
       String req = "INSERT INTO `wishlist`(`idUser`, `idProduct`) VALUES (?,?)";
       PreparedStatement ps;
         try {
             ps = cnx.prepareStatement(req);
             ps.setInt(1, t.getIdUser());
             ps.setInt(2, t.getIdProduct());
             ps.executeUpdate();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
            

    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `wishlist` WHERE id = " + id ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public Wishlist getOneById(int id) {
         Wishlist p = null;
        try {
            String req = "SELECT * FROM `wishlist` WHERE id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               p = new Wishlist(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    @Override
    public List<Wishlist> getAllById() {
        List<Wishlist> result = new ArrayList();
        try {
            String req = "Select * from `wishlist`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Wishlist p = new Wishlist(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                result.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return result;
    }
    
}
