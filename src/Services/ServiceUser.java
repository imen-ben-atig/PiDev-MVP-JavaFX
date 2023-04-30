package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import Entities.User_admin;
import Entities.User;
import db.DataBaseConnection;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jakarta.mail.*;
import jakarta.mail.internet.*;
public class ServiceUser {

	private Connection connection = DataBaseConnection.getInstance().getConnection();;

	public ServiceUser() {
		// TODO Auto-generated constructor stub
	}
	
	public void ajouter(User user) {
        String query = "INSERT INTO user (email,roles, password,is_verified, first_name, last_name, phone_number, image, status) VALUES (?, ?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
           
            statement.setString(1, user.getEmail());
            statement.setString(2, "user");
            statement.setString(3, user.getPassword());
            statement.setInt(4, 0);
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getImage());
            statement.setString(9, "enabled");
       
            statement.executeUpdate();
            System.out.println("ok ajouter");
               String subject = "Nouvelle compte ";
               String body = "Bonjour,\n\nUne nouvelle compte a crerr pour votre emial a  .\n\nCordialement,\n l'equipe de support";               
        }catch (SQLException ex) {
        	   System.out.println("errr "+ex);
        	
        }
	}
              
           
          
     
	 
	 
	
    public void supprimer(int id) {
        String query = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("ok");
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
    
    public void modifier(User user, int id) {
        String query = "UPDATE user SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
       
            statement.setString(1, "desable");
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
    public void Update(User user, int id) {
        String query = "UPDATE user SET first_name = ? ,last_name=?,phone_number=? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
       
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getPhoneNumber());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
    
    public ObservableList<User> afficherTous() {
         
		ObservableList<User> users = FXCollections.observableArrayList();

        try   { String query = "SELECT * FROM user";
        	Statement statement = connection.createStatement();

		 
		ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String role = resultSet.getString("roles");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                String phonenumber = resultSet.getString("phone_number");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");
                User user = new User(id, email, role, password, firstname, lastname, phonenumber, image, status);
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
        return users;
    }
    public boolean login(String username, String password) {
         
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isAuthenticated = false;

        try {
            // Etape 1 : se connecter � la base de donn�es
 
            // Etape 2 : Pr�parer la requ�te SQL
            String sql = "SELECT * FROM user WHERE first_name = ? AND password = ? ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
       

            // Etape 3 : Ex�cuter la requ�te SQL
            rs = stmt.executeQuery();

            // Etape 4 : V�rifier si l'utilisateur existe et que le mot de passe correspond
            if (rs.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            // G�rer les erreurs de connexion � la base de donn�es
            e.printStackTrace();
        }   
      

        return isAuthenticated;
    }
     

    public String getHashedPasswordForUser(String username) {
        String hashedPassword = null;
        try {
             PreparedStatement ps = connection.prepareStatement("SELECT password FROM user WHERE first_name = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hashedPassword = rs.getString("password");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
    public void bloquer(int userId) {
        try {
            String query = "UPDATE user SET bloque = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("bloquerr");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

	public String getRole(String username) {
		// TODO Auto-generated method stub
		String query="SELECT roles FROM user WHERE first_name = ?";
	 try {
	      PreparedStatement ps = connection.prepareStatement(query);
           
          ps.setString(1, username);
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
        	  return rs.getString("roles");
          }          
          System.out.println("bloquerr");
      } catch (SQLException ex) {
          System.err.println(ex.getMessage());
      }
	return null;
	   
		 
	}


    
}
