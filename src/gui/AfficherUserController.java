package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import Entities.User_admin;
import Entities.User;
import Services.ServiceUser_admin;
import db.DataBaseConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AfficherUserController implements Initializable {
   
	Connection connection = DataBaseConnection.getInstance().getConnection();
 

	public ObservableList<Comment> afficherT() {

		ObservableList<Comment> users = FXCollections.observableArrayList();

		try {
			String query = "SELECT comment.*, user.first_name FROM comment JOIN user ON comment.user_id = user.id";
			;
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int user_admin_id = resultSet.getInt("user_admin_id");
				int user_id = resultSet.getInt("user_id");
				String content = resultSet.getString("content");
				Timestamp timestamp = resultSet.getTimestamp("created_at");
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				String firstName = resultSet.getString("first_name");
				Comment comment = new Comment(id, user_id, user_admin_id, content, localDateTime);
				comment.setUserName(firstName);
				users.add(comment);
				;
			}
		} catch (SQLException e) {
			System.err.println("Error executing SQL query: " + e.getMessage());
		}
		return users;
	}

	@FXML
	private ListView<User_admin> listView;
       /* @FXML
        private ComboBox<String> profilbox;*/
	public void initialize(URL location, ResourceBundle resources) {
                    //profilbox.getItems().addAll("Profil", "Logout");
		ServiceUser_admin es = new ServiceUser_admin();
		List<User_admin> User_admin = es.afficherTous();

		listView.setCellFactory((Callback<ListView<User_admin>, ListCell<User_admin>>) new Callback<ListView<User_admin>, ListCell<User_admin>>() {
					public ListCell<User_admin> call(ListView<User_admin> param) {
						return new ListCell<User_admin>() {
							@Override
							protected void updateItem(User_admin item, boolean empty) {
								super.updateItem(item, empty);

								if (empty || item == null) {
									setText(null);
									setGraphic(null);
								} else {
									VBox vbox = new VBox();
									vbox.setSpacing(10);

									Label titleLabel = new Label(item.getTitle());
									titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

									Label contentLabel = new Label(" Likes: " + item.getLike());
									contentLabel.setStyle("-fx-font-size: 14;");
									FontAwesomeIconView likeBtn = new FontAwesomeIconView(FontAwesomeIcon.THUMBS_UP);

									// D�finir la couleur de l'ic�ne en bleu
									likeBtn.setFill(Color.BLUE);
									likeBtn.setGlyphSize(30); // D�finir la taille de l'ic�ne du bouton � 30 pixels
									likeBtn.setOnMouseClicked(arg0 -> {
										ServiceUser_admin es = new ServiceUser_admin();
										
										
										item.setLike(item.getLike() + 1);

										// update UI to reflect the change
										contentLabel.setText(" Likes: " + item.getLike());

										// disable the like button
										likeBtn.setDisable(true);
									});
									
									ListView<Comment> commentListView = new ListView<Comment>();
									commentListView.setItems(afficherT());
									commentListView.setPrefHeight(200);
									commentListView
											.setCellFactory((ListView<Comment> param) -> new ListCell<Comment>() {
												@Override
												protected void updateItem(Comment comment, boolean empty) {
													super.updateItem(comment, empty);

													if (empty || comment == null) {
														setText(null);
														setGraphic(null);
													} else {
														VBox vbox = new VBox();
														vbox.setSpacing(5);

														Label contentLabel = new Label(comment.getContent());
														contentLabel.setWrapText(true);

														Label authorLabel = new Label("By " + comment.getUserName()
																+ " at " + comment.getCreatedAt().toString());

														vbox.getChildren().addAll(contentLabel, authorLabel);

														setGraphic(vbox);
													}
												}
											});

									TextArea commentTextArea = new TextArea();
									commentTextArea.setPromptText("Add a comment...");
									commentTextArea.setWrapText(true);
									commentTextArea.setPrefHeight(50);

									Button commentButton = new Button("Comment");
									commentButton.setOnAction(event -> {
										String commentText = commentTextArea.getText();

										LocalDateTime now = LocalDateTime.now();
										Timestamp timestamp = Timestamp.valueOf(now);
										// update database to store the comment
										Connection connection = DataBaseConnection.getInstance().getConnection();
										String query = "INSERT INTO comment (user_admin_id, user_id, content, created_at) VALUES (?, ?, ?, ?)";
										try (PreparedStatement statement = connection.prepareStatement(query)) {
											statement.setInt(1, item.getId());
											statement.setInt(2, item.getUserId());
											statement.setString(3, commentText);
											statement.setTimestamp(4, timestamp);
											statement.executeUpdate();
											System.out.println("  adding comment: ");
											commentListView.setItems(afficherT());
											commentListView.refresh();
										} catch (SQLException ex) {
											System.out.println("Error adding comment: " + ex.getMessage());

										}
									});

									vbox.getChildren().addAll(titleLabel, contentLabel, likeBtn, commentListView,
											commentTextArea, commentButton);

									setGraphic(vbox);
								}
							}

						};

					}
				});

		// Ajouter les �v�nements � la liste
		listView.getItems().addAll(User_admin);
	}
        @FXML
        public void logout(){}
}
