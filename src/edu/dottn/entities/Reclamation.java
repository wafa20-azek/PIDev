/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import edu.dottn.services.MemberServices;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author ProInfo
 */
public class Reclamation {
    private int idRec;
    private User customer;
    private String status ;
    private String description;
    private Timestamp dateRec;
 MemberServices us = new MemberServices();
    public Reclamation() {
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation(User customer, int product, String status, String description, Timestamp dateRec) {
        this.customer = customer;
        this.status = status;
        this.description = description;
        this.dateRec = dateRec;
    }

    public Reclamation(int idRec, int customer, String status, String description, Timestamp dateRec) {
        this.idRec = idRec;
        this.customer = us.getOneById(customer);
        this.status = status;
        this.description = description;
        this.dateRec = dateRec;
    }

    public int getIdRec() {
        return idRec;
    }

    public User getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateRec() {
        return dateRec;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateRec(Timestamp dateRec) {
        this.dateRec = dateRec;
    }

   
   

    @Override
    public String toString() {
        return "Reclamation { customer=" + customer  + ", status=" + status + ", description=" + description + ", dateRec=" + dateRec + '}';
    }
    
    
    
}
