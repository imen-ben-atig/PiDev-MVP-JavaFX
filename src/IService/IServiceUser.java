/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.User;
import java.util.List;

/**
 *
 * @author The Nutorious BIG
 */
public interface IServiceUser {
    public void ajouterUser(User u);

    public List<User> afficherUser();

    public void modifierUser(int id, User newuser);

    public void supprimerUser(User u);

    public boolean getUser(User u);
    
}
