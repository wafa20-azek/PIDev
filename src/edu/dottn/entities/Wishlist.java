/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

/**
 *
 * @author WALID
 */
public class Wishlist {
    private int id;
    private int idUser;
    private int idProduct;

    public Wishlist() {
    }

    public Wishlist(int idUser, int idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public Wishlist(int id, int idUser, int idProduct) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
    
    
    
    
}
