/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Service.MyListener;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import Service.ServiceReclamation;
import com.itextpdf.text.PageSize;
import gamegalaxy1.FXMain;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Element;
import com.itextpdf.text.Chunk;
import javafx.scene.control.TableView;
import Service.ServiceReclamation;
import gamegalaxy1.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author winxspace
 */
public class FXMLgstreclamationuserController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitre;
    @FXML
    private TextArea tfdescription;
    @FXML
    private GridPane grid;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Label idgetter;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfstatut;
    @FXML
    private TextField tfusername;
    @FXML
    private DatePicker tfdate;
     @FXML
    private TableView<Reclamation> tvtype;
      ServiceReclamation str=new ServiceReclamation();
    ObservableList<String> data=FXCollections.observableArrayList();
        @FXML
    private TableColumn<Reclamation, Integer> cid;
    @FXML
    private TableColumn<Reclamation, String> ctitre;
    @FXML
    private TableColumn<Reclamation, Date> ctype;
    @FXML
    private TableColumn<Reclamation, String> cstatus;
    @FXML
    private TableColumn<Reclamation, String> csusername;
    @FXML
    private TableColumn<Reclamation, String> cdesc;
    @FXML
    private TableColumn<Reclamation, Date> cdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfdate.setValue(LocalDate.now());
    }    

@FXML
private void modifier(ActionEvent event) {
       Reclamation selectedReclamation = (Reclamation) tvtype.getSelectionModel().getSelectedItem();
    if (selectedReclamation == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row to update!");
        alert.showAndWait();
        return;
    }
    if(controleDeSaisie().length() > 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur modification réclamation");
        alert.setContentText(controleDeSaisie());
        alert.showAndWait();
    } else {
    // Get the updated values from the UI controls
    System.out.println("test");
    String titreRec = tftitre.getText();
    String typeRec = tftype.getText();
    String statusRec = tfstatut.getText();
    String contenuRec = tfdescription.getText();
    String Username = tfusername.getText();
    LocalDate localDate = tfdate.getValue();
    Date dateRec = Date.valueOf(localDate);
    
    // Update the selected Reclamtion object
    selectedReclamation.setTitre_rec(titreRec);
    selectedReclamation.setType_rec(typeRec);
    selectedReclamation.setUsername(Username);
    selectedReclamation.setStatut_rec(statusRec);
    selectedReclamation.setContenu_rec(contenuRec);
    selectedReclamation.setDate_rec(dateRec);
    
    // Call the update method and refresh the table view
    str.modifier(selectedReclamation, 1);
    displayData();
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
        if(tfdescription.getText().trim().isEmpty()){
            erreur+="Description vide!\n";
        }
        return erreur;
    }
    @FXML
    private void supprimer(ActionEvent event) throws Exception {
        ServiceReclamation sr = new ServiceReclamation();
        sr.supprimer(Integer.valueOf(tfid.getText()));
        displayData();
    }
    @FXML
    private void displayData() {
    ObservableList<Reclamation> dataList = FXCollections.observableArrayList(str.afficher());
    cid.setCellValueFactory(new PropertyValueFactory<>("id"));
    ctitre.setCellValueFactory(new PropertyValueFactory<>("titre_rec"));
    ctype.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
    cstatus.setCellValueFactory(new PropertyValueFactory<>("statut_rec"));
    csusername.setCellValueFactory(new PropertyValueFactory<>("username"));  
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rep"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rep"));
    tvtype.setItems(dataList);
}
@FXML
private void generatepdf(ActionEvent event) {
    // Get the data from the form fields
    String titre = tftitre.getText();
    String type = tftype.getText();
    String description = tfdescription.getText();
    String statut = tfstatut.getText();
    String username = tfusername.getText();
    LocalDate date = tfdate.getValue();

    // Create a PDF document
    try {
        // Specify the file name and path for the PDF file
        String fileName = "reclamation.pdf";
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);

        // Create a Document object
        Document document = new Document(PageSize.A4, 50, 50, 50, 50); // Set page size and margins
        PdfWriter writer = PdfWriter.getInstance(document, fos);

        // Open the document
        document.open();

        // Add the form field data to the document with formatting
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE);
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph title = new Paragraph("Réclamation", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Chunk("\n"));
        document.add(new Paragraph("Titre: ", labelFont));
        document.add(new Paragraph(titre, valueFont));

        document.add(new Paragraph("Type: ", labelFont));
        document.add(new Paragraph(type, valueFont));

        document.add(new Paragraph("Description: ", labelFont));
        document.add(new Paragraph(description, valueFont));

        document.add(new Paragraph("Statut: ", labelFont));
        document.add(new Paragraph(statut, valueFont));

        document.add(new Paragraph("Username: ", labelFont));
        document.add(new Paragraph(username, valueFont));

        document.add(new Paragraph("Date: ", labelFont));
        document.add(new Paragraph(date.toString(), valueFont));

        // Close the document
        document.close();

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réclamation");
        alert.setContentText("Génération du PDF effectuée avec succès");
        alert.showAndWait();
    } catch (DocumentException | IOException ex) {
        Logger.getLogger(FXMLgstreclamationuserController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    


    
  @FXML
private void tri(ActionEvent event) {

    ServiceReclamation sr = new ServiceReclamation();
    List<Reclamation> reclamations = sr.afficher();
    reclamations.sort((r1, r2) -> r1.getTitre_rec().compareTo(r2.getTitre_rec()));
    grid.getChildren().clear();
    int row = 1;
    for (Reclamation r : reclamations) {
        Label idLabel = new Label(String.valueOf(r.getId()));
        idLabel.setPadding(new Insets(5));
        grid.add(idLabel, 0, row);

        Label titreLabel = new Label(r.getTitre_rec());
        titreLabel.setPadding(new Insets(5));
        grid.add(titreLabel, 1, row);

        row++;
    }
}

    @FXML
    private void gotoajouterreclamation(ActionEvent event) {
           Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLreclamationfront.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reclamation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @FXML
private void afficher(ActionEvent event) {
     ObservableList<Reclamation> dataList = FXCollections.observableArrayList(str.afficher());
    cid.setCellValueFactory(new PropertyValueFactory<>("id"));
    ctitre.setCellValueFactory(new PropertyValueFactory<>("titre_rec"));
    ctype.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
    cstatus.setCellValueFactory(new PropertyValueFactory<>("statut_rec"));
    csusername.setCellValueFactory(new PropertyValueFactory<>("username"));  
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rep"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rep"));
    tvtype.setItems(dataList);
}
 @FXML
    private void repondre (ActionEvent event) {
            Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLreponseback.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reponse!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    
}
