package edu.dottn.test;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;




public class Example {
    
 public  final String ACCOUNT_SID = "AC5cdd383d413ed620eedf7dad1cb391fa";
  public  final String AUTH_TOKEN = "632e9902034e972c36341a801050995e";
    
    public void sendMessage(String assoName,String titlePost){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+21698198273"),
      "MG5c01c879f4f2b9148bd6a3efe7fdd3c9",
      "Bonjour, l'association "+assoName +" vient de publier un nouvel Post de don intitulé "+titlePost+".")
    .create();

    System.out.println(message.getSid());
    }
  }
