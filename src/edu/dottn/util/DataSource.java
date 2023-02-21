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
 * @author ProInfo
 */
public class DataSource {
    private Connection cnx ;
    private static DataSource instance;
    private final String USER ="root" ;
    private final String PWS ="";
    private final String URL ="jdbc:mysql://localhost:3306/pidev";

    private DataSource(){
        try {
            cnx= DriverManager.getConnection(URL, USER, PWS);
            System.out.println("Connected to DB");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    public Connection getCnx() {
        return cnx;
    }
    public static DataSource getInstance(){
        if(instance==null)
            instance = new DataSource();
        return instance;
    }
    }
    

