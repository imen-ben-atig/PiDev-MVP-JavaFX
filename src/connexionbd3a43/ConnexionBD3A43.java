/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connexionbd3a43;

import util.DataSource;

/**
 *
 * @author Winxspace
 */
public class ConnexionBD3A43 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1= DataSource.getInstance();
        System.out.println(ds1);
         DataSource ds2= DataSource.getInstance();
        System.out.println(ds2);
    }
    
}
