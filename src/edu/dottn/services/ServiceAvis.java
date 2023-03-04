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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bochr
 */
public class ServiceAvis implements AvisService<Avis_Offer> {

    Connection con = MyConnection.getInstance().getConnection();

    @Override
    public void ajouterAvisOffer(Avis_Offer a) {
        try {
            String req = "INSERT INTO `avis_offre` (`id_offre`,`ID_Product`, `idUser`,`ratting`) VALUES (?,?,?,?)";
            PreparedStatement as = con.prepareStatement(req);
            as.setInt(1,a.getOffer().getId_Offre());
            as.setInt(2, a.getID_Product());
            as.setInt(3, a.getIdUser());
            as.setInt(4, a.getRatting());
//            as.setString(5, a.getDescription());
            as.executeUpdate();
            System.out.println("avis added ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierAvisOffer(Avis_Offer a) {
        try {
            String req = "UPDATE `avis_offre` SET `id_offre`='" + a.getOffer().getId_Offre()+ "',`ID_Product`='" + a.getID_Product() + "',`idUser`='" + a.getIdUser() + "',`ratting`='" + a.getRatting() + "' WHERE `avis_offre`= " + a.getIdavis() + "";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerAvisOffer(Avis_Offer a) {
        try {
            System.out.println(a);
            String req = "DELETE FROM `avis_offre` WHERE  `idavis` =" +a.getIdavis();
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println(" Avis deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    //sattement 3ala kol sttment nasn3 instance(ma3andish boucle)
    //f prepare statment k tabda 3andi boucle plus perfrmente que statement (3andi boucle mm temps )

    @Override
    public Avis_Offer getAvisOffeById(int idavis) {
        
        try {
            String req = "SELECT * FROM `avis_offre` WHERE idavis= " + idavis;
            PreparedStatement st = con.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               ServiceOffre so = new ServiceOffre();
                Offre offer = so.getOneById(rs.getInt(2));
                System.out.println("Avis getted ");
                return new Avis_Offer(rs.getInt(1), offer,rs.getInt(3),rs.getInt(3),rs.getInt(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
}
