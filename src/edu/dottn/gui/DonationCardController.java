/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.dottn.gui;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import edu.dottn.entities.Donation;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Saif
 */
public class DonationCardController implements Initializable {

    @FXML
    private Label donaterUsername;
    @FXML
    private AnchorPane root;
    @FXML
    private Label donationDate;
    
    public AnchorPane getRoot() {
        
        return root;
    }

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData( Donation item) {
    // Set the data for the post
   Label titleLabel = (Label) root.lookup("#donaterUsername");
   Label datelabel = (Label) root.lookup("#donationDate");
    //Label date = (Label) root.lookup("donationDate");
     //  date.setText(time.toString());
      titleLabel.setText(item.getUser().getName());
      datelabel.setText(item.getProduct().getName());
   //   datelabel.setText(item.getDateDonation().toString());
   
}
    
}
