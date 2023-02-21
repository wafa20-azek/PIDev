/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author ProInfo
 */
public class Reclamation {
    private int idRec;
    private int customer;
    private int product;
    private String status ;
    private String description;
    private Timestamp dateRec;

    public Reclamation() {
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation(int customer, int product, String status, String description, Timestamp dateRec) {
        this.customer = customer;
        this.product = product;
        this.status = status;
        this.description = description;
        this.dateRec = dateRec;
    }

    public Reclamation(int idRec, int customer, int product, String status, String description, Timestamp dateRec) {
        this.idRec = idRec;
        this.customer = customer;
        this.product = product;
        this.status = status;
        this.description = description;
        this.dateRec = dateRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateRec() {
        return dateRec;
    }

    public void setDateRec(Timestamp dateRec) {
        this.dateRec = dateRec;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idRec;
        hash = 79 * hash + this.customer;
        hash = 79 * hash + this.product;
        hash = 79 * hash + Objects.hashCode(this.status);
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
        final Reclamation other = (Reclamation) obj;
        if (this.idRec != other.idRec) {
            return false;
        }
        if (this.customer != other.customer) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation { customer=" + customer + ", product=" + product + ", status=" + status + ", description=" + description + ", dateRec=" + dateRec + '}';
    }
    
    
    
}
