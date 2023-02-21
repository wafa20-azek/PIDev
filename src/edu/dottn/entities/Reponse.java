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
public class Reponse {
    private int idRep;
    private Reclamation reclamation ;
    private String Rep ;
    private Timestamp dateRep;

    public Reponse() {
    }

    public Reponse(Reclamation reclamation, String Rep, Timestamp dateRep) {
        this.reclamation = reclamation;
        this.Rep = Rep;
        this.dateRep = dateRep;
    }

    public Reponse(int idRep, Reclamation reclamation, String Rep, Timestamp dateRep) {
        this.idRep = idRep;
        this.reclamation = reclamation;
        this.Rep = Rep;
        this.dateRep = dateRep;
    }

    public int getIdRep() {
        return idRep;
    }
    
    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public String getRep() {
        return Rep;
    }

    public void setRep(String Rep) {
        this.Rep = Rep;
    }

    public Timestamp getDateRep() {
        return dateRep;
    }

    public void setDateRep(Timestamp dateRep) {
        this.dateRep = dateRep;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idRep;
        hash = 89 * hash + Objects.hashCode(this.reclamation);
        hash = 89 * hash + Objects.hashCode(this.Rep);
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
        final Reponse other = (Reponse) obj;
        if (this.idRep != other.idRep) {
            return false;
        }
        if (!Objects.equals(this.reclamation, other.reclamation)) {
            return false;
        }
        return true;
    }
    
 }
