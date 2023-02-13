/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

/**
 *
 * @author WAFA
 */
public class SubCategory {

    //les attributs
    private int id;
    private String name;
    private int idcategory;

    //les constructeurs
    public SubCategory() {
    }

    public SubCategory(String name, int idcategory) {
        this.name = name;
        this.idcategory = idcategory;
    }

    public SubCategory(int id, String name, int idcategory) {
        this.id = id;
        this.name = name;
        this.idcategory = idcategory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setName(String name) {
        this.name = name;
    }

 

    @Override
    public String toString() {
        return "SubCategory{" + "name=" + name + '}';
    }

}
