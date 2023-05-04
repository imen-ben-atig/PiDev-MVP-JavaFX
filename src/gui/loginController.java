/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The Nutorious BIG
 */
public class LoginController implements Initializable {
	private ServiceUser serviceUser;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginBtn;
	@FXML
	private Label errorLabel;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	// Called when the user clicks on the login button
	@FXML
	void login(ActionEvent event) {

		serviceUser = new ServiceUser();
		String emailText = email.getText();
		String passwordText = password.getText();
		if (!emailText.isEmpty() || !passwordText.isEmpty()) {
			if (serviceUser.login(emailText, passwordText)) // Returns true if email & password are verified & if user is enabled
			{
				// Load the home page
				try {
					Parent root = FXMLLoader.load(getClass().getResource("AcceuilUser.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();

				}

			}
		} else {
			// Update errorLabel
			errorLabel.setText("Email or password is incorrect");
			// Change color of errorLabel
			errorLabel.setStyle("-fx-text-fill: red");
		}

	}
}
