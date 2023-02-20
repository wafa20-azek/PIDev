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
import java.util.stream.Collectors;

/**
 *
 * @author WAFA
 */
public class ProductServices implements IservicesProduct<Product>{

    
     Connection cnx = MyConnection.getInstance().getConnection();
    List<Product> lp = new ArrayList();
    
    @Override
    public void addProduct(Product p) {

         try {
            PreparedStatement pr = cnx.prepareStatement("INSERT INTO `Product`(`name`, `description`, `image`, `price`, `idsubcategory`,`iduser`) VALUES (?,?,?,?,?,?)");
            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            pr.setString(3, p.getImage());
            pr.setFloat(4, p.getPrice());
            pr.setInt(5,p.getSubCategory().getId() );
            pr.setInt(6,p.getIduser() );
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
            PreparedStatement pr = cnx.prepareStatement("UPDATE `Product` SET `name`=?,`description`=?, `image`=?, `price`=?, `idsubcategory`=?, `iduser`=? WHERE ID_Product=?");
            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            pr.setString(3, p.getImage());
            pr.setFloat(4, p.getPrice());
            pr.setInt(5,  p.getSubCategory().getId());
            pr.setInt(6, p.getIduser());
             pr.setInt(7, p.getId());
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
                return new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getInt(6), result.getInt(7));
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        
        
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Product");
            while (result.next()) {
                Product p = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getInt(6), result.getInt(7));
                lp.add(p);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return lp;
    }


    public List<Product> getByName(String name) {
         try {     
            lp=this.getAll().stream().filter(s->s.getName().contains(name)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lp;
    }
public List<Product> getByCategory(String category) {
         try {     
            lp=this.getAll().stream().filter(s->s.getSubCategory().getCategory().getName().contentEquals(category)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lp;
    }

    public List<Product> getBySubCategory(String subcategory) {
         try {     
            lp=this.getAll().stream().filter(s->s.getSubCategory().getName().contentEquals(subcategory)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lp;
    }
     public List<Product> getByIdUser(int iduser) {
         try {     
            lp=this.getAll().stream().filter(s->s.getIduser()==iduser).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lp;
    }
    
    
     public Product exist(Product p) {
        try {
            boolean b=false;
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM `Product` WHERE EXISTS (SELECT * FROM `Product` WHERE `name`=? AND `description`=? AND `image`=? AND `price`=? AND `idsubcategory`=? AND `iduser`=?)");
            pr.setString(1, p.getName());
            pr.setString(2, p.getDescription());
            pr.setString(3, p.getImage());
            pr.setFloat(4, p.getPrice());
            pr.setInt(5,  p.getSubCategory().getId());
            pr.setInt(6, p.getIduser());
             ResultSet result =pr.executeQuery();
             while (result.next()) {
               b=true;
            }
             if (b==true){
            System.out.println("Product already exists ");
            return p;
             }
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
        };
        return null;
    }
}
