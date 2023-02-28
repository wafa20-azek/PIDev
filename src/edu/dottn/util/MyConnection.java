/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.util;

/**
 *
 * @author rajhi
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
   // JDBC URL, username and password
    private  final String url = "jdbc:mysql://localhost:3306/troctndb";
    private  final String user = "root";
    private  final String password = "";
  
    // JDBC  managing connection
    private  Connection cnx;
   
    private  static MyConnection instance = null;

    private MyConnection() {
        try {
            // opening database
            cnx = DriverManager.getConnection(url, user, password);

            System.out.println("Successfully connected to DB");
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            
        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnx;
    }

   
    public void closeConnection() {
        try {
            cnx.close();
            instance = null;
            System.out.println("DB Closed");
        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());
            
        }
    }
  

}
