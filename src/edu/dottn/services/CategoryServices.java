/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Category;
import edu.dottn.entities.SubCategory;
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
public class CategoryServices implements IservicesCategory<Category>{

     Connection cnx = MyConnection.getInstance().getConnection();
    List<Category> lc = new ArrayList();
    
    @Override
    public void addCategory(Category p) {
         try {
            PreparedStatement pr = cnx.prepareStatement("INSERT INTO `Category`(`name`) VALUES (?)");
            pr.setString(1, p.getName());
            pr.executeUpdate();
            System.out.println("Category added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void removeCategory(int id) {
          try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM Category WHERE ID_Category=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            if (result.next()) {
                pr = cnx.prepareStatement("DELETE FROM Category where ID_Category =?");
                pr.setInt(1, id);
                pr.executeUpdate();
                System.out.println("Category : " + id + " is deleted");
            } else {
                System.out.println("the Category doesn't exist in the db");
            }

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    @Override
    public void modifyCategory(Category p) {
          try {
            PreparedStatement pr = cnx.prepareStatement("UPDATE `Category` SET `name`=? WHERE ID_Category=?");
            pr.setString(1, p.getName());
            pr.setInt(2, p.getId());
            pr.executeUpdate();
            System.out.println("Category with id:  "+p.getId()+" has been modified ");
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
        };
    }

    @Override
    public Category getById(int id) {
          try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM Category WHERE ID_Category=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                return new Category(result.getInt(1), result.getString(2));
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
         try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Category");
            while (result.next()) {
                Category p = new Category(result.getInt(1), result.getString(2));
                lc.add(p);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return lc;
    }

   
     public List<Category> getByName(String name) {
          try {     
            lc=this.getAll().stream().filter(s->s.getName().contentEquals(name)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
        return lc;
    }
    
}
