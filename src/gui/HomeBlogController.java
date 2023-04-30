package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeBlogController {

    @FXML
    private Button btnBlogList;

    @FXML
    private Button btnAddBlog;

    @FXML
    private Button btnBlogListUser;

    @FXML
    private void navigateToBlogList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/BlogList.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnBlogList.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void navigateToAddBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/AddBlog.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnAddBlog.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void navigateToBlogListUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/BlogListUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnBlogListUser.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
