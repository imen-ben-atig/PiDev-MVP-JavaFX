/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author The Nutorious BIG
 */
public class TestGames  extends Application {
    	

      @Override
    public void start(Stage primaryStage) throws IOException {
        
       Parent root = FXMLLoader.load(getClass().getResource("../GUI/Authentification.fxml"));
        
         Scene scene= new Scene(root);
          
         primaryStage.setTitle("GameGalaxy");
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
