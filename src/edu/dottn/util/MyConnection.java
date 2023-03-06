
package edu.dottn.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author WAFA
 */


  
    // JDBC URL, username and password

public class MyConnection {
   // JDBC URL, username and password

    private  final String url = "jdbc:mysql://localhost:3306/troctndb";
    private  final String user = "root";
    private  final String password = "";
  
    // JDBC  managing connection
    private  Connection con;
   
    private  static MyConnection instance = null;


    private MyConnection() {
        try {
            // opening database
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Successfully connected to MySQL database");
        } catch (SQLException sqlEx) {
            
            System.err.println(sqlEx.getMessage());


        }
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }


}
