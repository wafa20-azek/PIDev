/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.util;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author WAFA
 */
public class MyConnection {
    

      private final String USER = "root";
      private final String PWD = "";
      private final String URL = "jdbc:mysql://localhost:3306/troctndb";

    private static MyConnection instance;
    // JDBC  managing connection
    private  Connection con;
    
    
      private MyConnection() {
            try {
                con= DriverManager.getConnection(URL, USER, PWD);
                System.out.println("Connection established");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    
    public static MyConnection getInstance() {
        if (instance == null) {
            return instance = new MyConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return con;
    }
}
