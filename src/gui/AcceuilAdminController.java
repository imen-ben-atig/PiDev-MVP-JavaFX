/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
  */
public class AcceuilAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   @FXML
    public void openModifInterface3(ActionEvent event) {
        try {
        	  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDesUser_admins.fxml"));
            Parent root = loader.load();

            // Show the Modif.fxml interface
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
            } catch (IOException ex) {
                ex.printStackTrace();

             }
    }
        @FXML
        public void openModifInterface2(ActionEvent event) {
            try {
            	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 		        stage.close();
                // Load the Modif.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterUser_admin.fxml"));
                Parent root = loader.load();

                // Show the Modif.fxml interface
                Scene scene = new Scene(root);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.show();

            } catch (IOException e) {
                e.printStackTrace();
            }}
        
   
    @FXML
    public void openModifInterface4(ActionEvent event) {
        try {
            // Load the Modif.fxml file
        	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeUsers.fxml"));
            Parent root = loader.load();

            // Show the Modif.fxml interface
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
