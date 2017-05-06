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
            connect = DriverManager.getConnection(URL_FAC, LOGIN, MDP);
        } 
        catch (ClassNotFoundException e) 
        {
        	e.printStackTrace();
        } 
        catch (SQLException ex) 
        {
            try 
            {
                connect = DriverManager.getConnection(URL_EXTERIEUR, LOGIN, MDP);
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
    
    public static void closeConnect()
    {
        try 
        {
        	if(single!=null)
        	{
	            connect.close();
	            single = null;
	            System.out.println("Connexion fermée.");
        	}
        } 
        catch (SQLException e) 
        {
        	e.printStackTrace();
        }
    }
}
