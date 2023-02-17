/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WALID
 */
public class Member extends User {

    private int credit;
    private List FavoritesList;
    private List ProductsList;

    public Member() {
    }

    public Member(int idUser, String Name, String Address, String Email, String Password, int Numero, int credit) {
        super(idUser, Name, Address, Email, Password, Numero);
        this.credit = credit;
        this.FavoritesList = new ArrayList();
        this.ProductsList = new ArrayList();
    }

    public Member(String Name, String Address, String Email, String Password, int Numero, int credit) {
        super(Name, Address, Email, Password, Numero);
        this.credit = credit;
        this.FavoritesList = new ArrayList();
        this.ProductsList = new ArrayList();
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List getFavoritesList() {
        return FavoritesList;
    }

    public List getProductsList() {
        return ProductsList;
    }
}
