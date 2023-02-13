/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import edu.dottn.entities.User;
import edu.dottn.services.MemberServices;
import java.util.Scanner;

/**
 *
 * @author WALID
 */
public class Main {

    public static void main(String[] args) {
//        User p1 = new User("walid", "rades", "walidtouj@gmail.com", "walid123456", 28006963, "Member", 0);
//        User p2 = new User("alii", "zahra", "alimed@gmail.com", "ali123", 58749684, "Member", 0);
//        User p3 = new User("salma", "megrine", "salma@gmail.com", "salma123", 58749874, "Member", 0);
//        User p5 = new User("walidtouj@gmail.com","walid123456");
//        MemberServices m1 = new MemberServices();
//        m1.ajouterUser(p1);
//        User p4 = m1.getOneById(3);
//        p4.setName("salmaa");
//        p4.setNumero(22147963);
//        m1.modifierUser(p4);
        int choice ;
        int decision = 0;
        while (true) {
            MemberServices m1 = new MemberServices();
            User p6 = m1.verifSession();
            if (p6 != null) {
                decision = 1;
            } else {
                System.out.println("1-signin");
                System.out.println("2-signup");
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter your choice");
                choice = myObj.nextInt();
                if (choice == 1) {
                    Scanner myObj1 = new Scanner(System.in);
                    System.out.println("Enter your email");
                    String email = myObj1.nextLine();
                    Scanner myObj2 = new Scanner(System.in);
                    System.out.println("Enter your password");
                    String password = myObj2.nextLine();
                    User p5 = new User(email, password);
                    p6 = m1.authenticateUser(p5);
                    decision = 1;

                } else if (choice == 2) {
                    Scanner myObj1 = new Scanner(System.in);
                    System.out.println("Enter your name");
                    String name = myObj1.nextLine();
                    Scanner myObj2 = new Scanner(System.in);
                    System.out.println("Enter your address");
                    String addresse = myObj2.nextLine();
                    Scanner myObj3 = new Scanner(System.in);
                    System.out.println("Enter your email");
                    String email = myObj3.nextLine();
                    Scanner myObj4 = new Scanner(System.in);
                    System.out.println("Enter your password");
                    String password = myObj4.nextLine();
                    Scanner myObj5 = new Scanner(System.in);
                    System.out.println("Enter your numero");
                    int numero = myObj5.nextInt();
                    String role = "Member";
                    p6 = new User(name, addresse, email, password, numero, role, 0);
                    m1.ajouterUser(p6);
                    decision = 1;
                }
            }
            while (decision == 1) {
                System.out.println("want you logout yes or no ?");
                Scanner myObj4 = new Scanner(System.in);
                String out = myObj4.nextLine();
                if (out.equals("yes") && p6 != null) {
                    m1.logOut(p6.getIdUser());
                    System.out.println("vous avez quitter");
                    decision = 0;
                }
            }
            if (decision == 0){
                break;}
        }

    }
}
