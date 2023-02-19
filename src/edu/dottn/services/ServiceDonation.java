/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Donation;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author rajhi
 */
public class ServiceDonation implements DService<Donation> {

    Connection cnx = MyConnection.getInstance().getConnection();
//Ajout avec verification

    public void ajouter(Donation d) {
        try {
            String insertReq = "INSERT INTO donation (idUser,idAssociation,ID_Product,idPost, status) VALUES (?,?,?,?,?)";
            PreparedStatement stInsert = cnx.prepareStatement(insertReq);
            stInsert.setInt(1, d.getIdUser());
            stInsert.setInt(2, d.getIdAssociation());
            stInsert.setInt(3, d.getIdProduct());
            stInsert.setInt(4, d.getPost().getIdPost());

            stInsert.setString(5, d.getEtatDonation().toString());
            stInsert.executeUpdate();
            System.out.println("Votre don a été ajouté !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du don : " + ex.getMessage());
        }
    }
// Méthode supprimer des dons 

    @Override
    public void supprimerParId(int id) {
        try {
            String req = "DELETE FROM donation WHERE idDon = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Donation supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du don : " + ex.getMessage());
        }
    }

    public List<Donation> getAll() {
        List<Donation> list = new ArrayList<>();
        try {
            String req = "Select * from donation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Donation d = new Donation();
                d.setDateDonation(rs.getTimestamp("date_donation"));
                d.setEtatDonation(Donation.DonationStatus.valueOf(rs.getString("status")));
                list.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Donation getOneById(int id) {
        Donation d = null;
        try {
            String req = "Select * from donation where idDon = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                d = new Donation();
                d.setDateDonation(rs.getTimestamp("date_donation"));
                d.setEtatDonation(Donation.DonationStatus.valueOf(rs.getString("status")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    public String thankyou() {
        final String THANK_YOU_MESSAGE = "Merci %s pour votre généreux don! Votre contribution aide vraiment à faire une différence.";
        String name = getCurrentUserName();
        return String.format(THANK_YOU_MESSAGE, name);
    }

    private static String getCurrentUserName() {
        return "utilisateur";
    }

/*public List<Integer> findTopDonors() {
    ServiceDonation serviceDonation = new ServiceDonation();
    List<Donation> donations = serviceDonation.getAll();
    int topCount = 5; // par exemple, on peut définir le nombre de top donateurs par défaut ici
    Map<Integer, Long> donationCounts = donations.stream()
        .collect(Collectors.groupingBy(Donation::getIdUser, Collectors.counting()));
    List<Integer> topDonors = donationCounts.entrySet().stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .limit(topCount)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
    return topDonors;
}
*/


}
