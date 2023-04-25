/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import Entite.Statut;
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
import java.util.stream.Collectors;
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
import javafx.scene.control.ComboBox;
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
    private ComboBox<String> statut;
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
    private void tri(ActionEvent event) {
            cid.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
    ctitre.setCellValueFactory(new PropertyValueFactory<>("titre_rec"));
    ctype.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
    cstatus.setCellValueFactory(new PropertyValueFactory<>("statut_rec"));
    csusername.setCellValueFactory(new PropertyValueFactory<>("username"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rec"));
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
            Reclamation p=new Reclamation();
        ServiceReclamation sp = new ServiceReclamation();
    }
@FXML
private void display(ActionEvent event) {
    Reclamation selectedReclamation = tvtype.getSelectionModel().getSelectedItem(); // Get selected item from table view
    if (selectedReclamation != null) {
        // Display selected row data in text fields
       
        tftitre.setText(selectedReclamation.getTitre_rec());
        tftype.setText(selectedReclamation.getType_rec());
        tfusername.setText(selectedReclamation.getUsername());
        tfdescription.setText(selectedReclamation.getContenu_rec());
        tfdate.setValue(selectedReclamation.getDate_rec().toLocalDate());
    }
}
    @FXML
private void rechercher(ActionEvent event) {
    String recherche = tfrecherche.getText();
    // Call the search method in your ServiceReclamation class to search for Reclamation objects
    // Replace "str" with the appropriate instance of your ServiceReclamation class
    List<Reclamation> result = str.search("titre_rec", recherche); 
    List<Reclamation> result1 = str.search("type_rec", recherche);
    List<Reclamation> result2 = str.search("username", recherche);

    // Update table view with search results
    tvtype.setItems(FXCollections.observableArrayList(result));
    tvtype.setItems(FXCollections.observableArrayList(result1));
    tvtype.setItems(FXCollections.observableArrayList(result2));
}
@FXML
private void modifier(ActionEvent event) {
    
    Reclamation selectedReclamation = tvtype.getSelectionModel().getSelectedItem(); // Get selected item from table view
    if (selectedReclamation != null) {
        // Update selected row data with values from text fields
        selectedReclamation.setTitre_rec(tftitre.getText());
        selectedReclamation.setType_rec(tftype.getText());
        selectedReclamation.setStatut_rec(Statut.EN_ATTENTE);
        selectedReclamation.setUsername(tfusername.getText());
        selectedReclamation.setContenu_rec(tfdescription.getText());
        selectedReclamation.setDate_rec(Date.valueOf(tfdate.getValue()));

        // Call your update method to update the reclamation data in the database
        // replace with the appropriate method call to update the data in your service/repo
        str.modifier(selectedReclamation, selectedReclamation.getId());

        // Clear text fields after update
   
        tftitre.clear();
        tftype.clear();
  
        tfusername.clear();
        tfdescription.clear();
        tfdate.getEditor().clear();

        // Refresh table view after update
        tvtype.refresh();
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
        if(tvtype.getSelectionModel().getSelectedItem()!=null){
            int id=tvtype.getSelectionModel().getSelectedItem().getId();
            sr.supprimer(id);
            displayData();
        }
    }
    @FXML
    private void displayData() {
    ObservableList<Reclamation> dataList = FXCollections.observableArrayList(str.afficher());
    cid.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
    ctitre.setCellValueFactory(new PropertyValueFactory<>("titre_rec"));
    ctype.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
    cstatus.setCellValueFactory(new PropertyValueFactory<>("statut_rec"));
    csusername.setCellValueFactory(new PropertyValueFactory<>("username"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rec"));
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
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
    cstatus.setCellValueFactory(new PropertyValueFactory<>("Statut_rec"));
    csusername.setCellValueFactory(new PropertyValueFactory<>("username"));  
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rec"));
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
