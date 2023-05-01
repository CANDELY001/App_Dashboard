/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HP
 */
public class connexion {
      public static Connection seConnecter() throws Exception{
        try {
            Connection con=null;
            String url="jdbc:oracle:thin:@localhost:1521/XE";
            String user="ocp";
            String mdp="123";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection(url,user,mdp);
            System.out.println("Connexion etablie");
            return con;
        } catch (ClassNotFoundException ex) {
           throw new Exception ("impossible de charger le driver.");
        }catch (SQLException ex) {
           throw new Exception ("impossible de se connecter ."+ex.getMessage());
        }
    }
    
}
     
