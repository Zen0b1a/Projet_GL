package utils;

import java.sql.*;

/*
 * Classe pour la gestion de la connexion à la BD
 */

public class ConnexionUtils {

	final static private String LOGIN = "ag092850";
	final static private String MDP = "ag092850";
	final static private String URL_FAC = "jdbc:oracle:thin:@butor:1521:ensb2016";
	final static private String URL_EXTERIEUR = "jdbc:oracle:thin:@ufrsciencestech.u-bourgogne.fr:25561:ensb2016";

	private ConnexionUtils() {

	}

	private static class ConnexionUtilsHolder {
		private final static ConnexionUtils connexionUtils = new ConnexionUtils();
	}

	public static ConnexionUtils getInstance() {
		return ConnexionUtilsHolder.connexionUtils;
	}

	public static Connection getConnexion() throws SQLException
	{
		Connection connect = null;
        
        try 
        {
			connect = getConnexion(URL_FAC);
		} 
        catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
        catch (SQLException e) 
        {
			try 
			{
				connect = getConnexion(URL_EXTERIEUR);
			} 
			catch (ClassNotFoundException e1) 
			{
				e1.printStackTrace();
			}
		} 
        
        return connect;
	}

	private static Connection getConnexion(String url) throws ClassNotFoundException, SQLException {
		Connection connect;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// Tentative de connexion depuis la fac
		connect = DriverManager.getConnection(url, LOGIN, MDP);
		return connect;
	}
}
