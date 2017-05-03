package fr.univ.projet.gl.utils;

import java.sql.*;

/*
 * Classe pour la gestion de la connexion à la BD
 */

public class ConnexionUtils {
	final private String LOGIN = "ag092850";
    final private String MDP = "ag092850";
    final private String URL_FAC = "jdbc:oracle:thin:@butor:1521:ensb2016";
    final private String URL_EXTERIEUR = "jdbc:oracle:thin:@ufrsciencestech.u-bourgogne.fr:25561:ensb2016";
    private static Connection connect;
    private static ConnexionUtils single;
    
    private ConnexionUtils()
    {
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connect = DriverManager.getConnection(URL_FAC, LOGIN, MDP);
        } 
        catch (ClassNotFoundException e) 
        {
        	e.printStackTrace();
        } 
        catch (SQLException ex) 
        {
            try 
            {
                this.connect = DriverManager.getConnection(URL_EXTERIEUR, LOGIN, MDP);
            } 
            catch (SQLException e) 
            {
            	e.printStackTrace();
            }
        } 
    }
    
    public static Connection getInstance()
    {
        if(single==null)
        {
            single = new ConnexionUtils();
            System.out.println("Création d'instance.");
        }
        return connect;
    }
}
