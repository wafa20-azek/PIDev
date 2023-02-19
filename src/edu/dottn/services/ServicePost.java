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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                Post p = new Post(rs.getString("titlePost"), rs.getString("description"), rs.getTimestamp("date_created"));
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
                post = new Post(rs.getString("titlePost"), rs.getString("description"), rs.getTimestamp("date_created"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération du post avec l'id " + id + ": " + ex.getMessage());
        }

        return post;
    }

    public Post getRecentPost() {
        Post recentPost = null;
        try {
            String req = "SELECT * FROM post ORDER BY date_created DESC LIMIT 1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            List<Post> posts = Stream.of(rs)
                    .flatMap(resultSet -> {
                        try {
                            List<Post> resultList = new ArrayList<>();
                            while (resultSet.next()) {
                                resultList.add(new Post(
                                        resultSet.getString("title"),
                                        resultSet.getString("description"),
                                        resultSet.getTimestamp("date_created")
                                ));
                            }
                            return resultList.stream();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(Collectors.toList());

            if (!posts.isEmpty()) {
                recentPost = posts.get(0);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }
        return recentPost;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> posts = new ArrayList<>();
        List<Post> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM post";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                posts.add(new Post(rs.getString("titlePost"), rs.getString("description"), rs.getTimestamp("date_created")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur" + ex.getMessage());
        }

        result = posts.stream()
                .filter(p -> p.getTitlePost().contains(keyword) || p.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return result;
    }

    public List<Post> getAllPostsSortedByDate(boolean isAscending) {
        List<Post> posts = new ArrayList<>();
        try {
            String req = "SELECT * FROM post ORDER BY date_created " + (isAscending ? "ASC" : "DESC");
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String title = rs.getString("titlePost");
                String description = rs.getString("description");
                Timestamp dateCreated = rs.getTimestamp("date_created");
                Post post = new Post(title, description, dateCreated);
                posts.add(post);
                
            }
        } catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }

        return posts;
    }

}
