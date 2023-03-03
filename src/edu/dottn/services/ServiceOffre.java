/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.services;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import edu.dottn.entities.Offre;
import edu.dottn.util.MyConnection;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import com.itextpdf.text.Document;

import javax.swing.text.Position;
import javax.swing.text.Segment;
import static sun.misc.MessageUtils.where;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Phrase;
import java.awt.Desktop;
import java.io.File;

import java.io.FileNotFoundException;

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;

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
            String req = "INSERT INTO `offre`(`ID_Product`, `idUser`,`ID_Product1`, `idUser1`, `name`,`date_offre`,`status`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pso = con.prepareStatement(req);
//            Offre o = new Offre(pso.setInt(2,o.getID_Product()),pso.setInt(3,o.getIdUser()),pso.setDate(4,(Date) o.getDate_offre()));
            pso.setInt(1, o.getID_Product());
            pso.setInt(2, o.getIdUser());
            pso.setInt(3, o.getID_Product1());
            pso.setInt(4, o.getIdUser1());
            pso.setString(5, o.getName());
            pso.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
            pso.setString(7, o.getStatus());
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
    public void modifierOffre(Offre o) {
        try {
            String req = "UPDATE `offre` SET `ID_Product`='" + o.getID_Product() + "',`idUser`='" + o.getIdUser() + "',`ID_Product1`='" + o.getID_Product1() + "',`idUser1`='" + o.getIdUser1() + "',`date_offre`='" + o.getDate_offre() + "'WHERE `status`='On_Hold' ";
//            String req = "UPDATE `offre` SET `ID_Product`='" + o.getID_Product() + "',`idUser`='" + o.getIdUser() + "', `date_offre`='" + o.getDate_offre() + "'"" WHERE`status`='On_Hold' ";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Offre> getBYStatus(String status) {
        List<Offre> offre = new ArrayList<>();
        if (status.equals("Accepted")) {

            try {

                String req = "SELECT * FROM `offre` where status='Accepted'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(req);
//            System.out.println(test);
                while (rs.next()) {

                    Offre of = new Offre(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(6), rs.getString(8));

                    offre.add(of);
//                    System.out.println(of);

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (status.equals("On_Hold")) {

            try {

                String req = "SELECT * FROM `offre` where status='On_Hold'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(req);
//            System.out.println(test);
                while (rs.next()) {

                    Offre of = new Offre(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(6), rs.getString(8));

                    offre.add(of);
//                    System.out.println(of);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (status.equals("Declined")) {

            try {

                String req = "SELECT * FROM `offre` where status='Declined'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(req);
//            System.out.println(test);
                while (rs.next()) {

                    Offre of = new Offre(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(6), rs.getString(8));

                    offre.add(of);
//                    System.out.println(of);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return offre;
    }

    @Override
//
    public Offre getOneById(int id_Offre) {

        try {
            String req = "SELECT * FROM `offre` WHERE id_Offre = " + id_Offre;
            PreparedStatement st = con.prepareStatement(req);
            ResultSet rst = st.executeQuery(req);
            while (rst.next()) {
                System.out.println("Offer getted ");
                return new Offre(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getString(4), rst.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
    public List<Offre> getByProduct(Offre o1) {
        List<Offre> rech1 = null;
        try {
            rech1 = this.getAll()
                    .stream()
                    .filter(o -> o.getID_Product() == o1.getID_Product())
                    .collect(Collectors.toList());
            System.out.println("les recherches de product sont" + rech1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rech1;

    }

    public Offre verifierOffre(Offre o) {
        try {
            String req = "SELECT * FROM `offre` WHERE EXISTS (SELECT * FROM `offre` WHERE ID_Product=? AND ID_Product1=? AND idUser=? AND idUser1=?) ";

            PreparedStatement pst = con.prepareStatement(req);
            pst.setInt(1, o.getID_Product());
            pst.setInt(2, o.getID_Product1());
            pst.setInt(3, o.getIdUser());
            pst.setInt(4, o.getIdUser1());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Offre o1 = new Offre(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));

                return o1;

            }
            System.out.println("offre created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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

    public void AccepterOffre(Offre o) {
        try {
            String req = "UPDATE `offre` SET `status`='" + "Accepted" + " ' WHERE `id_offre`='" + o.getId_Offre() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //refuser
    public void RefuserOffer(Offre o) {
        try {
            String req = "UPDATE `offre` SET `status`='" + "Declined" + " ' WHERE `id_offre`='" + o.getId_Offre() + "'";
            Statement st = con.createStatement();
            st.executeUpdate(req);
            System.out.println("Offer Declined !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void generatePDF(Offre o) {
        Document document = new Document();
        String offerFilePath = "offre.pdf";

        try {
            String req = "SELECT * FROM `offre` WHERE  id_offre = " + o.getId_Offre();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(req);

            // Loop through the result set and add data to PDF
            while (resultSet.next()) {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(offerFilePath));
                document.open();
                document.addTitle("My offre");
                Image logo = Image.getInstance("src/img/Logo.png");
                logo.setAbsolutePosition(20, 770);
                logo.scaleToFit(150, 150);
                addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
                document.add(logo);
                Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.UNDERLINE, BaseColor.BLUE);
                Paragraph p = new Paragraph("My offer ", f);
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                addEmptyLine(document, 2);
                Paragraph paragraph = new Paragraph("Cher(e) client(e) " + o.getName() + ",");
                document.add(paragraph);
                addEmptyLine(document, 1);
                Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.UNDEFINED, BaseColor.BLACK);
                paragraph = new Paragraph("Nous vous remercions pour vos echange sur TrocTn Votre echange" + o.getId_Offre() + " a été confirmée avec succès.", f1);
                addEmptyLine(paragraph, 1);
                document.add(paragraph);
                Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.UNDEFINED, BaseColor.BLACK);
                paragraph = new Paragraph("Vous avez accepté l'offre de " + o.getIdUser1() + "de produit " + o.getID_Product1() + "de la date " + o.getDate_offre() + ".", f2);
                addEmptyLine(paragraph, 1);
                document.add(paragraph);
                PdfPTable table = new PdfPTable(2); // Nombre de colonnes
                table.setWidthPercentage(100); // Largeur de la table
                table.setSpacingBefore(10f); // Espace avant la table
                table.setSpacingAfter(10f); // Espace après la table
                // Ajouter les cellules
                PdfPCell cell;
                cell = new PdfPCell(new Phrase("Détails du destinataire" +o.getName()+""));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Adresse Client"));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                 addEmptyLine(paragraph, 2);
                 Font f3= new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.UNDEFINED, BaseColor.BLACK);
                paragraph = new Paragraph("Happy shopping ", f3);
                addEmptyLine(paragraph, 1);
                document.add(paragraph);
                Image logo1= Image.getInstance("src/img/signature.png");
                logo.setAbsolutePosition(20, 770);
                logo.scaleToFit(150, 150);
                addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
                document.add(logo1);
                Image logo2= Image.getInstance("src/img/telecharger.png");
                logo.setAbsolutePosition(20, 770);
                logo.scaleToFit(150, 150);
                addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
                document.add(logo2);
                
                document.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

}
