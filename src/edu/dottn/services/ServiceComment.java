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
import javafx.scene.control.ButtonType;

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

    public void ajouterComment(Comment c ,int idPost) {
        // Verify if the comment is not empty
        if (c.getContenu().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le contenu du commentaire ne peut pas être vide");
            alert.showAndWait();
            return;
        }
        // Check for offensive content
        if (isOffensive(c.getContenu())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Your comment has an offensive content !! ");
            alert.showAndWait();
            return;
        }

        // Insert comment into database
        String sql = "INSERT INTO comment (Contenu,dateComment,idPost) VALUES (?,?,?) ";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, c.getContenu());
            statement.setTimestamp(2, c.getDateComment());
            statement.setInt(3, idPost);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Comment Added");
                alert.setHeaderText("Your comment is added , thank you!");
                alert.showAndWait();
            } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error in adding your comment !");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void supprimerParId(int id) {
        // Vérifier l'id userr
        if (id <= 0) {
            Alert alert = new Alert(AlertType.ERROR, "ID de commentaire invalide !");
            alert.showAndWait();
            return;
        }
        try {
            String req = "DELETE FROM `comment` WHERE id_Comment = " + id;
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);
            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION, "Commentaire supprimé avec succès !");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, "Le commentaire n'a pas été supprimé !");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR, "Erreur lors de la suppression du commentaire: " + ex.getMessage());
            alert.showAndWait();
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

    public List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();
        try {
            String req = "SELECT * FROM comment WHERE idPost = ?";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setInt(1, postId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Comment c = new Comment(rs.getString("Contenu"), rs.getTimestamp("dateComment"));
                comments.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des commentaires pour le post avec l'id " + postId + ": " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Une erreur est survenue : " + ex.getMessage());
        }

        return comments;
    }

    public void supprimerCommentairesByUserId(int idUser) {
        // Vérifier si l'ID utilisateur est valide
        if (idUser <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "ID utilisateur invalide");
            alert.showAndWait();
            return;
        }
        try {
            String req = "DELETE FROM `comment` WHERE idUser = ?";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setInt(1, idUser);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Commentaires supprimés pour l'utilisateur avec l'ID " + idUser);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Aucun commentaire trouvé pour l'utilisateur avec l'ID " + idUser);
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la suppression des commentaires de l'utilisateur avec l'ID " + idUser + ": " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
