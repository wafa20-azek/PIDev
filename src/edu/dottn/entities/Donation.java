package edu.dottn.entities;

import edu.dottn.services.ServiceDonation;
import edu.dottn.services.ServicePost;
import java.sql.Timestamp;

public class Donation {

    private int idDon;
    private int idUser;
    private int idAssociation;
    private int ID_Product;
    private Post post=new Post( );
    private Timestamp date_Donation;
    private DonationStatus etatDonation;
    private ServicePost pos = new ServicePost();
    
    // Les Constructeuurs :D 

    public Donation() {
    }
    
       public Donation(int idUser, int idAssociation, int ID_Product, int idPost, DonationStatus etatDonation) {
        this.idUser = idUser;
        this.idAssociation = idAssociation;
        this.ID_Product = ID_Product;
        this.post = pos.getOneById(idPost);
        this.etatDonation = etatDonation;
    }
       
       // Définition des Getters et Setters :D

    public int getIdDon() {
        return idDon;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdAssociation() {
        return idAssociation;
    }
    public int getIdProduct() {
        return ID_Product;
    }

    public Post getPost() {
        return post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }

    public Timestamp getDateDonation() {
        return date_Donation;
    }

    public void setDateDonation(Timestamp date_Donation) {
        this.date_Donation = date_Donation;
    }

    public DonationStatus getEtatDonation() {
        return etatDonation;
    }

    public void setEtatDonation(DonationStatus etatDonation) {
        this.etatDonation = etatDonation;
    }

    public enum DonationStatus {
        ACCEPTED, REJECTED
    }

    @Override
    public String toString() {
        return "Donation{" + "post=" + post + ", date_Donation=" + date_Donation + ", etatDonation=" + etatDonation + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idDon;
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
        final Donation other = (Donation) obj;
        if (this.idDon != other.idDon) {
            return false;
        }
        return true;
    }
}
