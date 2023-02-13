/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Avis_Offer;
import edu.dottn.entities.Offre;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bochr
 */
public class ServiceAvis implements AvisService<Avis_Offer> {

    Connection con = MyConnection.getInstance().getCon();

    @Override
    public void ajouterAvisOffer(Avis_Offer a) {
        try {
            String req = "INSERT INTO `offre`(`ID_Product`, `idUser`, `date_offre`) VALUES (?,?,?)";
            PreparedStatement as = con.prepareStatement(req);
            as.setInt(1, a.getID_Product());
            as.setInt(2, a.getIdUser());
            as.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            as.executeUpdate();
            System.out.println("avis added ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierAvisOffer(Avis_Offer a) {
        try {
            String req = "UPDATE `avis_offre` SET `id_offre`='" + a.getId_offer() + "',`ID_Product`='" + a.getID_Product() + "',`idUser`='" + a.getIdUser() + "',`ratting`='" + a.getRatting() + "',`description`='" + a.getDescription() + "' WHERE 1";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerAvisOffer(Avis_Offer a) {
        try {
            String req = "DELETE FROM `offre` WHERE  `id_offre` = " + a.getId_offer();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println(" Offer deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Avis_Offer getAvisOffeById(int id_offer) {
        Avis_Offer a = null;
        try {
            String req = "SELECT * FROM `offre` WHERE id_Offre = ";
            PreparedStatement st = con.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                a = new Avis_Offer(rs.getInt(3), rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5));
                System.out.println("Offer getted ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;

    }

}
