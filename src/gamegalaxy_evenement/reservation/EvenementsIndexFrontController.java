package gamegalaxy_evenement.reservation;

import Services.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;


public class EvenementsIndexFrontController implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private HBox elementHBox;

    @FXML
    private Label nomLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Label capaciteLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label errorMsg;

    @FXML
    private Button CalendarBtn;

    @FXML
    private VBox containerVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EvenementService dao = new EvenementService();
        ArrayList<HBox> HBoxList = new ArrayList<>();
        try {
            // Get the ArrayList from the service
            ArrayList<Evenement> evenements = dao.afficherAll();
            int size = evenements.size();
            nomLabel.setText(evenements.get(0).getNom().toString());
            dateLabel.setText(evenements.get(0).getDate().toString());
            dureeLabel.setText(String.valueOf(evenements.get(0).getDuree()));
            capaciteLabel.setText(String.valueOf(evenements.get(0).getCapacite()));
            descriptionLabel.setText(evenements.get(0).getDescription());
            int i;
            for (i=1;i<size;i++) {
                try {
                    Evenement evenement=evenements.get(i);
                    // Displays the evenements in the containerVBox by spawning newElementHBox in it
                    // for each evenement and assigning the values to the labels

                    HBox newElementBox = (HBox) FXMLLoader.load(getClass().getResource("EvenementElement.fxml"));
                    // Assigns the evenement values to the labels in the newElementHBox
                    // Gets the Labels
                    Label nomLabel = (Label) newElementBox.lookup("#nomLabel");
                    Label dateLabel = (Label) newElementBox.lookup("#dateLabel");
                    Label dureeLabel = (Label) newElementBox.lookup("#dureeLabel");
                    Label capaciteLabel = (Label) newElementBox.lookup("#capaciteLabel");
                    Label descriptionLabel = (Label) newElementBox.lookup("#descriptionLabel");
                    //ImageView imageView = (ImageView) newElementBox.lookup("#imageView");

                    // Assigns the values
                    nomLabel.setText(evenement.getNom());
                    dateLabel.setText(evenement.getDate().toString());
                    dureeLabel.setText(String.valueOf(evenement.getDuree()));
                    capaciteLabel.setText(String.valueOf(evenement.getCapacite()));
                    descriptionLabel.setText(evenement.getDescription());
                    //imageView.setImage(evenement.getImage());

                    
                    //Add the newElementBox to the ArrayList
                    HBoxList.add(newElementBox);
                } catch (Exception ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //iterate through the ArrayList and add the HBoxes to the containerVBox
            for (HBox hBox : HBoxList) {
                containerVBox.getChildren().add(hBox);
            }


        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void close(ActionEvent event)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    

    }

    @FXML
    void showCalendar(ActionEvent event) {
        try {
            Stage stage = (Stage) (CalendarBtn.getScene().getWindow());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            // Get the controller and add the calendar view to it
            Controller controller = loader.getController();
            // controller.calendarPane.getChildren().add(new
            // FullCalendarView(YearMonth.now()).getView());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
