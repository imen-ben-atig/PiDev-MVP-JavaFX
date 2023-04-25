/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Reclamation;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author winxspace
 */
public class ServiceReclamation implements Iservice<Reclamation>{

Connection conn;
    public ServiceReclamation(){
        conn=DataSource.getInstance().getConn();
    }
    @Override
    public void ajouter(Reclamation t) {
        try{String query="INSERT INTO `reclamation`"
                        + "(`titre_rec`, `type_rec`,"
                        + " `date_rec`, `contenu_rec`, `statut_rec`,"
                        + " `username`) VALUES "
                        + "('"+t.getTitre_rec()+"',"
                        + "'"+t.getType_rec()+"','"+t.getDate_rec()+"',"
                        + "'"+t.getContenu_rec()+"','"+t.getStatut_rec()+"',"
                        +"'"+t.getUsername()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void modifier(Reclamation t, int id) {
        try {
            String query="UPDATE `reclamation` SET `titre_rec`='"+t.getTitre_rec()+"',"
                    + "`type_rec`='"+t.getType_rec()+"',"
                    + "`date_rec`='"+t.getDate_rec()+"',"
                    + "`contenu_rec`='"+t.getContenu_rec()+"',"
                    + "`statut_rec`='"+t.getStatut_rec()+"',"
                    + "`username`='"+t.getUsername()+"' WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void supprimer(int id) throws Exception {
        try {
            String query="DELETE FROM `reclamation` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficher() {
 List<Reclamation> lr=new ArrayList<>();
        try {
            String query="SELECT * FROM `reclamation`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reclamation r=new Reclamation();
                r.setDate_rec(rs.getDate("date_rec"));
                r.setContenu_rec(rs.getString("contenu_rec"));
                r.setUsername(rs.getString("username"));
                r.setType_rec(rs.getString("type_rec"));
                r.setTitre_rec(rs.getString("titre_rec"));
                r.setId(rs.getInt("id"));
                lr.add(r);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lr;    }
    public Reclamation getReclamationById(int id) {
    try {
        String req = "SELECT * FROM reclamation WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Reclamation r = new Reclamation();
            r.setId(rs.getInt("id"));
            r.setTitre_rec(rs.getString("titre_rec"));
            r.setType_rec(rs.getString("type_rec"));
            r.setDate_rec(rs.getDate("date_rec"));
            r.setContenu_rec(rs.getString("contenu_rec"));
            r.setStatut_rec(rs.getString("statut_rec"));
            r.setUsername(rs.getString("username"));
            return r;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}
    public List<String> getAllTitre(){
        List<String> ln=afficher().stream().map(tr->tr.getTitre_rec()).collect(Collectors.toList());
        return ln;
    }
    public int getReclamationByTitre(String titre){
        Reclamation t= afficher().stream().filter(tr->tr.getTitre_rec().equals(titre)).findFirst().orElse(null);
        if(t!=null){
            return t.getId();
        }
        else{
            return 0;
        }
    }
    
}
