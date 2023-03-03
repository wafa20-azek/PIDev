/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Category;
import edu.dottn.entities.Product;
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
public class SubCategoryServices implements IservicesSubCategory<SubCategory>{
    
      Connection cnx = MyConnection.getInstance().getConnection();
    List lc = new ArrayList();

    @Override
    public void addSubCategory(SubCategory s) {

  try {
            PreparedStatement pr = cnx.prepareStatement("INSERT INTO `subcategory`(`name`,`ID_Category`) VALUES (?,?)");
            pr.setString(1, s.getName());
            pr.setInt(2, s.getCategory().getId());
            pr.executeUpdate();
            System.out.println("subcategory added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void removeSubCategory(int id) {
 try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM subcategory WHERE ID_SubCategory=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            if (result.next()) {
                pr = cnx.prepareStatement("DELETE FROM subcategory where ID_SubCategory =?");
                pr.setInt(1, id);
                pr.executeUpdate();
                System.out.println("subcategory : " + id + " is deleted");
            } else {
                System.out.println("the subcategory doesn't exist in the db");
            }

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
    }

    @Override
    public void modifySubCategory(SubCategory s) {
   try {
            PreparedStatement pr = cnx.prepareStatement("UPDATE `subcategory` SET `name`=? WHERE ID_SubCategory=?");
            pr.setString(1, s.getName());
            pr.setInt(2, s.getId());
            pr.executeUpdate();
            System.out.println("subcategory with id:  "+s.getId()+" has been modified ");
        }catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
        };
    }

    @Override
    public SubCategory getById(int id) {
  try {
            PreparedStatement pr = cnx.prepareStatement("SELECT * FROM subcategory WHERE ID_SubCategory=?");
            pr.setInt(1, id);
            ResultSet result = pr.executeQuery();
            while (result.next()) {
                return new SubCategory(result.getInt(1), result.getString(2),result.getInt(3));
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return null;
    }

    @Override
    public List<SubCategory> getAll() {
  try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM subcategory");
            while (result.next()) {
                SubCategory p = new SubCategory(result.getInt(1), result.getString(2),result.getInt(3));
                lc.add(p);
            }
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
        }
        return lc;
    }
    
     public List<SubCategory> getAllByIdCategory(int id) {
  try {     
            lc=this.getAll().stream().filter(s->s.getCategory().getId()==id).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lc;
    }
     
    public List<SubCategory> getByName(String name) {
         try {     
            lc=this.getAll().stream().filter(s->s.getName().contains(name)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lc;
    }
}
