/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

/**
 *
 * @author ProInfo
 */
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
    private boolean activated;

    public Member() {
    }

    public Member(int idUser, String Name, String Address, String Email, String Password, int Numero, int credit,boolean activated) {
        super(idUser, Name, Address, Email, Password, Numero);
        this.credit = credit;
        this.activated = activated;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    

    public List getFavoritesList() {
        return FavoritesList;
    }

    public List getProductsList() {
        return ProductsList;
    }
}
