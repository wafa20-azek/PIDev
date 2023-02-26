/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Produit;
import edu.dottn.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ProInfo
 */
public class ServiceProduit {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<Produit> getall() {
        List<Produit> p = new ArrayList<>();
        try {
            String req = "SELECT * FROM `produit`";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Produit prod = new Produit(
                        result.getInt("ID_Product"),
                        result.getString("Name"));
                p.add(prod);
            }

            System.out.println("row pulled");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }

}
