package edu.dottn.main;

import edu.dottn.services.AssociationServices;
import edu.dottn.services.MessageServices;




public class Main {
    
    public static void main (String [] args  ){
        
        AssociationServices as = new AssociationServices();
        as.runAssociationApp();
         
       MessageServices ms = new MessageServices();
       ms.getConversation(1, 3);
       // ms.displayConversationUsers("3");
        //MessageServices ms = new MessageServices();

//        AssociationServices as = new AssociationServices();
//        as.Signup();
//      System.out.println(as.getById(2));
        
        //ms.sendAssociationMessage("2");
    }
    
    
}
