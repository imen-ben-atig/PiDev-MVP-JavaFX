/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.Produit;
import Gui.MainController;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import util.DataSource;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Winxspace
 */
public class Produitservice implements IService<Produit> {

    
private TableView<Produit> tableView;

    private Connection conn;
    
    public Produitservice(){
    conn=DataSource.getInstance().getcnx();
    }
    
    
    @Override
        public void insert(Produit t) {
        String requete = "insert into produit(nom_produit,prix,description,rating,stock,img)"
                + "values('" + t.getNom_produit() + "','" + t.getPrix()+ "','" + t.getStock()+ "','" + t.getImg()
               + "','" + t.getDescription() + "'," + t.getRating() + ")";
      //  Date.valueOf(t.getDns());
        try {
            Statement ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        }
        
      public void insertPrd(Produit p) {
    String requete = "insert into produit (nom_produit, prix, description, stock, rating, img) values (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pst = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, p.getNom_produit());
        pst.setFloat(2, p.getPrix());
        pst.setString(3, p.getDescription());
        pst.setInt(4, p.getStock());
        pst.setInt(5, p.getRating());
        pst.setString(6, p.getImg());

        int rowsInserted = pst.executeUpdate();

        if (rowsInserted > 0) {
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
            }conn.commit();

        }
    } catch (SQLException ex) {
        Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    @Override
    public void delete(int id) {
       String requete = "delete from produit where id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Produit t) {
     String requete = "update produit set nom_produit = ?, id_categorie = ?, prix = ?, stock = ?, img = ?, description = ?, rating = ? where id = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, t.getNom_produit());
            pst.setInt(2, t.getId_categorie_id());
            pst.setFloat(3, t.getPrix());
            pst.setInt(4, t.getStock());
            pst.setString(5, t.getImg());
            pst.setString(6, t.getDescription());
            pst.setInt(7, t.getRating());
            pst.setInt(8, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public List<Produit> readAll() {
        List<Produit> produits = new ArrayList<>();
    String requete = "SELECT * FROM produit";
    try {
        Statement ste = conn.createStatement();
        ResultSet rs = ste.executeQuery(requete);
        while (rs.next()) {
            int id = rs.getInt("id_produit");
            String nom_produit = rs.getString("nom_produit");
            float prix = rs.getFloat("prix");
            String description = rs.getString("description");
            int stock = rs.getInt("stock");
            int rating = rs.getInt("rating");
            String img = rs.getString("img");
            Produit produit = new Produit(id, nom_produit, prix, description, stock, rating, img);
            produits.add(produit);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Produitservice.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produits;
}

    @Override
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   public ArrayList<Produit> afficherAll() throws SQLException {
        ArrayList<Produit> ProduitList = new ArrayList<>();
        String requete = "SELECT * FROM evenement";
        PreparedStatement pst = conn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Produit produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setNom_produit(rs.getString("nom"));
            //cours.setDate(rs.getString("date"));
            produit.setDescription(rs.getString("description"));
            produit.setPrix(rs.getInt("prix"));
            produit.setStock(rs.getInt("duree"));
            produit.setRating(rs.getInt("Rating"));
            produit.setImg(rs.getString("image"));
            ProduitList.add(produit);
        }
        return ProduitList;
    }
    
}
        
   