/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

public class CategorieService implements IService<Categorie> {

    private Connection conn;

    public CategorieService() {
        conn = DataSource.getInstance().getcnx();
    }

    @Override
    public void insert(Categorie t) {
        String requete = "INSERT INTO categorie (nom_categorie, etat, type) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
            pst.setInt(2, t.getEtat());
            pst.setString(3, t.getType());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String requete = "DELETE FROM categorie WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @Override
public void update(Categorie t) {
    String requete = "UPDATE categorie SET nom_categorie = ?, etat = ?, type = ? WHERE id = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, t.getNom_categorie());
        pst.setInt(2, t.getEtat());
        pst.setString(3, t.getType());
        pst.setInt(4, t.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @Override
    public List<Categorie> readAll() {
        List<Categorie> list = new ArrayList<>();
        String requete = "SELECT * FROM categorie";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Categorie(
                        rs.getInt("id"),
                        rs.getString("nom_categorie"),
                        rs.getInt("etat"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Categorie readById(int id) {
        Categorie categorie = null;
        String requete = "SELECT * FROM categorie WHERE id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                categorie = new Categorie(
                    rs.getInt("id"),
                    rs.getString("nom_categorie"),
                    rs.getInt("etat"),
                    rs.getString("type")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorie;
    }

    public ArrayList<Categorie> afficherAll()throws SQLException {
     ArrayList<Categorie> produitList = new ArrayList<>();
    String requete = "SELECT * FROM categorie";
    PreparedStatement pst = conn.prepareStatement(requete);
    ResultSet rs = pst.executeQuery();
    while (rs.next()) {
        Categorie Categorie = new Categorie();
        Categorie.setId(rs.getInt("id"));
        Categorie.setNom_categorie(rs.getString("nom_categorie")); // Fix column name
        Categorie.setEtat(rs.getInt("Etat"));
        Categorie.setType(rs.getString("type"));
        
        produitList.add(Categorie);
    }
    return produitList;
}

}