/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.test;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import edu.dottn.entities.Donation;
import edu.dottn.entities.Donation.DonationStatus;
import edu.dottn.entities.Post;
import edu.dottn.entities.Product;
import edu.dottn.entities.SubCategory;
import edu.dottn.entities.User;
//import edu.dottn.entities.User;
import edu.dottn.services.ServiceComment;
import edu.dottn.services.ServiceDonation;
import edu.dottn.services.ServicePost;
import edu.dottn.util.MyConnection;
import java.io.IOException;
import javax.mail.MessagingException;
//import com.restfb.types.User;
import edu.dottn.services.MemberServices;
import edu.dottn.services.ProductServices;


/**
 *
 * @author rajhi
 */
public class Main {
    public static void main(String[] args) throws MessagingException, IOException {
       MyConnection.getInstance();
       ServicePost sp = new ServicePost();
      // ServiceDonation don1= new ServiceDonation();
      // ServiceComment com1 = new ServiceComment();
     //Post p1 = new Post("first post","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
     // Post p2 = new Post("second test","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
     // Post p3 = new Post("second test","TEST API SAIF",new java.sql.Timestamp(System.currentTimeMillis()));

      //  System.out.println(com1.getCommentsByPostId(29));
      ServiceDonation sd = new ServiceDonation();
      Post post = sp.getOneById(29);
      SubCategory sb = new SubCategory(5, "test", 1);
      MemberServices ms = new MemberServices();
       // System.out.println(ms.getOneById(1));
        ProductServices ps = new ProductServices();
       // System.out.println(ps.getAll());
      //  System.out.println(ps.getByIdUser(1));
        
        
        User us = ms.getOneById(1);
        Product p = ps.getById(11);
       // System.out.println(p);
        Donation d = new Donation(us, p, post);
       sd.ajouter(d);
        //System.out.println(sp.getOneById(29));
        
       // ps.addProduct(p);
      //post1.ajouter(p1);
     // System.out.println(post1.getAll());
     //System.out.println(post1.getOneById(1));
    // post1.ajouter(p2);
    //post1.supprimerParId(1);
    //post1.ajouter(p1);
   // post1.modifier(p2);
  // System.out.println("***** DONATION *****");
  // Donation d1= new Donation(DonationStatus.ACCEPTED);
   //Donation d2= new Donation(DonationStatus.REJECTED);
     //don1.ajouter(d1);
     //don1.ajouter(d2);
     //System.out.println(don1.getOneById(2));
     //System.out.println(don1.getAll());
     //don1.modifier(d2);
    // don1.supprimer(1);
     //System.out.println(don1.thankyou());
     
     //post1.modifier(new Post(3,"testststs","test"));
     //post1.supprimerParId(2);
     //don1.modifier(new Donation(3,new java.sql.Timestamp(System.currentTimeMillis()),DonationStatus.REJECTED));
     //don.ajouter(d2);
     //Donation d3 =new Donation(1,1,10,1,DonationStatus.REJECTED);
    //onation d4 = new Donation(user, association, product, p2, dateDonation, DonationStatus.ACCEPTED)

     //don1.ajouter(d3);
   //  Post p3 = new Post("first post","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
     // Post p4 = new Post("second test","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
      //post1.ajouter(p4);
      //post1.ajouter(p3);
      //  System.out.println(post1.getAllPostsSortedByDate(true));

        //System.out.println(post1.searchPostsByKeyword("first"));
        //post1.ajouter(p4);
      //  System.out.println("-*-*-*-*-*-*-*-*-*-*--*-*-");
    //    Post p5 = new Post("hello", "hellopost", new java.sql.Timestamp(System.currentTimeMillis()));
        //post1.ajouter(p5);
        //System.out.println(p5.getIdPost());
        //System.out.println(p5.getTitlePost());
      // post1.modifier(new Post(p5.getIdPost(), 0, "hey", "howay", new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())));
     //post1.supprimerParId(5);
        //System.out.println(post1.getAllPostsSortedByDate(false));
       // System.out.println(post1.searchPostsByKeyword("hello"));
    //System.out.println(don1.findTopDonors());
    
      //System.out.println("****------*******-------*****");
      //Comment c1 = new Comment(p5, "hello");
      //  com1.ajouterComment(c1);
       // System.out.println(com1.getAll());
       // System.out.println(com1.getOneById(5));
      // com1.supprimerParId(5);
       // Comment c2= new Comment(p5,"xxxxx",new java.sql.Timestamp(System.currentTimeMillis()));
      //  com1.ajouterComment(c2);
     // User user1 = new User("Saif", "Tunis","walid.touj@esprit.tn", "testest", 12345678);
      // // User user = new User("Saif Rajhi", "ben arrous", "saif.messaoudi@esprit.tn", "hello", 12345678);
     //  Donation d3 = new Donation(DonationStatus.ACCEPTED);
      //  don1.envoyer(user1);
       // System.out.println(don1.countDonationsForPost(1));
      
      //  don1.ajouter(d3);
        //System.out.println(don1.getDonationsSortedByEtatDonation(DonationStatus.ACCEPTED));
        
       
        //System.out.println( post1.getRecentPost());
        //don1.ajouter(d3);
       // System.out.println(don1);
        
       // com1.ajouterComment(c2);
       //com1.ajouterComment(c1);
       // System.out.println(com1.getAll());
       //don1.envoyer(user);
      //  post1.shareOnPage(p3);
      // don1.sendDonationSms(d1);
     // MemberServices mem = new MemberServices();
      //  System.out.println(mem.getAllById());
      
        
        
        
        
  
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            }
     
    
    
    

     
}