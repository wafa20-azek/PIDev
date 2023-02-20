/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author bochr
 */
public class Offre {

    private int idUser, id_Offre, ID_Product;
    private String name;
    private Timestamp date_offre;
    private String status;
    List<String> st = new ArrayList();

    public Offre() {
    }

    public Offre(int id_Offre, int ID_Product, int idUser, String name, String status) {

        this.id_Offre = id_Offre;
        this.ID_Product = ID_Product;
        this.idUser = idUser;
        this.name = name;
        this.status = status;
        this.date_offre = Timestamp.valueOf(LocalDateTime.now());

    }

    public Offre(int ID_Product, int idUser, String name, String status) {
        st.add("Accepted");
        st.add("Declined");
        st.add("On_Hold");
        this.ID_Product = ID_Product;
        this.idUser = idUser;
        this.name = name;
        this.date_offre = Timestamp.valueOf(LocalDateTime.now());
        this.status = status;

    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
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
        return "Offre{" + "idUser=" + idUser + ", ID_Product=" + ID_Product + ", name=" + name + ", date_offre=" + date_offre + ", status=" + status + '}';
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
