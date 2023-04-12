/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;
import java.time.LocalDate;


/**
 *
 * @author Winxspace
 */
public class Produit {
    private int id;
    private int id_categorie_id;
    private String nom_produit;
    private float prix;
    private String description;
    private int stock;
    private int rating;
    private String img; 
    private LocalDate dns;
        
    
     public LocalDate getDns() {
        return dns;
    }

    public void setDns(LocalDate dns) {
        this.dns = dns;
    }
    
    
     
    public Produit(){}

    public Produit(int id, int id_categorie_id, String nom_produit, float prix, String description, int stock, int rating, String img) {
        this.id = id;
        this.id_categorie_id = id_categorie_id;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
        this.stock = stock;
        this.rating = rating;
        this.img = img;
    }

    public Produit(int id_categorie_id, String nom_produit, float prix, String description, int stock, int rating, String img) {
        this.id_categorie_id = id_categorie_id;
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
        this.stock = stock;
        this.rating = rating;
        this.img = img;
    }

    public Produit(String nom_produit, float prix, String description, int stock, int rating, String img) {
        this.nom_produit = nom_produit;
        this.prix = prix;
        this.description = description;
        this.stock = stock;
        this.rating = rating;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categorie_id() {
        return id_categorie_id;
    }

    public void setId_categorie_id(int id_categorie_id) {
        this.id_categorie_id = id_categorie_id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String image) {
        this.img = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", id_categorie_id=" + id_categorie_id + ", nom_produit=" + nom_produit + ", prix=" + prix + ", description=" + description + ", stock=" + stock + ", rating=" + rating + ", image=" + img + '}';
    }
    
 
    
}