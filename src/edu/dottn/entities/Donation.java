package edu.dottn.entities;

import edu.dottn.services.AssociationServices;
import edu.dottn.services.ServiceDonation;
import edu.dottn.services.ServicePost;
import java.sql.Timestamp;

public class Donation {

    private int idDon;
    private User user;
    private Association association;
    private Product product;
    private Post post;
    private Timestamp dateDonation;
    private DonationStatus etatDonation;
    private ServicePost pos = new ServicePost();
    private AssociationServices asso = new AssociationServices();

    public Donation() {
    }

    public Donation(User user, Product product, Post post, DonationStatus etatDonation) {
        this.user = user;
        this.product = product;
        this.post = post;
    
        this.etatDonation = etatDonation;
    }
    

    public Donation(User user, Association association, Product product,Post post, Timestamp dateDonation, DonationStatus etatDonation) {
        this.user = user;
        this.association = association;
        this.product = product;
        this.post = post;
        this.dateDonation = dateDonation;
        this.etatDonation = etatDonation;
    }

    public Donation(User user, Product product, Post post) {
        this.user = user;
        this.product = product;
        this.post = post;
    }
    
    
    public Donation(Timestamp dateDonation, DonationStatus etatDonation) {
        this.dateDonation = dateDonation;
        this.etatDonation = etatDonation;
    }

    public Donation(DonationStatus etatDonation) {
        this.etatDonation = etatDonation;
    }

    public int getIdDon() {
        return idDon;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    


    
    public User getUser() {
        return user;
    }

    public Association getAssociation() {
        return association;
    }

    public Product getProduct() {
        return product;
    }

    public Post getPost() {
        return post;
    }
    
    public Timestamp getDateDonation() {
        return dateDonation;
    }

    public void setDateDonation(Timestamp dateDonation) {
        this.dateDonation = dateDonation;
    }

    public DonationStatus getEtatDonation() {
        return etatDonation;
    }

    public void setEtatDonation(DonationStatus etatDonation) {
        this.etatDonation = etatDonation;
    }

    public enum DonationStatus {
        ACCEPTED, REJECTED, ON_HOLD
    }

    @Override
    public String toString() {
        return "Donation{" + "user=" + user + ", product=" + product + ", post=" + post + '}';
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
