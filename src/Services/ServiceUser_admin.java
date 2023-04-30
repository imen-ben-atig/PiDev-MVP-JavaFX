package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import Entities.User_admin;
 import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import db.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceUser_admin {

	private Connection connection = DataBaseConnection.getInstance().getConnection();;

	public ServiceUser_admin() {
	}

	public void ajouter(User_admin user_admin) {
		try {
			String query = "INSERT INTO user_admin (user_id, title, content,likes,image_code_qr) VALUES (?, ?, ?, ?,?);";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, user_admin.getUserId());
			preparedStatement.setString(2, user_admin.getTitle());
			preparedStatement.setString(3, user_admin.getContent());
			preparedStatement.setInt(4, user_admin.getLike());
			preparedStatement.setString(5, user_admin.getImage_code_qr());
			preparedStatement.executeUpdate();
			System.out.println("L'user_admin a �t� ajout� avec succ�s !");
                         // Call function to send email
               
           
                String accountSid = "AC37b0be76e041951e9ed53dfc9035d5ca";
               String authToken = "163686bef95fe3680eb8ff15c79738f0";
               Twilio.init(accountSid, authToken);
               String fromPhoneNumber = "+16813256343"; // Change this to your Twilio phone number
               String toPhoneNumber = "+21655947170"; // Change this to the user's phone number
               String smsBody = "Bonjour, un nouvelle user_admin a ajouter pour vous. Cordialement, L'�quipe de support";
        Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), smsBody).create();
               System.out.println("SMS sent successfully to " + toPhoneNumber);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'ajout de l'user_admin.");
		}
                    // Send SMS to user
               
	}

	public void supprimer(int id) {
		try {
			String query = "DELETE FROM user_admin WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("L'user_admin a �t� supprim� avec succ�s !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de l'user_admin.");
		}
	}

	public void modifier(User_admin user_admin, int id) {
		try {
			String query = "UPDATE user_admin SET user_id = ?, title = ?, content = ?, likes = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_admin.getUserId());
			preparedStatement.setString(2, user_admin.getTitle());
			preparedStatement.setString(3, user_admin.getContent());
			preparedStatement.setInt(4, user_admin.getLike());
			preparedStatement.setInt(5, id);
			preparedStatement.executeUpdate();
			System.out.println("L'user_admin a �t� modifi� avec succ�s !");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification de l'user_admin.");
		}
	}

	public ObservableList<User_admin> afficherTous() {
		ObservableList<User_admin> user_admins = FXCollections.observableArrayList();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_admin");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int userId = resultSet.getInt("user_id");
				String title = resultSet.getString("title");
				String content = resultSet.getString("content");
				int like = resultSet.getInt("likes");
				User_admin user_admin = new User_admin(id, userId, title, content, like);
				user_admins.add(user_admin);
				System.out.println(user_admin.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_admins;
	}

	public User_admin rechercherUserParId(int id) throws SQLException {
		User_admin user_admin = null;
		String query = "SELECT * FROM user_admin WHERE id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int like = rs.getInt("likes");
				user_admin = new User_admin(id, userId, title, content, like);
			}

			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user_admin;
	}

	
}
