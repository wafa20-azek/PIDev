/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.entities;

import edu.dottn.services.CategoryServices;
import edu.dottn.services.SubCategoryServices;

/**
 *
 * @author WAFA
 */
public class SubCategory {

    //les attributs
    private int id;
    private String name;
    private Category category = new Category();
    private CategoryServices  ss= new CategoryServices();
    //les constructeurs
    public SubCategory() {
    }

    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public SubCategory(int id, String name, int idcategory) {
        this.id = id;
        this.name = name;
        this.category = ss.getById(idcategory);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

 

    @Override
    public String toString() {
        return "SubCategory{" + "name=" + name + category+ '}';
    }

}