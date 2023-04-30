package gui;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.mindrot.jbcrypt.BCrypt;

import Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;

public class loginController  implements Initializable{
	private ServiceUser serviceUser;

    @FXML
    private TextField login;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button log;

    @FXML
    private Button regs;

    private  ServiceUser userService;
@FXML
private User user;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Associer un gestionnaire d'�v�nements au bouton de connexion
       
    }
   
	@FXML
	void login(ActionEvent event)  {
		
                 if ((login.getText().isEmpty()) || (passwordField.getText().isEmpty()) ) {
			 
			Alert alert1 = new Alert(AlertType.WARNING);
			alert1.setTitle("oops");
			alert1.setHeaderText(null);
			alert1.setContentText("remplir tous les champs ou bien votre compte est desactiver par l'administration");
			alert1.showAndWait();
			return;

		}
                  try {
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.close();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherUser.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage2 = new Stage();
               stage2.setScene(scene);
               stage2.show();
               } catch (IOException ex) {
                   ex.printStackTrace();

                }
		
        // R�cup�rer les informations de l'utilisateur et du mot de passe
        String username = login.getText();
        String password = passwordField.getText();

		serviceUser = new ServiceUser();
		String salt = BCrypt.gensalt(); // G�n�re un sel al�atoire pour renforcer la s�curit� du hachage
		
		String hashedPassword = BCrypt.hashpw(password, salt);
		String hashedPasswordFromDB = serviceUser.getHashedPasswordForUser(username);
               

		// Crypte le mot de passe avec le sel g�n�r�
        // V�rifier si l'utilisateur existe dans la base de donn�es
		System.out.println(hashedPassword);
        boolean isAuthenticated = serviceUser.login(username, hashedPassword);
        String role=serviceUser.getRole(username);
       
        if (BCrypt.checkpw(password, hashedPasswordFromDB)) {

			   Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Confirmation");
	            alert.setHeaderText(null);
	            alert.setContentText("login avec succes   " );
	            alert.showAndWait();
	            if (role.equalsIgnoreCase("admin")) {
	                // do something if the strings are equal, ignoring case
	         
	            
	            }
	            else {
	            	  try {
	  	                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	  	                stage.close();
	  	                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherUser.fxml"));
	   	                Parent root = loader.load();
	  	                Scene scene = new Scene(root);
	  	                Stage stage2 = new Stage();
	  	                stage2.setScene(scene);
	  	                stage2.show();
	  	                } catch (IOException ex) {
	  	                    ex.printStackTrace();

	  	                 }
	            }
             
        } else {
        	Alert alert1 = new Alert(AlertType.WARNING);
			alert1.setTitle("oops");
			alert1.setHeaderText(null);
			alert1.setContentText("Verifier !!!!! ");
			alert1.showAndWait();
			return;
        }
        //
    }
	
	@FXML
	void regsitre(ActionEvent event)  {
		
		   try {
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.close();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/registreUser.fxml"));
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage2 = new Stage();
               stage2.setScene(scene);
               stage2.show();
               } catch (IOException ex) {
                   ex.printStackTrace();

                }
}
 






}
