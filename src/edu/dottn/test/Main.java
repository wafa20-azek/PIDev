/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.test;

import com.sun.nio.sctp.Association;
import edu.dottn.entities.Comment;
import edu.dottn.entities.Donation;
import edu.dottn.entities.Donation.DonationStatus;
import edu.dottn.entities.Post;
import edu.dottn.services.ServiceComment;
import edu.dottn.services.ServiceDonation;
import edu.dottn.services.ServicePost;
import edu.dottn.util.MyConnection;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 *
 * @author rajhi
 */
public class Main {
    public static void main(String[] args) {
       MyConnection.getInstance();
       ServicePost post1 = new ServicePost();
       ServiceDonation don1= new ServiceDonation();
       ServiceComment com1 = new ServiceComment();
      Post p1 = new Post("first post","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
      Post p2 = new Post("second test","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));

      
      //post1.ajouter(p1);
     // System.out.println(post1.getAll());
     //System.out.println(post1.getOneById(1));
    // post1.ajouter(p2);
    //post1.supprimerParId(1);
    //post1.ajouter(p1);
   // post1.modifier(p2);
   System.out.println("***** DONATION *****");
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
    // Donation d3 =new Donation(1,1,10,1,DonationStatus.REJECTED);
     //don1.ajouter(d3);
     Post p3 = new Post("first post","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
      Post p4 = new Post("second test","dons des livres",new java.sql.Timestamp(System.currentTimeMillis()));
      //post1.ajouter(p4);
      //post1.ajouter(p3);
      //  System.out.println(post1.getAllPostsSortedByDate(true));

        //System.out.println(post1.searchPostsByKeyword("first"));
        //post1.ajouter(p4);
        System.out.println("-*-*-*-*-*-*-*-*-*-*--*-*-");
        Post p5 = new Post("hello", "hellopost", new java.sql.Timestamp(System.currentTimeMillis()));
        //post1.ajouter(p5);
        //System.out.println(p5.getIdPost());
        //System.out.println(p5.getTitlePost());
      // post1.modifier(new Post(p5.getIdPost(), 0, "hey", "howay", new java.sql.Timestamp(System.currentTimeMillis()), new java.sql.Timestamp(System.currentTimeMillis())));
     //post1.supprimerParId(5);
        //System.out.println(post1.getAllPostsSortedByDate(false));
       // System.out.println(post1.searchPostsByKeyword("hello"));
    //System.out.println(don1.findTopDonors());
    
        System.out.println("****------*******-------*****");
        Comment c1 = new Comment(p5, "hello its me",new java.sql.Timestamp(System.currentTimeMillis()));
        //com1.ajouterComment(c1);
       // System.out.println(com1.getAll());
       // System.out.println(com1.getOneById(5));
      // com1.supprimerParId(5);
        Comment c2= new Comment(p5,"xxxxx",new java.sql.Timestamp(System.currentTimeMillis()));
        com1.ajouterComment(c2);
    
    }
    
    
    

     
}