/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hassène
 */
public class UserService {
    private Connection con = MyConnection.getInstance().getConnection();

    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> UserList = new ArrayList<>();
        String requete = "SELECT * FROM user";
        PreparedStatement pst = con.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setPseudo(rs.getString("pseudo"));
            user.setEmail(rs.getString("email"));
            
            UserList.add(user);
        }
        return UserList;
    }


}
