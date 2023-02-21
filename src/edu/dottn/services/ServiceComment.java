/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Comment;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ServiceComment implements CService<Comment> {

    private Connection cnx = MyConnection.getInstance().getConnection();
    private List<Integer> bannedUserIds = new ArrayList<>();

    public void banUser(int idUser) {
        // CS verifier si le id user existe déja
        if (idUser <= 0) {
            System.out.println("Invalid user ID");
            return;
        }
        bannedUserIds.add(idUser);
    }

    private boolean isOffensive(String content) {
        String[] offensiveWords = {"xxx", "xxxx", "xxxxx"};
        for (String word : offensiveWords) {
            if (content.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override

    public void ajouterComment(Comment c) {
        // Verify if the comment is not empty
        if (c.getContenu().isEmpty()) {
            System.out.println("Le contenu du commentaire ne peut pas être vide");
            return;
        }

        // Check for offensive content
        if (isOffensive(c.getContenu())) {
            System.out.println("Le commentaire contient du contenu offensive et ne peut pas être posté");
            return;
        }

        // Insert comment into database
        String sql = "INSERT INTO comment (Contenu,dateComment) VALUES (?,?)";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, c.getContenu());
            statement.setTimestamp(2, c.getDateComment());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Comment added!");
            } else {
                System.out.println("Error while adding Comment");
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void supprimerParId(int id) {
        // verifier l'id userr
        if (id <= 0) {
            System.out.println("Invalid comment ID");
            return;
        }
        try {
            String req = "DELETE FROM `comment` WHERE id_Comment = " + id;
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);
            if (rowsAffected > 0) {
                System.out.println("Comment Supprimé ");
            } else {
                System.out.println("Commentaire non Supprimé !");
            }
        } catch (SQLException ex) {
            System.err.println("Error while deleting post: " + ex.getMessage());
        }
    }

    @Override
    public Comment getOneById(int id) {
        if (id <= 0) {
            System.out.println("Invalid comment ID");
            return null;
        }
        Comment comment = null;
        try {
            String req = "SELECT * FROM comment WHERE id_comment = ?";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                comment = new Comment(rs.getString("Contenu"), rs.getTimestamp("DateComment"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du post avec l'id " + id + ": " + ex.getMessage());
        }

        return comment;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        try {
            String req = "SELECT * FROM comment WHERE idUser NOT IN (?)";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1, bannedUserIds.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Comment c = new Comment(rs.getString("Contenu"), rs.getTimestamp("dateComment"));
                comments.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de tous les posts: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Une erreur est survenue : " + ex.getMessage());
        }

        return comments;
    }
}
