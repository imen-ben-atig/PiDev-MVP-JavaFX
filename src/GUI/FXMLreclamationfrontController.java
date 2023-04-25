 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import Service.ServiceReclamation;
import gamegalaxy1.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author winxspace
 */
public class FXMLreclamationfrontController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private TextField tftitre;
    @FXML
    private TextArea tadesc;
    @FXML
    private ImageView img;
    @FXML
    private TextField tftype;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfstatut;
    @FXML
    private TextField tfusername;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tfdate.setValue(LocalDate.now());
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
          if (controleDeSaisie().length() > 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur ajout reclamation");
        alert.setContentText(controleDeSaisie());
        alert.showAndWait();
    } else {
        LocalDate currentDate = LocalDate.now();
        LocalDate localDate = tfdate.getValue();
        if (localDate.compareTo(currentDate) < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout reclamation");
            alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
            alert.showAndWait();
        } else {
        Reclamation r = new Reclamation();
        r.setTitre_rec(tftitre.getText());
        r.setContenu_rec(tadesc.getText());
        r.setType_rec(tftype.getText());
        r.setStatut_rec(tfstatut.getText());
        r.setUsername(tfusername.getText());
        Date date = Date.valueOf(localDate);
        r.setDate_rec(date);
        
        ServiceReclamation sr = new ServiceReclamation();
        sr.ajouter(r);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setContentText("ajout reclamation avec succes");
            alert.showAndWait();
               tftitre.clear();
        tadesc.clear();
        tftype.clear();
        tfdate.setValue(null);
        tfstatut.clear();
        tfusername.clear();
      
        }
        
    }
    }
       public String controleDeSaisie(){
        String erreur="";
        if(tftitre.getText().trim().isEmpty()){
            erreur+="Titre vide!\n";
        }
        if(tftype.getText().trim().isEmpty()){
            erreur+="Type vide!\n";
        }
        if(tfusername.getText().trim().isEmpty()){
            erreur+="Username vide!\n";
        }
        if(tfstatut.getText().trim().isEmpty()){
            erreur+="Status vide!\n";
        }
        if(tadesc.getText().trim().isEmpty()){
            erreur+="Description vide!\n";
        }
        return erreur;
    }

    @FXML
    private void gotousergstreclam(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLgstreclamationfront.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reclamation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
