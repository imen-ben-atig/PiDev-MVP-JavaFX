/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Gui;


import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Service.Produitservice;
import Entity.Produit;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

    @FXML
private TableView<Produit> tableView;
@FXML
private TableColumn<Produit, String> columnNomProduit;
@FXML
private TableColumn<Produit, Float> columnPrix;
@FXML
private TableColumn<Produit, String> columnDescription;
@FXML
private TableColumn<Produit, Integer> columnRating;
@FXML
private TableColumn<Produit, Integer> columnStock;
@FXML
private TableColumn<Produit, String> columnImg;

    private Label labelnom;
    @FXML
    private Button btn;
    @FXML
    private TextField tfn;
    @FXML
    private TextField tfp;
    @FXML
    private TextField tfs;
    @FXML
    private TextField tfr;
    @FXML
    private TextField tfi;
    @FXML
    private TextField tfd;

    public void setLabelnom(String labelnom) {
        this.labelnom.setText(labelnom);
    }
    
    @Override
public void initialize(URL url, ResourceBundle rb) {

}
    @FXML
private void ajouter(ActionEvent event) throws IOException {
    Produitservice ps = new Produitservice();
    
    // Create a new Produit object with the values entered in the text fields
    Produit p = new Produit(tfn.getText(), 
                             Float.parseFloat(tfp.getText()), 
                             tfd.getText(), 
                             Integer.parseInt(tfs.getText()), 
                             Integer.parseInt(tfr.getText()), 
                             tfi.getText());
    
    // Insert the new Produit into the database
    ps.insertPrd(p);

    // Clear the text fields
    tfn.clear();
    tfp.clear();
    tfd.clear();
    tfs.clear();
    tfr.clear();
    tfi.clear();
    
    // Display a message to indicate that the product was successfully added
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Ajout de produit");
    alert.setHeaderText(null);
    alert.setContentText("Le produit a été ajouté avec succès !");
    alert.showAndWait();
}
@FXML
private void update(ActionEvent produit) throws IOException {
    Produitservice ps = new Produitservice();

    // Create a new Produit object with the values entered in the text fields
    Produit p = new Produit(tfn.getText(),
            Float.parseFloat(tfp.getText()),
            tfd.getText(),
            Integer.parseInt(tfs.getText()),
            Integer.parseInt(tfr.getText()),
            tfi.getText());

    // Update the existing Produit in the database
    ps.update(p);

    // Clear the text fields
    tfn.clear();
    tfp.clear();
    tfd.clear();
    tfs.clear();
    tfr.clear();
    tfi.clear();

    // Display a message to indicate that the product was successfully updated
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Mise à jour du produit");
    alert.setHeaderText(null);
    alert.setContentText("Le produit a été mis à jour avec succès !");
    alert.showAndWait();
}
@FXML
private void delete(ActionEvent produit) throws IOException {
    Produitservice ps = new Produitservice();

    // Get the id of the product to be deleted
    int id = Integer.parseInt(tfn.getText());

    // Delete the product from the database
    ps.delete(id);

    // Clear the text fields
    tfn.clear();

    // Display a message to indicate that the product was successfully deleted
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Suppression de produit");
    alert.setHeaderText(null);
    alert.setContentText("Le produit a été supprimé avec succès !");
    alert.showAndWait();
}

}
