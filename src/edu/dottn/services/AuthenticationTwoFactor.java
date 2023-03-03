/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

/**
 *
 * @author WALID
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class AuthenticationTwoFactor {

    // Twilio API credentials
    public static final String ACCOUNT_SID = "ACc96c9836f8bb4c0178017342f92c6f13";
    public static final String AUTH_TOKEN = "b0d76cdbb990c84aad6a96f98cfd025c";

    public static void TwoFactorSendVerification(int number,int code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Recipient phone number
        String toPhoneNumber = "+216"+number;

        // Sender phone number (Twilio number)
        String fromPhoneNumber = "+13156057964";
        
        // SMS message content
        String message = "Your TrocTn verification code is : " + code;
        // Send the SMS message
        Message twilioMessage = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                message).create();

        // Print the message SID (identifier) and status
        System.out.println("Message SID: " + twilioMessage.getSid());
        System.out.println("Message status: " + twilioMessage.getStatus());
    }

}
