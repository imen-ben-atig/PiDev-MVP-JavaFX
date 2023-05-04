/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author The Nutorious BIG
 */
public class AuthentificationController implements Initializable {
    @FXML
    private Hyperlink Lconnexion;
    @FXML
    private Hyperlink Linscription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void pagelogin(ActionEvent event)
    {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    
    @FXML
    void pageinscription(ActionEvent event)
    {
          
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/Inscription.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    }
    

