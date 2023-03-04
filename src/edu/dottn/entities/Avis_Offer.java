/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import edu.dottn.services.MemberServices;
import edu.dottn.services.ProductServices;
import edu.dottn.services.ServiceOffre;

/**
 *
 * @author bochr
 */
public class Avis_Offer {

    private int idavis;
    private Offre offer;
    private Product product= new Product();
    private User user= new User();
    private int ratting	;
    private ServiceOffre so=new ServiceOffre();
//    private String description;

    public Avis_Offer() {
    }

    public Avis_Offer( Offre offer,  int ratting) {
        this.offer = offer;
        this.product = offer.getProduct2();
        this.user = offer.getUser2();
        this.ratting = ratting;
//        this.description = description;
    }

    public Avis_Offer(int idavis,Offre offer, int ID_Product, int idUser, int ratting, String description) {
        this.idavis = idavis;
        this.offer = offer;
        this.product =offer.getProduct2();
        this.user = offer.getUser2();
        this.ratting = ratting;
//        this.description = description;
    }

    public Offre getOffer() {
        return offer;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

  

  

    public int getRatting() {
        return ratting;
    }

//    public String getDescription() {
//        return description;
//    }

    public int getIdavis() {
        return idavis;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    @Override
    public String toString() {
        return "Avis_Offer{" + "idavis=" + idavis + ", offer=" + offer + ", product=" + product.getName() + ", user=" + user.getName() + ", ratting=" + ratting + '}';
    }

  
}
