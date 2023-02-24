/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

//import java.io.BufferedReader;

//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;


//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
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
public class ActivationEmail {

   private static final String SENDGRID_API_KEY = "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";
    // method to send activation email to user
    public static void sendActivationEmail(String email, String activationCode) {
        
//                    Request request = new Request();
//        try {
//            Email from = new Email("walidca572@gmail.com");
//            String subject = "Activate Your Account";
//            Email to = new Email(email);
//            String message = "Your code to activate your account : " + activationCode;
//            Content content = new Content("text/plain", message);
//            Mail mail = new Mail(from, subject, to, content);
//            SendGrid sg = new SendGrid(SENDGRID_API_KEY);
//
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
// Set up the email data
//        String subject = "Activate Your Account";
//        String message = "Your code to activate your account: " + activationCode;
//        String from = "walidca572@gmail.com";
//        String to = "walidtouj@gmail.com";
//
//        // Encode the email data
//        byte[] data = ("subject=" + subject + "&text=" + message + "&from=" + from + "&to=" + to).getBytes(StandardCharsets.UTF_8);
//        String encodedData = Base64.getEncoder().encodeToString(data);

// Send the email
//        try {
//            URL url = new URL("https://api.mailgun.net/v3/sandbox062d13aaa50a4d20b54da615e637927b.mailgun.org/messages");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(("api:" + "4f905ce6db0204a4882d9942fd04fddd-52d193a0-91422921").getBytes()));
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
//            connection.setDoOutput(true);
//            connection.getOutputStream().write(encodedData.getBytes());
//
//            // Check if the email was successfully sent
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.contains("\"message\": \"Queued. Thank you.\"")) {
//                    System.out.println("done");
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
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
        mail.setSubject("Activate Your Account");
        Personalization personalization = new Personalization();
        personalization.addTo(new Email(email));
        mail.addPersonalization(personalization);
        mail.addContent(new Content("text/plain", "Your code to activate your account: " + activationCode));
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
                System.out.println("Activation email sent successfully.");
            } else {
                System.out.println("Activation email failed to send.");
            }
        } catch (IOException ex) {
            System.err.println("Error sending activation email: " + ex.getMessage());
        }

    }

}
