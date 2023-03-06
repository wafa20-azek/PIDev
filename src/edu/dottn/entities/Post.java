package edu.dottn.entities;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Post {

    private int idPost;
    private Association association;
    private String titlePost;
    private String description;
    private String postimage; 
    private Timestamp date_created;
    private Timestamp date_updated;
    private int likes;
    private int dislikes;

    public Post() {

    }

    public Post(String titlePost, String description, Timestamp date_created,String postimage,int id ) {
        this.titlePost = titlePost;
        this.description = description;
        this.date_created = date_created;
        this.postimage=postimage;
        this.idPost=id;
    }
      public Post(String titlePost, String description, Timestamp date_created,String postimage ) {
        this.titlePost = titlePost;
        this.description = description;
        this.date_created = date_created;
        this.postimage=postimage;
      
    }

    public Post(int idPost, String titlePost, String description, Timestamp date_createdn, String postimage) {
        this.idPost = idPost;
        this.titlePost = titlePost;
        this.description = description;
        this.postimage = postimage;
        this.date_created = date_created;
    }
      

    public Post(Association associations ,String titlePost, String description,String image) {
        this.titlePost = titlePost;
         this.association = association;
        this.description = description;
        this.postimage=image;
       
    }

    public Post(int idPost, Association association, String titlePost, String description, String postimage, Timestamp date_created, Timestamp date_updated, int likes, int dislikes) {
        this.idPost = idPost;
        this.association = association;
        this.titlePost = titlePost;
        this.description = description;
        this.postimage = postimage;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getIdPost() {
        return idPost;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
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
    
    public String getPhotos() { 
        return postimage;
    }

    public void setPhotos(String postimage) { 
        this.postimage = postimage;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
    
    public void addLike() {
        this.likes++;
    }

    public void addDislike() {
        this.dislikes++;
    }
    
    public void removeLike() {
        if (likes > 0) {
            likes--;
        }
    }
    
    public void removeDisLike() {
        if (dislikes > 0) {
            dislikes--;
        }
    }

    @Override
    public String toString() {
        return "Post{" + "titlePost=" + titlePost + ", description=" + description + ", photos=" + postimage + ", date_created=" + date_created + ", date_updated=" + date_updated + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }

   
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
