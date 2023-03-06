/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import edu.dottn.entities.Event;
import edu.dottn.entities.Event.Status;
import edu.dottn.entities.Reclamation;
import edu.dottn.entities.Reponse;
import edu.dottn.services.ServiceEvent;
import edu.dottn.services.ServiceReclamation;
import edu.dottn.services.ServiceReponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ProInfo
 */
public class Main {
    public static void main(String[] args) {
        //ServiceReclamation sr = new ServiceReclamation();
        //Reclamation r =new Reclamation(1,1,"Not fixed","Broken Product ",Timestamp.from(Instant.now()));
       //sr.ajouter(r);
       // sr.supprimer(1);
       // Reclamation r1  =sr.getOneById(2);
       // System.out.println(r1.toString());
   
//        ServiceReponse pr = new ServiceReponse();
//             List <Reponse> a = pr.getAll();
//             for (Reponse rep : a) {
//        System.out.println("Response: " + rep.getRep());
//        System.out.println("Date: " + rep.getDateRep());
//        System.out.println("Complaint: " + rep.getReclamation().getDescription());
//        System.out.println();
//Reponse a=pr.getOneById(1);
//        
//       System.out.println("Response: " + a.getRep());
//        System.out.println("Date: " + a.getDateRep());
//        System.out.println("Complaint: " + a.getReclamation().getDescription());
//      System.out.println();
//ServiceEvent ee = new ServiceEvent();
//    Event e = new Event("troc","trial number 1",Date.valueOf(LocalDate.of(2023, 12, 15)),"tunis",Status.ongoing);
//    //ee.ajouter(e);
//    List <Event> e1=ee.getAll();
//    for (Event ev:e1){
//        System.out.println("name :"+ev.getName());
//        System.out.println("description : "+ev.getDescription());
//        System.out.println("Date: "+ev.getEventDate());
//        System.out.println("Location : "+ev.getLocation());
//        System.out.println("Status : "+ev.getStatus());
//        System.out.println();
//  }
    ServiceReclamation rep = new ServiceReclamation();
    List<Reclamation>ls=rep.myRec(2);
    for(Reclamation r:ls){
        System.out.println("rep "+r.getDescription());
    }

    
            
   
}}
