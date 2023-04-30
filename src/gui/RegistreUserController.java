package gui;

import java.io.File;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Entities.User;
import Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
public class RegistreUserController implements Initializable {

	@FXML
	private Label nom;
	@FXML
	private Button btnAjouter;
	@FXML
	private TextField urlTF;
 
	 
	
	private File file;
 	private boolean isValid(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean isvalid(String num) {
		// String phonenumberstr = Integer.toString(num);
		if (num.length() != 8) {
			return false;
		}
		for (int i = 0; i < num.length(); i++) {
			if (!Character.isDigit(num.charAt(i))) {
				return false;
			}
		}
		return !"12345678".equals(num);

	}

	@FXML
	private Button btnChoisir;
	@FXML
	private ImageView imageview;
 
	 

	@FXML
	private TextField txtemail;

	 

	@FXML
	private TextField txtnom1;
	@FXML
	private TextField txtnom2;


    @FXML
    private PasswordField passwordField; 
    @FXML
    private PasswordField passwordField2; 

	@FXML
	private TextField txtnumT;
	
	@FXML
	private Stage stage;

	@FXML
	void ajouter(ActionEvent event) {
		boolean test = false;
		String nom, email, nom2, pass, num_t ;
		nom = txtnom1.getText();
		nom2 = txtnom2.getText();
		email = txtemail.getText();
 		num_t = txtnumT.getText();
 		pass =passwordField.getText();
 		String pass2=passwordField2.getText();
		if (txtnom1.getText().isEmpty() || txtemail.getText().isEmpty() ||txtnom2.getText().isEmpty()
				  || txtnumT.getText().isEmpty()|| txtnumT.getText().isEmpty()  
				|| urlTF.getText().isEmpty()||passwordField.getText().isEmpty()||passwordField2.getText().isEmpty()) {
			 
				// test=false;
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("oops");
				alert1.setHeaderText(null);
				alert1.setContentText("remplir tous les champs");
				alert1.showAndWait();
				return;
 
		}

		if (isValid(email) == false) {
 			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Email");
			alert.setHeaderText(null);
			alert.setContentText("entrer une adresse email valide");
			alert.show();
			txtemail.requestFocus();
			txtemail.selectAll();
			return;
		}
		if ((isvalid(num_t) == false)) {
 			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Invalid numero");
			alert1.setHeaderText(null);
			alert1.setContentText("entrer une numero valide");
			alert1.show();
			txtemail.requestFocus();
			txtemail.selectAll();
			return;

		}
 
			User nouvelleUser = new User();
			nouvelleUser.setFirstName(nom);
			String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
			System.out.println(hashedPassword);
			 
				if (!pass.equals(pass2)) {
				    test=false;
				    JOptionPane.showMessageDialog(null, "Le deuxième mot de passe ne correspond pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
				    test=true;
				}
			 
			// Stocker le mot de passe haché dans la base de données
			nouvelleUser.setPassword(hashedPassword);
			// Stocker le mot de passe haché dans la base de données
			nouvelleUser.setPassword(hashedPassword);
			
			nouvelleUser.setLastName(nom2);
 
			nouvelleUser.setImage(file.getAbsolutePath());
			nouvelleUser.setEmail(txtemail.getText());
			 
			nouvelleUser.setPhoneNumber(num_t);
			 
			 
			boolean ok =false ;
			if(test==true) {
			    ServiceUser bs = new ServiceUser();
			    bs.ajouter(nouvelleUser);
			    Alert alert2 = new Alert(AlertType.CONFIRMATION);
			    alert2.setTitle("Information Dialog");
			    alert2.setHeaderText(null);
			    alert2.setContentText("User insérée avec succès!");
			    alert2.showAndWait();
			    ok=true; 
				if(ok==true) {
				    try {
				    	   
				    	// Fermer la première interface
				        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				        stage.close();
				        
				        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
				        Parent root = loader.load();
				        Scene scene = new Scene(root);
				        Stage stage2 = new Stage();
				        stage2.setScene(scene);
				        stage2.show();
				    } catch (IOException ex) 
				    {
				        ex.printStackTrace();
				    }
				}
			}
			else {
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("oops");
				alert1.setHeaderText(null);
				alert1.setContentText("verifierr tous les champs!!!");
				alert1.showAndWait();
			}
		
	}
	 
 

	@FXML
	private void importer(ActionEvent event) {
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez un fichier PNG");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
        	urlTF.setText(fichierSelectionne.getName());
            file = fichierSelectionne;
        }
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
