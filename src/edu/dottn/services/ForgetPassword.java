/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
//
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
/**
 *
 * @author WALID
 */
public class ForgetPassword {
    private static final String SENDGRID_API_KEY = "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";
   
    // method to send activation email to user
    public static void sendPasswordResetEmail(String email, String password) {
//        System.out.println(email);
//        System.out.println(password);
//
//        String smtpUsername = "apikey"; // Replace with your SendGrid SMTP username
//        String smtpPassword = "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"; // Replace with your SendGrid SMTP password
//
//        // Set up SMTP properties
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.sendgrid.net");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//
//        // Set up SMTP session with authentication
//        Authenticator auth = new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(smtpUsername, smtpPassword);
//            }
//        };
//        Session session = Session.getInstance(props, auth);
//        SendGrid sg = new SendGrid("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");
//        Mail mail = new Mail();
//        mail.setFrom(new Email("walidca572@gmail.com"));
//        mail.setSubject("Réinitialisation de votre mot de passe");
//        Personalization personalization = new Personalization();
//        personalization.addTo(new Email(email));
//        mail.addPersonalization(personalization);
//        mail.addContent(new Content("text/plain", "Bonjour,\\n\\nVotre nouveau mot de passe est : "));
//        try {
//            HttpClient http = HttpClientBuilder.create().build();
//            HttpPost post = new HttpPost("https://api.sendgrid.com/v3/mail/send");
//            StringEntity entity = new StringEntity(mail.build());
//            post.setEntity(entity);
//            post.setHeader("Authorization", "Bearer " + "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");
//            post.setHeader("Content-Type", "application/json");
//            HttpResponse response = http.execute(post);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == 202) {
//                System.out.println("Reset email sent successfully.");
//            } else {
//                System.out.println("Reset email failed to send."+statusCode);
//            }
//        } catch (IOException ex) {
//            System.err.println("Error sending Reset email: " + ex.getMessage());
//        }
           String smtpUsername = "apikey"; // Replace with your SendGrid SMTP username
        String smtpPassword = "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"; // Replace with your SendGrid SMTP password

        // Set up SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        // Set up SMTP session with authentication
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        };
        Session session = Session.getInstance(props, auth);
        SendGrid sg = new SendGrid("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");
        Mail mail = new Mail();
        mail.setFrom(new Email("walidca572@gmail.com"));
        mail.setSubject("mot de passe");
        Personalization personalization = new Personalization();
        personalization.addTo(new Email(email));
        mail.addPersonalization(personalization);
        mail.addContent(new Content("text/plain", "Votre nouveau mot de passe est: " + password));
        try {
            HttpClient http = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://api.sendgrid.com/v3/mail/send");
            StringEntity entity = new StringEntity(mail.build());
            post.setEntity(entity);
            post.setHeader("Authorization", "Bearer " + "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");
            post.setHeader("Content-Type", "application/json");
            HttpResponse response = http.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 202) {
                System.out.println("Password sent successfully.");
            } else {
                System.out.println("Password failed to send.");
            }
        } catch (IOException ex) {
            System.err.println("Error sending activation email: " + ex.getMessage());
        }

    }
    
}
