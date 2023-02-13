/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

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
public class ServicePost implements PService<Post> {

    Connection cnx = MyConnection.getInstance().getConnection();

    public void ajouter(Post p) {
        String sql = "INSERT INTO post (titlePost, description) VALUES (?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, p.getTitlePost());
            statement.setString(2, p.getDescription());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Post added successfully");
            } else {
                System.out.println("Post add failed");
            }
        } catch (SQLException ex) {
            System.err.println("Error while adding post: " + ex.getMessage());
        }
    }

    public void supprimerParId(int idPost) {
        try {
            String req = "DELETE FROM `post` WHERE idPost = " + idPost;
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);
            if (rowsAffected > 0) {
                System.out.println("Post deleted");
            } else {
                System.out.println("Post delete failed");
            }
        } catch (SQLException ex) {
            System.err.println("Error while deleting post: " + ex.getMessage());
        }
    }

    public void modifier(Post p) {
        try {
            String req = "UPDATE `post` SET `titlePost` = ?, `description` = ? WHERE idPost=?";
            PreparedStatement statement = cnx.prepareStatement(req);
            
            statement.setString(1, p.getTitlePost());
            statement.setString(2, p.getDescription());
            statement.setInt(3, p.getIdPost());
            statement.executeUpdate();
            System.out.println("Post MODIFIÉ !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du post: " + ex.getMessage());
        }
    }

    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM post";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Post p = new Post(rs.getString("titlePost"), rs.getString("description"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération de tous les posts: " + ex.getMessage());
        }

        return list;
    }

    public Post getOneById(int id) {
        Post post = null;
        try {
            String req = "SELECT * FROM post WHERE idPost = ?";
            PreparedStatement statement = cnx.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                post = new Post(rs.getString("titlePost"), rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du post avec l'id " + id + ": " + ex.getMessage());
        }

        return post;
    }

    public List<Post> getPostsByAssociation(int idAssociation) {
        List<Post> posts = new ArrayList<>();
        // while (resultSet.next()) {
        //     int idPost = resultSet.getInt("id_post");
        //     int idAssociation = resultSet.getInt("id_association");
        //     String titlePost = resultSet.getString("title_post");
        //     String description = resultSet.getString("description");
        //     Date dateCreated = resultSet.getDate("date_created");
        //     Date dateUpdated = resultSet.getDate("date_updated");
        //     Post post = new Post(idPost, idAssociation, titlePost, description, dateCreated, dateUpdated);
        //     posts.add(post);    return posts;
        return posts;
    }

    public Post getRecentPost() {
        Post recentPost = null;
//yyyyyyyyyyyyyyyyy
        return recentPost;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> posts = new ArrayList<>();
        List<Post> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM post";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            for (Post p : posts) {
                if (p.getTitlePost().contains(keyword) || p.getDescription().contains(keyword)) {
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }

        return result;

    }

    public List<Post> getAllPostsSortedByDate(boolean isAscending) {
        List<Post> posts = new ArrayList<>();
        try {
            String req = "SELECT * FROM post";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (isAscending) {
                posts.sort((p1, p2) -> p1.getDate_created().compareTo(p2.getDate_created()));
            } else {
                posts.sort((p1, p2) -> p2.getDate_created().compareTo(p1.getDate_created()));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }

        return posts;
    }

}
