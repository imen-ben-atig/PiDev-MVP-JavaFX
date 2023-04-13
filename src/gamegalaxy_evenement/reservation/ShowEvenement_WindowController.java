package gamegalaxy_evenement.reservation;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowEvenement_WindowController implements Initializable {

    @FXML
    private Label idLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Label capaciteLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private ImageView imgDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void closeCurrentWindow() {
        imgDisplay.getScene().getWindow().hide();

    }

    public void initData(Evenement E) {
        idLabel.setText(String.valueOf(E.getId()));
        nomLabel.setText(E.getNom());
        dateLabel.setText(E.getDate());
        descriptionLabel.setText(E.getDescription());
        dureeLabel.setText(String.valueOf(E.getDuree()));
        capaciteLabel.setText(String.valueOf(E.getCapacite()));
        typeLabel.setText(E.getType());
        String absolute_path = "C:\\Users\\Hassène\\Desktop\\GameGalaxy_Evenement-Reservation\\Uploads\\Evenement\\"
                + E.getImage();
        File file = new File(absolute_path);
        Image image = new Image(file.toURI().toString());
        imgDisplay.setImage(image);

    }

}
