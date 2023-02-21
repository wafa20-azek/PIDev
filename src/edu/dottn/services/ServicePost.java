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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author rajhi
 */
public class ServicePost implements PService<Post> {

    Connection cnx = MyConnection.getInstance().getConnection();

    public void ajouter(Post p) {
        // Vérifier que les champs ne sont pas nuls
        if (p.getTitlePost().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing field");
            alert.setHeaderText(null);
            alert.setContentText("The title field must not be empty");
            alert.showAndWait();
            return;
        }
        if (p.getDescription().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Empty field");
            alert.setHeaderText(null);
            alert.setContentText("The description field must not be empty");
            alert.showAndWait();
            return;
        }

        // Vérifier que les champs respectent une longueur maximale
        int titleMaxLen = 50;
        int descMaxLen = 500;
        if (p.getTitlePost().length() > titleMaxLen) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("field too long");
            alert.setHeaderText(null);
            alert.setContentText("The title field must not exceed " + titleMaxLen + " characters");
            alert.showAndWait();
            return;
            
        }
        if (p.getDescription().length() > descMaxLen) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("field too long");
            alert.setHeaderText(null);
            alert.setContentText("The description field must not exceed  " + descMaxLen + " characters");
            alert.showAndWait();
            return;
        }

        String sql = "INSERT INTO post (titlePost, description) VALUES (?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(sql)) {
            statement.setString(1, p.getTitlePost());
            statement.setString(2, p.getDescription());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Post added ");
                alert.setHeaderText(null);
                alert.setContentText("The post was successfully added");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'ajout du post a échoué");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error adding post:" + ex.getMessage());
            alert.showAndWait();
        }
    }

    public void supprimerParId(int idPost) {
        // Vérifier que l'ID de l'article est valide
        if (idPost <= 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("invalid ID");
            alert.setHeaderText(null);
            alert.setContentText("Item ID must be a non-zero positive integer");
            alert.showAndWait();
            return;
        }

        try {
            String req = "DELETE FROM `post` WHERE idPost = " + idPost;
            Statement st = cnx.createStatement();
            int rowsAffected = st.executeUpdate(req);
            if (rowsAffected > 0) {
                System.out.println("Post Deleted !!");
            } else {
                System.out.println("Post delete failed");
            }
        } catch (SQLException ex) {
            System.err.println("Error while deleting post: " + ex.getMessage());
        }
    }

    public void modifier(Post p) {
        // Vérifier que les champs ne sont pas nuls
        if (p.getTitlePost().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champ titre invalide");
            alert.setHeaderText(null);
            alert.setContentText("The title field must not be empty");
            alert.showAndWait();
            return;
        }
        if (p.getDescription().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champ description invalide");
            alert.setHeaderText(null);
            alert.setContentText("The Description field must not be empty");
            alert.showAndWait();
            return;
        }

        try {
            String req = "UPDATE `post` SET `titlePost` = ?, `description` = ? WHERE idPost=?";
            PreparedStatement statement = cnx.prepareStatement(req);

            statement.setString(1, p.getTitlePost());
            statement.setString(2, p.getDescription());
            statement.setInt(3, p.getIdPost());
            statement.executeUpdate();
            System.out.println("Post edited!");
        } catch (SQLException ex) {
            System.out.println("Error editing post:" + ex.getMessage());
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
            System.out.println("Error retrieving all posts: " + ex.getMessage());
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
            System.out.println("Error retrieving post with l'id: " + id + ": " + ex.getMessage());
        }

        return post;
    }

    public Post getRecentPost() {
       
        Post recentPost = null;

        try {
            
            String req = "SELECT * FROM post ORDER BY date_created DESC LIMIT 1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            // Extraction des résultats de la requête SQL et conversion en une liste de Post
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

            // Si la liste de Post extraite n'est pas vide, le premier élément est le post le plus récent
            if (!posts.isEmpty()) {
                recentPost = posts.get(0);
            } else {
                // Si aucun post n'est trouvé, afficher une alerte
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("No Posts Found");
                alert.setHeaderText(null);
                alert.setContentText("No posts were found in the database.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            // Si une erreur se produit, afficher une alerte avec le message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while fetching the most recent post: " + ex.getMessage());
            alert.showAndWait();
        }
        // Retourner le post le plus récent ou null si aucun post n'est trouvé
        return recentPost;
}


    public List<Post> searchPostsByKeyword(String keyword) {
    List<Post> posts = new ArrayList<>();  // Créer une liste vide pour stocker les objets Post
    List<Post> result = new ArrayList<>();  // Créer une autre liste vide pour stocker les résultats de la recherche
    try {
        String req = "SELECT * FROM post";  
        Statement st = cnx.createStatement();  
        ResultSet rs = st.executeQuery(req);  
        while (rs.next()) {  
            posts.add(new Post(rs.getString("titlePost"), rs.getString("description"), rs.getTimestamp("date_created")));
        }
    } catch (SQLException ex) {
        System.out.println("Erreur" + ex.getMessage());  // 
    }

    result = posts.stream()  // Utiliser Stream pour traiter les objets Post de la liste "posts"
            .filter(p -> p.getTitlePost().contains(keyword) || p.getDescription().contains(keyword))  // Filtrer la liste pour ne garder que les objets Post contenant le mot-clé
            .collect(Collectors.toList());  // Stocker les objets filtrés dans la liste "result"

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
