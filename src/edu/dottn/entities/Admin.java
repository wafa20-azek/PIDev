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
public class Admin extends User {

    public Admin() {
    }

    public Admin(int idUser, String Name, String Address, String Email, String Password, int Numero) {
        super(idUser, Name, Address, Email, Password, Numero);
    }

    public Admin(String Name, String Address, String Email, String Password, int Numero) {
        super(Name, Address, Email, Password, Numero);
    }
    
}
