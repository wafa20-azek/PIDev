package edu.dottn.main;

import edu.dottn.services.AssociationServices;
import edu.dottn.services.MessageServices;




public class Main {
    
    public static void main (String [] args  ){
        
        AssociationServices as = new AssociationServices();
        as.runAssociationApp();
<<<<<<< HEAD
         
       MessageServices ms = new MessageServices();
       ms.getConversation(1, 3);
       // ms.displayConversationUsers("3");
=======
>>>>>>> 78467244155aebe847fb951d6f473a7bce939133
        //MessageServices ms = new MessageServices();

//        AssociationServices as = new AssociationServices();
//        as.Signup();
//      System.out.println(as.getById(2));
        
        //ms.sendAssociationMessage("2");
    }
    
    
}
