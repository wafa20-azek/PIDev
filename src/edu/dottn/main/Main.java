package edu.dottn.main;


import edu.dottn.entities.Category;
import edu.dottn.entities.Product;
import edu.dottn.entities.SubCategory;
import edu.dottn.services.CategoryServices;
import edu.dottn.services.ProductServices;
import edu.dottn.services.SubCategoryServices;
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WAFA
 */
public class Main {

    public static void main(String[] args) {

//        ProductServices ps = new ProductServices();
//        Product p= new Product("product","oifzqio f i","image1",128.5f);
//        Product p1= new Product("product2","oifzqio f i","image1",128f);
//        Product p2= new Product("product3","oifzqio f i","image1",12.5f);
//        Product p3= new Product("product4","oifzqio f i","image1",18.5f);
//        ps.addProduct(p);
//        ps.addProduct(p1);
//        ps.addProduct(p2);
//        ps.addProduct(p3);
        //ps.removeProduct(1);
        // ps.modifyProduct(p);
        //System.out.println(ps.getById(8));
        //System.out.println(ps.getAllById());
        CategoryServices cs = new CategoryServices();
//        Category c1 = new Category("déco");
      Category c2 = new Category("maison");
//        Category c3 = new Category("jouets");
//        Category c4 = new Category("painture");
//        cs.addCategory(c1);
//        cs.addCategory(c2);
//        cs.addCategory(c3);
//        cs.addCategory(c4);
   //     cs.removeCategory(1);
        SubCategoryServices cc= new SubCategoryServices();
SubCategory c = new SubCategory("meuble", 2);
cc.addSubCategory(c);


        System.out.println(cs.getById(2));
//        System.out.println(cs.getAllById());
    }

}
