package gamegalaxy_evenement.reservation;

import Entities.Evenement;
import Services.EvenementService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Evenement> tableview;

    @FXML
    private TableColumn<Evenement, Integer> colid;

    @FXML
    private TableColumn<Evenement, String> colnom;

    @FXML
    private TableColumn<Evenement, String> coldesc;

    @FXML
    private TableColumn<Evenement, Integer> colduree;

    @FXML
    private TableColumn<Evenement, Integer> colcap;

    @FXML
    private TableColumn<Evenement, String> coltype;
    @FXML
    private TableColumn<Evenement, String> coldate;

    @FXML
    private TextField IDTextfield;
    @FXML
    private TextField NOM;
    @FXML
    private TextField DATE;
    @FXML
    private TextArea DESC;
    @FXML
    private TextField DUREE;
    @FXML
    private TextField CAP;
    @FXML
    private TextField TYPE;
    // File selection
    @FXML
    private Label IMG;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private VBox inputFields;
    @FXML
    private DatePicker DATEPICKER;
    @FXML
    private Spinner<Integer> DUREE_SPINNER;
    @FXML
    private Spinner<Integer> CAP_SPINNER;
    @FXML
    private ChoiceBox<String> TYPE_CB;

    private ObservableList<Evenement> observableList;

    @FXML
    private Button showBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load events from database into the tableview
        EvenementService dao = new EvenementService();
        try {
            // Get the ArrayList from the service
            ArrayList<Evenement> evenements = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(evenements);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set cell value factories for the table columns
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colcap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
        DATEPICKER.setValue(java.time.LocalDate.now());
        IDTextfield.setDisable(true);
        // Set ChoiceBox options (string values) and default string value: Choices are:
        // Social, Gaming, Tournament, Meetup. Default is value: Social
        TYPE_CB.getItems().addAll("Social", "Gaming", "Tournament", "Meetup");
        TYPE_CB.setValue("Social");
        showBtn.setDisable(true);

        // Set Spinner values and default value: 1, default value: 1
        DUREE_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 4));
        CAP_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 500, 100));

        // Listen for selection changes and show the evenement details when changed.
        // newSelection is the newly selected item, or null if there is no selection, it
        // is used to update the textfield, in method updateItem
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateButton.setDisable(true);
                deleteButton.setDisable(false);
                addButton.setDisable(false);
                IDTextfield.setDisable(true);
                IDTextfield.setText(String.valueOf(newSelection.getId()));
                NOM.setText(newSelection.getNom());
                DATEPICKER.setValue(java.time.LocalDate.parse(newSelection.getDate()));
                DESC.setText(newSelection.getDescription());
                DUREE_SPINNER.setValueFactory(
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, newSelection.getDuree()));
                CAP_SPINNER.setValueFactory(
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 500, newSelection.getCapacite()));
                TYPE_CB.setValue(newSelection.getType());
                IMG.setText(newSelection.getImage());
            }
        });

    }

    @FXML
    private void closeApp(ActionEvent event) {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            IMG.setText(selectedFile.getName());
            // To create a folder in the project directory called "Uploads" if folder
            // doesn't already exist and copy the image
            File dir = new File("Uploads\\Evenement");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File dest = new File(dir, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // A function to show the selected entry in a new window
    // "ShowEvenement_Window.fxml"
    @FXML
    private void show(ActionEvent event) {
        Evenement selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowEvenement_Window.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ShowEvenement_WindowController controller = loader.getController();

                controller.initData(selectedEvent);

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void addItem(ActionEvent event) {
        if (checkInput() && checkIfDateIsPrior()) {
            EvenementService dao = new EvenementService();

            String nom = NOM.getText();
            // DatePicker formatted as yyyy-mm-dd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = DATEPICKER.getValue().format(formatter).toString();
            String desc = DESC.getText();
            int duree = Integer.parseInt(DUREE_SPINNER.getValue().toString());
            int cap = Integer.parseInt(CAP_SPINNER.getValue().toString());
            String type = TYPE_CB.getValue();
            String img = IMG.getText();

            Evenement evenement = new Evenement(nom, date, desc, duree, cap, type, img);

            try {
                dao.ajouter(evenement);

                tableview.getItems().clear();

                refresh();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void deleteItem() {
        EvenementService dao = new EvenementService();
        Evenement selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            try {
                dao.delete(selectedEvent.getId());
                tableview.getItems().remove(selectedEvent);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                // Get the ArrayList from the service
                ArrayList<Evenement> evenements = dao.afficherAll();

                // Convert the ArrayList to an ObservableList
                observableList = FXCollections.observableArrayList(evenements);

                // Set the ObservableList as the data source for the TableView
                tableview.setItems(observableList);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableview.refresh();
            deselectItem();
        }
    }

    // selectItem(): When row is selected, Get the selected row and set the values
    // of the selected row to the input fields.
    @FXML
    private void selectItem() {
        // Get the selected row
        Evenement selectedEvent = tableview.getSelectionModel().getSelectedItem();
        // If there is a selected row
        if (selectedEvent != null) {
            // Enable the update button
            updateButton.setDisable(false);
            // Get the values from the selected row
            int id = selectedEvent.getId();
            String nom = selectedEvent.getNom();
            String date = selectedEvent.getDate();
            String desc = selectedEvent.getDescription();
            int duree = selectedEvent.getDuree();
            int cap = selectedEvent.getCapacite();
            String type = selectedEvent.getType();
            String img = selectedEvent.getImage();

            // Set the values of the selected row to the input fields
            IDTextfield.setText(String.valueOf(id));
            NOM.setText(nom);
            DATEPICKER.setValue(java.time.LocalDate.parse(date));
            DESC.setText(desc);
            DUREE_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, duree));
            CAP_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 500, cap));
            TYPE_CB.setValue(type);
            IMG.setText(img);

            // Disable add button
            addButton.setDisable(true);
            showBtn.setDisable(false);
        }
    }

    // deselect(): Method called when deselectBtn is clicked, it deselects the
    // selected row.
    @FXML
    private void deselectItem() {
        // Clear the selection
        tableview.getSelectionModel().clearSelection();
        // Disable the update, del, add buttons

        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);

        // Clear the input fields
        IDTextfield.setText("");
        NOM.setText("");
        DESC.setText("");
        DUREE_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1));
        CAP_SPINNER.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 500, 50));
        TYPE_CB.setValue("Social");
        IMG.setText("");
        // Set datepicker to today
        DATEPICKER.setValue(java.time.LocalDate.now());

        // Set add button text to "Dupliquer"
        addButton.setText("Add");
        showBtn.setDisable(true);

    }

    @FXML
    private void updateItem() {
        Evenement selectedEvent = tableview.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            EvenementService dao = new EvenementService();
            String nom = NOM.getText();
            // Pick date from datepicker fxid=DATEPICKER formatted as yyyy-mm-dd
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = DATEPICKER.getValue().format(formatter).toString();
            String desc = DESC.getText();
            int duree = Integer.parseInt(DUREE_SPINNER.getValue().toString());
            int cap = Integer.parseInt(CAP_SPINNER.getValue().toString());
            String type = TYPE_CB.getValue();
            String img = IMG.getText();

            Evenement updatedEvent = new Evenement(nom, date, desc, duree, cap, type, img);
            updatedEvent.setId(selectedEvent.getId());
            try {
                dao.update(updatedEvent);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            deselectItem();
            refresh();
        }
    }

    // Method checking if the input fields are empty and if integers and strings are
    // respected
    private Boolean checkInput() {
        String nom = NOM.getText();
        String desc = DESC.getText();
        String duree = DUREE_SPINNER.getValue().toString();
        String cap = CAP_SPINNER.getValue().toString();
        String type = TYPE_CB.getValue();
        String img = IMG.getText();
        // if any of the input fields are empty, andshow an error message
        if (nom.isEmpty() || desc.isEmpty() || duree.isEmpty() || cap.isEmpty() || type.isEmpty() || img.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Fields are empty.");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    @FXML
    private Boolean checkIfDateIsPrior(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = DATEPICKER.getValue().format(formatter).toString();
        LocalDate date1 = LocalDate.parse(date);
        LocalDate date2 = LocalDate.now();
        if(date1.isBefore(date2)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Date is prior to today.");
            alert.setContentText("Please choose a date after today.");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }

    @FXML
    private void refresh() {
        EvenementService dao = new EvenementService();
        try {
            // Get the ArrayList from the service
            ArrayList<Evenement> evenements = dao.afficherAll();

            // Convert the ArrayList to an ObservableList
            observableList = FXCollections.observableArrayList(evenements);

            // Set the ObservableList as the data source for the TableView
            tableview.setItems(observableList);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.refresh();
    }
}
