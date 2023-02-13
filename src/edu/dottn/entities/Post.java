/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.sql.Timestamp;

/**
 *
 * @author rajhi
 */
public class Post {

    private int idPost;
    private int idAssociation;
    private String titlePost;
    private String description;
    private  Timestamp date_created;
    private Timestamp date_updated;

    public Post() {

    }

    public Post(String titlePost, String description) {
        this.titlePost = titlePost;
        this.description = description;

    }

    public Post(int idPost, String titlePost, String description) {
        this.idPost = idPost;
        this.titlePost = titlePost;
        this.description = description;
    }
    

    public Post(int idPost, int idAssociation, String titlePost, String description, Timestamp date_created, Timestamp date_updated) {
        this.idPost = idPost;
        this.idAssociation = idAssociation;
        this.titlePost = titlePost;
        this.description = description;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    public int getIdPost() {
        return idPost;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public Timestamp getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Timestamp date_updated) {
        this.date_updated = date_updated;
    }

    public int getIdAssociation() {
        return idAssociation;
    }

    @Override
    public String toString() {
        return "Post{" + "titlePost=" + titlePost + ", description=" + description + ", date_created=" + date_created + ", date_updated=" + date_updated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idPost;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.idPost != other.idPost) {
            return false;
        }
        return true;
    }

}
