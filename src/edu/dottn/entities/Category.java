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
public class Category {

    //les attributs
    private int id;
    private String name;
  

    //les constructeurs
    public Category() {
    }



    public Category(String name) {
        this.name = name;
    }

    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //les getters et setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

   

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        
        return "Category{" + "name=" + name + '}';
    }

    

}
