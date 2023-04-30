/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class NewFXMain1 extends Application {
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Choix de l'action");
    alert.setHeaderText("Que voulez-vous faire ?");
    alert.setContentText("Voulez-vous passer à l'application ou choisir une musique ?");

    ButtonType buttonTypeApp = new ButtonType("Aller à l'application");
    ButtonType buttonTypeMusic = new ButtonType("Choisir une musique");

    alert.getButtonTypes().setAll(buttonTypeApp, buttonTypeMusic);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == buttonTypeApp){
        // Faire quelque chose pour passer à l'application
    } else if (result.get() == buttonTypeMusic) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier audio");
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers audio (*.mp3, *.wav, *.flac)", "*.mp3", "*.wav", "*.flac");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            javafx.scene.media.Media javafxMedia = new javafx.scene.media.Media(selectedFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(javafxMedia);
            mediaPlayer.setAutoPlay(true);
        } else {
            System.out.println("Aucun fichier audio sélectionné.");
        }
    }
               Parent root = FXMLLoader.load(getClass().getResource("../gui/Acceuil.fxml"));
        Scene scene = new Scene(root,950,650); 
        primaryStage.setTitle("Bienvenue chez-nous");
        //primaryStage.setIconified(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
