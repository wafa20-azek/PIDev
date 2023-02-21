/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ProInfo
 */
public class User {
    private int id;
    private String Name;
    private String Address;
    private String Email;
    private String Password;
    private int Numero;
    private List FavoritiesList;
    private String Role;
    private int credit;
    public User() {
    }

    public User(int id, String Name, String Address, String Email, String Password, int Numero,String Role,int credit) {
        this.id = id;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
        this.Numero = Numero;
        this.FavoritiesList = new ArrayList();
        this.credit = credit;
        this.Role = Role;
    }

    public User(String Name, String Address, String Email, String Password, int Numero,String Role,int credit) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
        this.Numero = Numero;
        this.FavoritiesList = new ArrayList();
        this.credit = credit;
        this.Role = Role;
    }
      //Getter & Setter
    public int getId() {
        return id;
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

    public List getFavoritiesList() {
        return FavoritiesList;
    }

    public String getRole() {
        return Role;
    }

    public int getCredit() {
        return credit;
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

    

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "User{" + "Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Password=" + Password + ", Numero=" + Numero + ", FavoritiesList=" + FavoritiesList + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        if (this.id != other.id) {
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
