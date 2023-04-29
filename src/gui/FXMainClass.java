package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXMainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/HomeBlog.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Exclusive Gaming Blogs");
        Image AppIcon = new Image("imagezz/mvp.png");
        stage.getIcons().add(AppIcon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
