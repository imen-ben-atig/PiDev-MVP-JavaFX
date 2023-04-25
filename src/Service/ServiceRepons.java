/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Reclamation;
import Entite.Repons;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author winxspace
 */
public class ServiceRepons implements Iservice<Repons>{
    Connection conn;
    public ServiceRepons(){
        conn=DataSource.getInstance().getConn();
    }

    public ServiceRepons(LocalDate value, String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void ajouter(Repons t) {
        try{String query = "INSERT INTO `repons` "
             + "(`id_reclamation_id`, `date_rep`, `status_rep`, `contenu_rep`) "
             + "VALUES "
             + "('"+t.getId_reclamation_id()+"', '"+t.getDate_rep()+"', "
             + "'"+t.getStatus_rep()+"', '"+t.getContenu_rep()+"')";
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ServiceRepons.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void modifier(Repons t, int i) {
          try {
            String query="UPDATE `repons` SET "
                    +"`id_reclamation_id`="+t.getId_reclamation_id()+","
                    + "`status_rep`='"+t.getStatus_rep()+"',"
                    + "`date_rep`='"+t.getDate_rep()+"',"
                    + "`contenu_rep`='"+t.getContenu_rep()+
                   "' WHERE id="+t.getId();
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRepons.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void supprimer(int id) throws Exception {
       try {
            String query="DELETE FROM `repons` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRepons.class.getName()).log(Level.SEVERE, null, ex);
        } }

    @Override
    public List<Repons> afficher() {
     List<Repons> lre=new ArrayList<>();
        try {
            String query="SELECT * FROM `repons`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Repons re=new Repons();
                re.setId_reclamation_id(rs.getInt("id_reclamation_id"));
                re.setDate_rep(rs.getDate("date_rep"));
                re.setStatus_rep(rs.getString("status_rep"));
                re.setContenu_rep(rs.getString("contenu_rep"));
                re.setId(rs.getInt("id"));
                lre.add(re);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRepons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lre; 
    }


    
}
