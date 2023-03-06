/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;
import edu.dottn.entities.Reclamation;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ProInfo
 */
public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx = MyConnection.getInstance().getConnection();

    @Override
    public void ajouter(Reclamation t) {
        try {
            String req = "INSERT INTO `reclamation`(`Customer`,  `status`, `Description`, `Date`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getCustomer().getIdUser());
            ps.setString(2, t.getStatus());
            ps.setString(3, t.getDescription());
            ps.setTimestamp(4, t.getDateRec());
            ps.executeUpdate();
            System.out.println("row added ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM reclamation WHERE ID_Rec = "+id+" ";
            PreparedStatement st = cnx.prepareStatement(req);
         
            st.executeUpdate();
            System.out.println("row deleted");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Reclamation getOneById(int id) {
         Reclamation rec = null;
        try {
            String req = "SELECT  `ID_Rec`,`Customer`, `status`, `Description`, `Date` FROM `reclamation` WHERE ID_Rec = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                rec = new Reclamation(
                   result.getInt("ID_Rec"),     
                result.getInt("Customer"),
                result.getString("status"),
                result.getString("Description"),
                result.getTimestamp("Date"));
            }
            System.out.println("row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rec;
    }

    @Override
    public void modifier(Reclamation t) {
        try {
            String req ="UPDATE `reclamation` SET `Customer`= ?,`status`= ?,`Description`= ?,`Date`= ? WHERE ID_Rec = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,t.getCustomer().getIdUser());
            ps.setString(2, t.getStatus());
            ps.setString(3, t.getDescription());
            ps.setTimestamp(4, t.getDateRec());
            ps.setInt(5, t.getIdRec());
            ps.executeUpdate();
            System.out.println("row modified");
                    } catch (SQLException ex) {  
                    System.err.println(ex.getMessage());}
    }

    @Override
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations= new ArrayList<>();
        try {
            String req ="SELECT * FROM `reclamation`";
            PreparedStatement ps =cnx.prepareStatement(req);
            ResultSet result =ps.executeQuery();
            while (result.next()){
                Reclamation rec = new Reclamation(
                        result.getInt("ID_Rec"),
                        result.getInt("Customer"),
                        result.getString("status"),
                        result.getString("Description"),
                        result.getTimestamp("Date")
                      
                );
                reclamations.add(rec);
            }
            System.out.println("row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamations;
    }
    public List<Reclamation> myRec(int id){
           List<Reclamation> l=new ArrayList<>();
        try {
         
            String req="SELECT  `ID_Rec`,`Customer`,  `status`, `Description`, `Date` FROM `reclamation` WHERE `Customer` = "+id+" ";
                    
                    
            PreparedStatement ps=cnx.prepareStatement(req);
          
            ResultSet rs = ps.executeQuery(req);
            while (rs.next()){
                Reclamation r=new Reclamation(rs.getInt("ID_Rec"), rs.getInt("Customer"), rs.getString("status"), rs.getString("Description"), rs.getTimestamp("Date"));
                l.add(r);
                
            }
            System.out.println("rows pulled ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l ;
        
    }

}
