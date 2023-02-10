/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Product;
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
 * @author WAFA
 */
public class ProductServices implements IservicesProduct<Product>{

    
     Connection cnx = MyConnection.getInstance().getConnection();
    ArrayList lp = new ArrayList();
    
    @Override
    public void addProduct(Product p) {

         try {
            PreparedStatement pr = cnx.prepareStatement("INSERT INTO `Product`(`name`, `description`, `image`, `price`) VALUES (?,?,?,?)");
            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            pr.setString(3, p.getImage());
            pr.setFloat(4, p.getPrice());
            pr.executeUpdate();
            System.out.println("Product added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

//        MyConnection.getInstance().closeConnection();
    }

    @Override
    public void removeProduct(int id) {
         try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM Product WHERE ID_Product=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            if (result.next()) {
                pr = cnx.prepareStatement("DELETE FROM Product where ID_Product =?");
                pr.setInt(1, id);
                pr.executeUpdate();
                System.out.println("Product : " + id + " is deleted");
            } else {
                System.out.println("the product doesn't exist in the db");
            }

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    @Override
    public void modifyProduct(Product p) {
        
         try {
            PreparedStatement pr = cnx.prepareStatement("UPDATE `Product` SET `name`=?,`description`=?, `image`=?, `price`=? WHERE ID_Product=?");
            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            pr.setString(3, p.getImage());
            pr.setFloat(4, p.getPrice());
            pr.setInt(5, p.getId());
            pr.executeUpdate();
            System.out.println("Product with id:  "+p.getId()+" has been modified ");
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
        };
    }

    @Override
    public Product getById(int id) {
         try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM Product WHERE ID_Product=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                return new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5));
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllById() {
        
        
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Product");
            while (result.next()) {
                Product p = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5));
                lp.add(p);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return lp;
    }
    
    
}
