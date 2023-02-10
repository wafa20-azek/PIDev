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
public class Product {
    
    //les attributs 
    private int id;
    private String Name;
    private String Description;
    private String image;
    private float price;
    
    //les constructeurs

    public Product() {
    }

    public Product(String Name, String Description, String image, float price) {
        this.Name = Name;
        this.Description = Description;
        this.image = image;
        this.price = price;
    }

    public Product(int id, String Name, String Description, String image, float price) {
        this.id = id;
        this.Name = Name;
        this.Description = Description;
        this.image = image;
        this.price = price;
    }
    
    //les getters et setters

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

   
    
    @Override
    public String toString() {
        return "Product{" + "Name=" + Name + ", Description=" + Description + ", image=" + image + ", price=" + price + '}';
    }
    
    
    
}
