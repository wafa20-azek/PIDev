/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;
import edu.dottn.entities.Reclamation;
import edu.dottn.util.DataSource;
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

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation t) {
        try {
            String req = "INSERT INTO `reclamation`(`Customer`, `Product`, `status`, `Description`, `Date`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getCustomer());
            ps.setInt(2, t.getProduct());
            ps.setString(3, t.getStatus());
            ps.setString(4, t.getDescription());
            ps.setTimestamp(5, t.getDateRec());
            ps.executeUpdate();
            System.out.println("row added ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM reclamation WHERE ID_Rec = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
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
            String req = "SELECT  `Customer`, `Product`, `status`, `Description`, `Date` FROM `reclamation` WHERE ID_Rec = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                rec = new Reclamation(
             
                result.getInt("Customer"),
                result.getInt("Product"),
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
            String req ="UPDATE `reclamation` SET `Customer`= ?,`Product`= ?,`status`= ?,`Description`= ?,`Date`= ? WHERE ID_Rec = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,t.getCustomer());
            ps.setInt(2, t.getProduct());
            ps.setString(3, t.getStatus());
            ps.setString(4, t.getDescription());
            ps.setTimestamp(5, t.getDateRec());
            ps.setInt(6, t.getIdRec());
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
                        result.getInt("ID_Req"),
                        result.getInt("Customer"),
                        result.getInt("Product"),
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

}
