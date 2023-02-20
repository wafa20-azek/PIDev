/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import edu.dottn.entities.Offre;
import edu.dottn.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/*
 * @author bochr
 */
// tout se qui est select nekhdmouh b exectuteQuery w exectute update nikhdmouha lil kol ajouter supprimer modifier
public class ServiceOffre implements Oservice<Offre> {

    Connection con = MyConnection.getInstance().getCon();
    //ajouter un offre

    @Override
    public void ajouterOffre(Offre o) {
        try {
            String req = "INSERT INTO `offre`(`ID_Product`, `idUser`, `name`,`date_offre`,`status`) VALUES (?,?,?,?,?)";
            PreparedStatement pso = con.prepareStatement(req);
//            Offre o = new Offre(pso.setInt(2,o.getID_Product()),pso.setInt(3,o.getIdUser()),pso.setDate(4,(Date) o.getDate_offre()));
            pso.setInt(1, o.getID_Product());
            pso.setInt(2, o.getIdUser());
            pso.setString(3, o.getName());
            pso.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            pso.setString(5, o.getStatus());

            pso.executeUpdate();
            System.out.println("true");
//          Statement of= con.createStatement();//statement tekho req sql w tbadalha b langage mtaa driver
//          of.executeUpdate(req);
            System.out.println("Offre Created !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
//supprimer

    @Override
    public void supprimerOffre(int id_Offre) {

        try {
            String req = "DELETE FROM `offre` WHERE  `id_offre` = " + id_Offre;
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println(" Offer deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//modifier

    @Override
    public void modifierOffre(Offre o, String sta) {
        try {
            String req = "UPDATE `offre` SET `ID_Product`='" + o.getID_Product() + "',`idUser`='" + o.getIdUser() + "', `date_offre`='" + o.getDate_offre() + "',`status`='" + sta + " ' WHERE `id_offre`='" + o.getId_Offre() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Offre> getBYStatus(String status) {
        List<Offre> offre = null;
        try {

            String req = "SELECT * FROM `offre`";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(req);
//            System.out.println(test);
            while (rs.next()) {
                Offre of = new Offre(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(6));
                if (of.getStatus().equals(status)) {
//                    offre.add(of);
                    System.out.println(of);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offre;
    }

    @Override
//
    public Offre getOneById(int id_Offre) {
        Offre o = null;
        try {
            String req = "SELECT * FROM `offre` WHERE id_Offre = " + id_Offre;
            PreparedStatement st = con.prepareStatement(req);
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                o = new Offre(rst.getInt(2), rst.getInt(3), rst.getString(4), rst.getString(6));
                System.out.println("Offer getted ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return o;
    }

    @Override
    public List<Offre> getAll() {
        List<Offre> list = new ArrayList<>();
        try {
            String req = "Select * from offre";
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Offre o = new Offre(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(6));
                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    //rechercher des offres selon idUser 
    public List<Offre> getByName(String name) {
        List<Offre> rech = null;
        try {
            rech = this.getAll()
                    .stream()
                    .filter(t -> t.getName().equals(name))
                    .collect(Collectors.toList());
            System.out.println("les user sont : " + rech);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rech;

    }

    //rechercher des offres selon le produit
    public List<Offre> getByProduct(int ID_Product) {
        List<Offre> rech1 = null;
        try {
            rech1 = this.getAll()
                    .stream()
                    .filter(o -> o.getID_Product() == ID_Product)
                    .collect(Collectors.toList());
            System.out.println("les recherches de product sont" + rech1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rech1;

    }

    //trier un offre par status (les offres qui ont reste On Hold
    public List<Offre> sortByDate() {
        List<Offre> tr = null;
        try {
            tr = this.getAll().stream()
                    .sorted(Comparator.comparing(Offre::getDate_offre))
                    .collect(Collectors.toList());
            System.out.println("les offres on hold sont " + tr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tr;
    }

}
//    public List<Offre> rechercherOffre(List<Offre> offres, int idProduct, int idUser) {
//    return offres.stream()
//        .filter(offre -> offre.getID_Product() == idProduct && offre.getIdUser() == idUser)
//        .collect(Collectors.toList());

//    public List<Offre> RecherchOffre(List<Offre> offre,int id_product,int idUser) {
//        
//        return offre.stream().filter(t->t.getID_Product()||t.getIdUser()).forEach((t) -> System.out.println(t));
//       
//    }
//accepter
//    @Override
//    public void AccepterOffre(Offre o) {
//        try {
//            String req = "SELECT * FROM `offre` WHERE status = "+o.getStatus();
////            Statement st = con.createStatement();
//             PreparedStatement pst= con.prepareStatement(req);
//           
//            pst.executeUpdate();
//            System.out.println("Offer accepted !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
//    //refuser
//
//    @Override
//    public void RefuserOffer(Offre o) {
//        try {
//            String req = "SELECT * FROM `offre` WHERE 1 ";
//            Statement st = con.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Offer Denied ");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
////
