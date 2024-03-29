/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.util.Objects;

/**
 *
 * @author WALID
 */
public class User {

    protected int idUser;
    protected String Name;
    protected String Address;
    protected String Email;
    protected String Password;
    protected int Numero;

    public User() {
    }

    public User(int idUser, String Name, String Address, String Email, String Password, int Numero) {
        this.idUser = idUser;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
        this.Numero = Numero;

    }

    public User(String Name, String Address, String Email, String Password, int Numero) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
        this.Numero = Numero;

    }
    //Getter & Setter

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public int getNumero() {
        return Numero;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    @Override
    public String toString() {
        return "User{" + "Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Password=" + Password + ", Numero=" + Numero + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idUser;
        hash = 79 * hash + Objects.hashCode(this.Name);
        hash = 79 * hash + Objects.hashCode(this.Password);
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
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        return true;
    }

}
