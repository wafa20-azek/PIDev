/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Donation;
import edu.dottn.entities.Donation.DonationStatus;
import edu.dottn.entities.Post;
import edu.dottn.entities.User;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Authenticator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
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
            stInsert.setInt(1, d.getIdUser());
            stInsert.setInt(2, d.getIdAssociation());
            stInsert.setInt(3, d.getIdProduct());
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

    public void sendDonationThankYouEmail(User user, Donation donation) {
        // Adresse e-mail du destinataire
        String recipientEmail = user.getEmail();

        // Sujet du message
        String subject = "Merci pour votre donation !";

        // Corps du message
        String body = "Bonjour " + user.getName() + ",\n\n"
                + "Nous tenions à vous remercier pour votre généreuse donation  "
                + "Grâce à votre soutien, nous pouvons continuer à aider ceux qui en ont le plus besoin. "
                + "Nous apprécions vraiment votre contribution à notre cause.\n\n"
                + "Cordialement,\n"
                + "L'équipe de notre organisation";

        // Configurer les propriétés JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        String username = "emna.rajhi@esprit.tn";
        String password = "223AFT1684";

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer un objet de message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoyer le message
            Transport.send(message);

            System.out.println("E-mail sent " + recipientEmail);
        } catch (MessagingException ex) {
            System.err.println("Error " + ex.getMessage());
        }
    }

    public List<Donation> getDonationsSortedByEtatDonation(DonationStatus etatDonation) {
        List<Donation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM donation WHERE status = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, etatDonation.toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Donation d = new Donation();
                d.setDateDonation(rs.getTimestamp("date_donation"));
                d.setEtatDonation(DonationStatus.valueOf(rs.getString("status")));

                // Récupérer l'objet Post associé au don
                int idPost = rs.getInt("idPost");
                Post post = new ServicePost().getOneById(idPost);
                d.setPost(post);

                list.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Trier la liste des dons par date de donation décroissante
        List<Donation> sortedList = list.stream()
                .sorted(Comparator.comparing(Donation::getDateDonation).reversed())
                .collect(Collectors.toList());

        return sortedList;
    }

}
