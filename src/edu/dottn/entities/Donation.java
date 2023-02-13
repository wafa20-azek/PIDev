/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

//import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author rajhi
 */
public class Donation {
    
 private int idDon;
    private int idUser;
    private int idAssociation;
    private int ID_Product;
    private int idPost;
    private Timestamp dateDonation;
    private DonationStatus etatDonation;

    public Donation() {
    }

    public Donation(DonationStatus etatDonation) {
        this.etatDonation = etatDonation;
    }
    

    public Donation(Timestamp dateDonation, DonationStatus etatDonation) {
        this.dateDonation = dateDonation;
        this.etatDonation = etatDonation;
    }
    
    public Donation(int idDon, int idUser, int idAssociation, int ID_Product, int idPost, Timestamp dateDonation, DonationStatus etatDonation) {
        this.idDon = idDon;
        this.idUser = idUser;
        this.idAssociation = idAssociation;
        this.ID_Product = ID_Product;
        this.idPost = idPost;
        this.dateDonation = dateDonation;
        this.etatDonation = etatDonation;
    }

    public Donation(int idUser, Timestamp dateDonation, DonationStatus etatDonation) {
        this.idUser = idUser;
        this.dateDonation = dateDonation;
        this.etatDonation = etatDonation;
    }
    

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


    public int getIdPost() {
        return idPost;
    }


    public Timestamp getDateDonation() {
        return dateDonation;
    }
// a corriiigerrrrrrrrr
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
        ACCEPTED, REJECTED
    }

    @Override
    public String toString() {
        return "Donation{" + "dateDonation=" + dateDonation + ", etatDonation=" + etatDonation + '}';
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
