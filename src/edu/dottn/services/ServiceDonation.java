/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Donation;
import edu.dottn.entities.Donation.DonationStatus;
import edu.dottn.entities.User;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rajhi
 */
public class ServiceDonation implements DService<Donation> {

    Connection cnx = MyConnection.getInstance().getConnection();
//Ajout avec verification

    @Override
    public void ajouter(Donation d) {
        // Vérifier que les champs ne sont pas nuls
        if (d.getEtatDonation().toString().isEmpty()) {
            System.out.println("Error : fields are null");
            return;
        }

        try {
            String insertReq = "INSERT INTO donation (idUser,idAssociation,ID_Product,idPost, status) VALUES (?,?,?,?,?)";
            PreparedStatement stInsert = cnx.prepareStatement(insertReq);
            stInsert.setInt(1, d.getUser().getIdUser());
            stInsert.setInt(2, d.getAssociation().getId());
            stInsert.setInt(3, d.getProduct().getId());
            stInsert.setInt(4, d.getPost().getIdPost());
            stInsert.setString(5, d.getEtatDonation().toString());
            stInsert.executeUpdate();
            System.out.println("DONATION ADDED. THANK YOU!");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
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

    @Override
    public List<Donation> getAll() {
        List<Donation> list = new ArrayList<>();
        try {
            String req = "Select * from donation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Donation d = new Donation();
                d.setDateDonation(rs.getTimestamp("date_donation"));
                try {
                    d.setEtatDonation(Donation.DonationStatus.valueOf(rs.getString("status")));
                } catch (IllegalArgumentException e) {
                    // handle the case where the value returned by rs.getString("status") is not a valid enum constant
                }
                list.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
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

    public  void envoyer(User user) throws MessagingException {

        String username = "emna.rajhi@esprit.tn";
        String password = "223AFT1684";
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = prepareMessage(session, username, user.getEmail(), user.getName());
        Transport.send(message);
        System.out.println("Message envoyé !!");
    }
    

    private static Message prepareMessage(Session session, String username, String userEmail, String userName) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Merci pour votre donation !");
            message.setText("Bonjour " + userName + ",\n\n"
                + "Nous tenions à vous remercier pour votre généreuse donation  "
                + "Grâce à votre soutien, nous pouvons continuer à aider ceux qui en ont le plus besoin. "
                + "Nous apprécions vraiment votre contribution à notre cause.\n\n"
                + "Cordialement,\n"
                + "L'équipe de notre organisation");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    

    public void confirmDonation(Donation donation) {
        try {
            // Confirm the donation and update the database accordingly
            donation.setEtatDonation(DonationStatus.ACCEPTED);
            String req = "UPDATE donation SET status = ? WHERE idDonation = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, donation.getEtatDonation().toString());
            st.setInt(2, donation.getIdDon());
            st.executeUpdate();

            // Send the thank-you email
            User user = donation.getUser();
            try {
                envoyer(user);
            } catch (MessagingException ex) {
                Logger.getLogger(ServiceDonation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
