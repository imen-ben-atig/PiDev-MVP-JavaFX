/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author winxspace
 */
public class DataSource {
    final String URL ="jdbc:mysql://127.0.0.1:3306/GameGalaxy" ;
    final String USER ="root" ; 
    final String PWD ="" ; 
    private Connection conn ; 
    private static DataSource instance ;
        private DataSource ()
    { try {
        conn =  DriverManager.getConnection(URL,USER,PWD);
    System.out.println("connecter") ; 
    } 
    catch (SQLException ex) 
    {    Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex) ;    }
    
    }
    public static DataSource getInstance () {
    if (instance == null )
    {
    instance = new DataSource () ; 
    }
    else { 
    System.out.println("deja connecter") ; 
    }
    return instance ; 
    }

    public Connection getConn() {
        return conn;
    }
}
