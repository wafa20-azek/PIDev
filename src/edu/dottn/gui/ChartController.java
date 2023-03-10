/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import edu.dottn.entities.User;
import edu.dottn.services.AssociationServices;
import edu.dottn.services.MemberServices;
import edu.dottn.util.MyConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ChartController implements Initializable {
    MyConnection cnx = MyConnection.getInstance();
    AssociationServices as = new AssociationServices();
   
    private Scene scene;
    
    @FXML
    private BarChart<?, ?> barChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //chart(scene,3);
    }  
    
    public void chart(Scene scene , int id){
        MemberServices ms = new MemberServices();
        try{
            String sql = "SELECT *, COUNT(*) AS num_posts  FROM donation WHERE idAssociation = ? GROUP BY idUser ";
            PreparedStatement stmt = cnx.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               User us = ms.getOneById(rs.getInt("idUser"));
                int num = rs.getInt("num_posts");
                 XYChart.Series ChartData = new XYChart.Series<>();
            ChartData.getData().add(new XYChart.Data<>(String.valueOf(us.getName()),num));
          barChart.getData().add(ChartData);
            
            }
              
             BorderPane root = new BorderPane(barChart);
    
        Scene scene1 = new Scene(root, 680 , 400);
  
         Stage stage = new Stage(); 
        stage.setScene(scene1);
        stage.show();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
}
