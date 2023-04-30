package Services;

import java.util.List;

import Entities.User_admin;

public interface IServiceUser_admin {
	
	void ajouter(User_admin article);

	void supprimer(int id);

	void modifier(User_admin user_admin, int id);

	List<User_admin> afficherTous();

	User_admin rechercherUserParId(int id);

}
