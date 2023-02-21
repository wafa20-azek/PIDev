/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import edu.dottn.services.ServicePost;
import java.sql.Timestamp;
/**
 *
 * @author rajhi
 */
public class Comment {
    private int id_comment;
    private int idUser;
    private Post post;
    ServicePost sp = new ServicePost();
    private String contenu;
    private Timestamp dateComment;

    public Comment() {
    }

    public Comment(Post post, String contenu, Timestamp dateComment) {
        this.post = post;
        this.contenu = contenu;
        this.dateComment = dateComment;
    }

    public Comment(String contenu, Timestamp dateComment) {
        this.contenu = contenu;
        this.dateComment = dateComment;
    }

    public Comment(Post post, String contenu) {
        this.post = post;
        this.contenu = contenu;
    }
    
    public int getIdUser() {
        return idUser;
    }


    public Post getIdPost() {
        return post;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDateComment() {
        return dateComment;
    }

    public void setDateComment(Timestamp dateComment) {
        this.dateComment = dateComment;
    }

    public int getId_comment() {
        return id_comment;
    }

    
    @Override
    public String toString() {
        return "Comment{" + "contenu=" + contenu + ", dateComment=" + dateComment + '}';
    }
    
    
   
    
}
