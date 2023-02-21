/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Reclamation;
import edu.dottn.entities.Reponse;
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
public class ServiceReponse implements IService<Reponse>{
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Reponse t) {
        try {
            String req ="INSERT INTO `reponse`(`ID_Rec`, `Response`, `Date_Rep`) VALUES (?,?,?)";
            PreparedStatement ps= cnx.prepareStatement(req);
            ps.setInt(1, t.getReclamation().getIdRec());
            ps.setString(2, t.getRep());
            ps.setTimestamp(3, t.getDateRep());
            ps.executeUpdate();
            System.out.println("Reponse added ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req="DELETE FROM `reponse` WHERE Id_Rep = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reponse Deleted ");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public Reponse getOneById(int id) {
        Reponse rep = null;
        try {
            String req ="SELECT r.Description, rp.ID_Rec, rp.Response, rp.Date_Rep " +
                    "FROM reclamation r " +
                    "JOIN reponse rp ON r.ID_Rec = rp.ID_Rec " +
                    "WHERE rp.Id_Rep = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            System.out.println("roww pulled");
            if (result.next()){
                rep = new Reponse();
               rep.setRep(result.getString("Response"));
               rep.setDateRep(result.getTimestamp("Date_Rep"));
               Reclamation rec = new Reclamation(result.getString("Description"));
               rep.setReclamation(rec);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rep;
    }

    @Override
    public void modifier(Reponse t) {
        try {
            String req = "UPDATE `reponse` SET `ID_Rec`= ? ,`Response`= ? ,`Date_Rep`= ?  WHERE Id_Rep = ? ";
            PreparedStatement ps= cnx.prepareStatement(req);
            ps.setInt(1, t.getReclamation().getIdRec());
            ps.setString(2,t.getRep());
            ps.setTimestamp(3, t.getDateRep());
            ps.setInt(4, t.getIdRep());
            ps.executeUpdate();
            System.out.println("row updated");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Reponse> getAll() {
         List<Reponse> reponse= new ArrayList<>();
        try {
            String req ="SELECT  r.Description, rp.ID_Rec, rp.Response, rp.Date_Rep " +
                    "FROM reclamation r " +
                    "JOIN reponse rp ON r.ID_Rec = rp.ID_Rec ";
                    
            PreparedStatement ps =cnx.prepareStatement(req);
            ResultSet result =ps.executeQuery();
            while (result.next()){
                Reponse rep = new Reponse();
                
                rep.setRep(result.getString("Response"));
               rep.setDateRep(result.getTimestamp("Date_Rep"));
               Reclamation rec = new Reclamation(result.getString("Description"));
               rep.setReclamation(rec);
                reponse.add(rep);
            }
            System.out.println("row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponse;
       
    }
   
    
}
