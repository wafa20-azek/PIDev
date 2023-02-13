/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Offre;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * @author bochr
 */
//est ce que lazem naaml classe clandrier wala nn  wala ila comment ajouter ub=n date
// tout se qui est select nekhdmouh b exectuteQuery w exectute update nikhdmouha lil kol ajouter supprimer modifier
public class ServiceOffre implements Oservice<Offre> {

    Connection con = MyConnection.getInstance().getCon();
   
    @Override
    public void ajouterOffre(Offre o) {
        try {
            String req = "INSERT INTO `offre`(`ID_Product`, `idUser`, `date_offre`) VALUES (?,?,?)";
            PreparedStatement pso = con.prepareStatement(req);
//            Offre o = new Offre(pso.setInt(2,o.getID_Product()),pso.setInt(3,o.getIdUser()),pso.setDate(4,(Date) o.getDate_offre()));
            pso.setInt(1, o.getID_Product());
            pso.setInt(2, o.getIdUser());
            pso.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()) );
            pso.executeUpdate();
//          Statement of= con.createStatement();//statement tekho req sql w tbadalha b langage mtaa driver
//          of.executeUpdate(req);
            System.out.println("Offre Created !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerOffre(int id_Offre) {

        try {
            String req = "DELETE FROM `offre` WHERE  `id_offre` = "+ id_Offre ;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println(" Offer deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifierOffre(Offre o) {
        try {
            String req = "UPDATE `offre` SET `date_offre`='"+o.getDate_offre()+"',`status`='"+o.getStatus()+" ' WHERE 1";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    @Override
    public void AccepterOffre(Offre o) {
        try {
            String req = "SELECT * FROM `offre` WHERE 1 ";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void RefuserOffer(Offre o) {
        try {
            String req = "SELECT * FROM `offre` WHERE 1 "; 
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer Denied ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    
    public Offre  getOneById(int id_Offre) {
        Offre o =null;
        try{
            String req = "SELECT * FROM `offre` WHERE id_Offre = "  ;
            PreparedStatement st = con.prepareStatement(req);
             ResultSet rst= st.executeQuery(req);
              while(rst.next()){
             o=new Offre(rst.getInt(3),rst.getInt(1), rst.getInt(2), rst.getTimestamp(4),rst.getString(5));
            System.out.println("Offer getted ");}
        }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return o;
    }

    @Override
    public List<Offre> getAll() {
         List<Offre> list = new ArrayList<>();
        try {
            String req = "Select * from offre";
            Statement st = con.createStatement();
            ResultSet res= st.executeQuery(req);
            while(res.next()){
                Offre o = new Offre(res.getInt(3),res.getInt(1),res.getInt(2),res.getTimestamp(4),res.getString(5));
                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }


}
