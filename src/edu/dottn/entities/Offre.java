/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import edu.dottn.services.MemberServices;
import edu.dottn.services.ProductServices;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author bochr
 */
public class Offre {

    private int  id_Offre;
    private Date date_offre ;
    private String status;
    List<String> st = new ArrayList();
    private User user1 = new User();
    private User user2 = new User();
    private MemberServices us= new MemberServices();
    private Product product1= new Product();
    private Product product2= new Product();
    private ProductServices ps= new ProductServices();

    public Offre() {
    }

    public Offre(int id_Offre, int ID_Product1, int idUser1, int ID_Product2, int idUser2,Date date_offre,String status) {

        this.id_Offre = id_Offre;
        this.product1 = ps.getById(ID_Product1);
        this.user1 = us.getOneById(idUser1);
        this.product2=ps.getById(ID_Product2);
        this.user2 = us.getOneById(idUser2);
        this.status = status;
        this.date_offre = date_offre;

    }

    public Offre(Product product1, User user1, Product product2, User user2) {
        st.add("Accepted");
        st.add("Declined");
        st.add("On_Hold");
        
       this.product1 = product1;
        this.user1 = user1;
        this.product2=product2;
        this.user2 = user2;
        this.date_offre = new Date(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        this.status = "On_Hold";

    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public Product getProduct1() {
        return product1;
    }

    public Product getProduct2() {
        return product2;
    }

    public int getId_Offre() {
        return id_Offre;
    }
    public Date getDate_offre() {
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
        return "Offre{" + "User name=" + user1.getName() + ", Product=" + product1.getName()  + "User name=" + user2.getName() + ", Product=" + product2.getName() + ", date_offre=" + date_offre + ", status=" + status + '}';
    }

  

}
