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

public class Categorieservice implements IService<Categorie> {
    
    private Connection conn;
    
    public Categorieservice() {
        conn = DataSource.getInstance().getcnx();
    }

    @Override
    public void insert(Categorie t) {
        String requete = "insert into categorie (nom_categorie, etat, type) values (?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
            pst.setInt(2, t.getEtat());
            pst.setString(3, t.getType());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String requete = "delete from categorie where id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Categorie t) {
        String requete = "update categorie set nom_categorie = ?, etat = ?, type = ? where id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
            pst.setInt(2, t.getEtat());
            pst.setString(3, t.getType());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> readAll() {
        List<Categorie> list = new ArrayList<>();
        String requete = "select * from categorie";
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
            Logger.getLogger(Categorieservice.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Categorieservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorie;
    }
}

