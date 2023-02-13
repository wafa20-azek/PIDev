/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.sql.Timestamp;




/**
 *
 * @author bochr
 */
public class Offre {

    private int idUser, id_Offre, ID_Product;
    private  Timestamp date_offre;
    private String status;

  
   private enum status {
        Accept, Denied, On_Hold
    };

    public Offre() {
    }

    public Offre(int idUser, int id_Offre, int ID_Product, Timestamp date_offre, String status) {
        this.idUser = idUser;
        this.id_Offre = id_Offre;
        this.ID_Product = ID_Product;
        this.date_offre = date_offre;
        this.status = status;
    }

    public Offre(int idUser, int ID_Product, Timestamp date_offre, String status) {
        this.idUser = idUser;
        this.ID_Product = ID_Product;
        this.date_offre = date_offre;
        this.status = status;
        
    }

    public int getIdUser() {
        return idUser;
    }

    public int getId_Offre() {
        return id_Offre;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public Timestamp getDate_offre() {
        return date_offre;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Offre{" + "date_offre=" + date_offre + ", status=" + status + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final Offre other = (Offre) obj;
        if (this.id_Offre != other.id_Offre) {
            return false;
        }
        return true;
    }
    

}
