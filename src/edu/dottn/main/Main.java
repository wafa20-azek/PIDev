/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.main;

import edu.dottn.entities.Admin;
import edu.dottn.entities.Member;
import edu.dottn.entities.User;
import edu.dottn.services.AuthenticationTwoFactor;
import edu.dottn.services.MemberServices;
import java.util.Scanner;

/**
 *
 * @author WALID
 */
public class Main {

    public static void main(String[] args) {
//        int choice;
//        int decision = 0;
    boolean add;
//        while (true) {
           MemberServices m1 = new MemberServices();
           
//            User p6 = m1.verifSession();
//            if (p6 != null) {
//                decision = 1;
//            } else {
//                System.out.println("1-signin");
//                System.out.println("2-signup");
//                Scanner myObj = new Scanner(System.in);
//                System.out.println("Enter your choice");
//                choice = myObj.nextInt();
//                if (choice == 1) {
//                    Scanner myObj1 = new Scanner(System.in);
//                    System.out.println("Enter your email");
//                    String email = myObj1.nextLine();
//                    Scanner myObj2 = new Scanner(System.in);
//                    System.out.println("Enter your password");
//                    String password = myObj2.nextLine();
//
//                    p6 = m1.authenticateUser(email, password);
//                    decision = 1;
//
//                } else if (choice == 2) {
//                    Scanner myObj1 = new Scanner(System.in);
//                    System.out.println("Enter your name");
//                    String name = myObj1.nextLine();
//                    Scanner myObj2 = new Scanner(System.in);
//                    System.out.println("Enter your address");
//                    String addresse = myObj2.nextLine();
//                    Scanner myObj3 = new Scanner(System.in);
//                    System.out.println("Enter your email");
//                    String email = myObj3.nextLine();
//                    Scanner myObj4 = new Scanner(System.in);
//                    System.out.println("Enter your password");
//                    String password = myObj4.nextLine();
//                    Scanner myObj5 = new Scanner(System.in);
//                    System.out.println("Enter your numero");
//                    int numero = myObj5.nextInt();
//
//                   User p5 = new Member("walid","radeq","walidtouj@gmail.com", "aaa", 69369789, 0);
//                 add = m1.ajouterUser(p5);
//                    decision = 1;
//                }
//            }
//            if (p6 instanceof Member) {
//                while (decision == 1) {
//                    System.out.println("want you logout yes or no ?");
//                    Scanner myObj4 = new Scanner(System.in);
//                    String out = myObj4.nextLine();
//                    if (out.equals("yes")) {
//                        m1.logOut(p6.getIdUser());
//                        System.out.println("vous avez quitter");
//                        decision = 0;
//                    }
//                }
//            } else if (p6 instanceof Admin) {
//                while (decision == 1) {
//                    System.out.println("1- chercher user par nom ");
//                    System.out.println("2- logout ");
//                    Scanner myObj4 = new Scanner(System.in);
//                    int z = myObj4.nextInt();
//                    if (z == 2) {
//                        m1.logOut(p6.getIdUser());
//                        System.out.println("vous avez quitter");
//                        decision = 0;
//                    }
//                    else if(z == 1){
//                       System.out.println("Tapez le nom"); 
//                       Scanner myObj7 = new Scanner(System.in);
//                       String nom = myObj7.nextLine();
//                       System.out.println(m1.findUserByNom(nom));
//                    }
//                }
//            }
//            if (decision == 0) {
//                break;
//            }
//        }
//
    }
}
