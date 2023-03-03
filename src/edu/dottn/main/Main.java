/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import static com.itextpdf.text.Annotation.FILE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.dottn.entities.Avis_Offer;
import edu.dottn.entities.Offre;
import edu.dottn.services.ServiceAvis;
//import static edu.dottn.services.ServiceAvis.getRatingStars;
import edu.dottn.services.ServiceOffre;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bochr
 */
public class Main {
// private static String FILE = "offre.pdf";
    public static void main(String[] args) {
        
//       
//        int rating = 3;
//        String stars = getRatingStars(rating);
//        System.out.println(stars);
//        Document document = new Document();
//     try {
//         PdfWriter.getInstance(document, new FileOutputStream(FILE));
//         
//         document.open();
//         document.addTitle("TEST");
//         addMeta(document);
//          Paragraph paragraph = new Paragraph("This is a test paragraph.");
//        document.add(paragraph);
//         document.close();
         
                 
  Offre o4= new Offre(11,5,5,"Mahmoud", "On_Hold");
  Offre o5= new Offre(14478,17889,380, 11, "Nour", "On_Hold");
  Offre o6= new Offre(14,178,3800, 101, "Maram", "On_Hold");
  Offre o7= new Offre(14,178,3440, 1401, "taha", "On_Hold");
  Offre o1= new Offre(14,178,3440, 1401, "adnen", "Accepted");
  Offre o2= new Offre(14,178,3440, 1401, "Eya", "Accepted");
//  
//  
  
        ServiceOffre so = new ServiceOffre();
        so.ajouterOffre(o4);
        so.ajouterOffre(o5);
        so.ajouterOffre(o6);
        so.ajouterOffre(o7);
        so.ajouterOffre(o6);
        so.ajouterOffre(o1);
        so.ajouterOffre(o2);
//        so.modifierOffre(o7);
////        
//      
//        System.out.println(so.getBYStatus("Accepted"));
//        String filepath = "‪offre.pdf";
//        so.generatePDF(o4);
//        Avis_Offer a = new Avis_Offer();
//        ServiceAvis sa = new ServiceAvis();
////        sa.ajouterAvisOffer(a);
//a=sa.getAvisOffeById(2);
//        System.out.println(a);
//        System.out.println(a.getIdavis());
//        sa.supprimerAvisOffer(a);

//
////        Offre o = new Offre(1222,7 ,"accept");
////       
////        ServiceOffre so = new ServiceOffre();
////        so.ajouterOffre(o);
////
////         ///System.out.println(so.getAll()); 
//     Offre o = new Offre(10, 1222, 7, "nimport", "On_Hold");
////
//////        o.setStatus("Accepted");
//////        Offre o1 = new Offre(14778, 8, "adnen", "Accepted");
////////        Offre o2 = new Offre(14778, 8, "adnen", "Accepted");
////         Offre o3 = new Offre(11,741852,9,"anouar", "Declined");
////        Of
//////        
//Offre o4= new Offre(14478,17889,15633,74896,"Mahmoud", "On_Hold");
//
//  ServiceOffre so = new ServiceOffre();
//  so.AccepterOffre(o4);
//  
////        so.supprimerOffre(12);
////        
////////        so.modifierOffre(o, "Accepted");
////////
//  so.getBYStatus("On_Hold");
  so.getBYStatus("Accepted");

//       if ( so.verifierOffre(o4)==null){
//          so.ajouterOffre(o4);
//       }else{
//           System.out.println("already exist");
//       }
//        
////////        so.sortByDate();
//        so.ajouterOffre(o4);
//////        so.getOneById(8);
//////        so.getByName("adnen");
//////        so.sortByStatus("On_Hold");
//////        so.ajouterOffre(o);
//                 } catch (Exception ex) {
//                 System.out.println(ex.getMessage());
//                 }
//                     
//     
//   }
    
//    private static void addMeta (Document document){
//        document.addTitle("test");
//        document.addSubject("Tessst");
//        document.addAuthor("Saif");
//    }
}}
