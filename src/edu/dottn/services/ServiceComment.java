/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Comment;
import edu.dottn.entities.Post;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rajhi
 */
public class ServiceComment implements CService<Comment> {

    Connection cnx = MyConnection.getInstance().getConnection();
    private List<Integer> bannedUserIds = new ArrayList<>();

    public void banUser(int idUser) {
        bannedUserIds.add(idUser);
    }

    private boolean isOffensive(String content) {
        String[] offensiveWords = {"xxxx", "xxxxx"};
        for (String word : offensiveWords) {
            if (content.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void ajouterComment(Comment c) {
        if (isOffensive(c.getContenu())) {
            System.out.println("Comment contains offensive content and cannot be posted");
            return;
        }

        String sql = "INSERT INTO comment (Contenu,dateComment) VALUES (?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, c.getContenu());
            statement.setTimestamp(2, c.getDateComment());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Comment added successfully");
            } else {
                System.out.println("Comment add failed");
            }
        } catch (SQLException ex) {
            System.err.println("Error while adding Comment : " + ex.getMessage());
        }
    }

    public void supprimerParId(int id) {
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
            String req = "SELECT * FROM comment WHERE username NOT IN (?)";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setString(1, bannedUserIds.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Comment c = new Comment(rs.getString("Contenu"), rs.getTimestamp("dateComment"));
                comments.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de tous les posts: " + ex.getMessage());
        }

        return comments;
    }

}
