/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import edu.dottn.entities.Avis_Offer;
import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceAvis;
import edu.dottn.services.ServiceOffre;

import java.sql.Timestamp;

/**
 *
 * @author bochr
 */
public class Main {

    public static void main(String[] args) {

        Avis_Offer a = new Avis_Offer(10, 7, 1222, 3, "parfait");
        ServiceAvis sa = new ServiceAvis();
        sa.ajouterAvisOffer(a);
        
//        sa.supprimerAvisOffer(a);

//
////        Offre o = new Offre(1222,7 ,"accept");
////       
////        ServiceOffre so = new ServiceOffre();
////        so.ajouterOffre(o);
////        
////         ///System.out.println(so.getAll()); 
//        Offre o = new Offre(10, 1222, 7, "nimport", "On_Hold");
//
//        o.setStatus("Accepted");
//        Offre o1 = new Offre(14778, 8, "adnen", "Accepted");
//        Offre o2 = new Offre(14778, 8, "adnen", "Accepted");
//        Offre o3 = new Offre(741852,9,"anouar", "Declined");
//        
//
//        ServiceOffre so = new ServiceOffre();
////        so.modifierOffre(o, "Accepted");
////
////        so.getBYStatus("On_Hold");
////        so.sortByDate();
//          so.ajouterOffre(o3);
////        so.getOneById(8);
////        so.getByName("adnen");
////        so.sortByStatus("On_Hold");
////        so.ajouterOffre(o);
    }
}
