package Services;

import java.util.List;

import Entities.User;

public interface IServiceUser {
	
	void ajouter(User user);

	void supprimer(int id);

	void modifier(User user, int id);

	List<User> afficherTous();

	User rechercherUserParId(int id);

}
