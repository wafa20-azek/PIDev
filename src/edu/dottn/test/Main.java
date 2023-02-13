/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.test;

import edu.dottn.entities.Donation;
import edu.dottn.entities.Donation.DonationStatus;
import edu.dottn.entities.Post;
import edu.dottn.services.ServiceDonation;
import edu.dottn.services.ServicePost;
import edu.dottn.util.MyConnection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author rajhi
 */
public class Main {
    public static void main(String[] args) {
       MyConnection.getInstance();
      Post p1 = new Post("first post","dons des livres");
      Post p2 = new Post("second test","dons des livres");

      ServicePost post1 = new ServicePost();
      //post1.ajouter(p1);
     // System.out.println(post1.getAll());
     //System.out.println(post1.getOneById(1));
    // post1.ajouter(p2);
    //post1.supprimerParId(1);
    //post1.ajouter(p1);
   // post1.modifier(p2);
   System.out.println("***** DONATION *****");
   Donation d1= new Donation(DonationStatus.ACCEPTED);
   Donation d2= new Donation(DonationStatus.REJECTED);

     ServiceDonation don1= new ServiceDonation();
      ServiceDonation don= new ServiceDonation();
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
     
     
 
    d2.setDateDonation(Timestamp.valueOf(LocalDateTime.now())); 
    d2.setEtatDonation(DonationStatus.ACCEPTED);

    don1.modifier(d2);

     
     
     
 
    }
    

     
}