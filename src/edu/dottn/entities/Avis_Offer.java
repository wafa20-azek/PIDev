/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

/**
 *
 * @author bochr
 */
public class Avis_Offer {
    
    private int id_offer;
    private int ID_Product;
    private int idUser;
    private String ratting;
    private String description;

    public Avis_Offer() {
    }

    public Avis_Offer(int ID_Product, int idUser, String ratting, String description) {
        this.ID_Product = ID_Product;
        this.idUser = idUser;
        this.ratting = ratting;
        this.description = description;
    }

    public Avis_Offer(int id_offer, int ID_Product, int idUser, String ratting, String description) {
        this.id_offer = id_offer;
        this.ID_Product = ID_Product;
        this.idUser = idUser;
        this.ratting = ratting;
        this.description = description;
    }

    public int getId_offer() {
        return id_offer;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getRatting() {
        return ratting;
    }

    public String getDescription() {
        return description;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Avis_Offer{" + "ID_Product=" + ID_Product + ", idUser=" + idUser + ", ratting=" + ratting + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Avis_Offer other = (Avis_Offer) obj;
        if (this.id_offer != other.id_offer) {
            return false;
        }
        if (this.ID_Product != other.ID_Product) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }
    
}
