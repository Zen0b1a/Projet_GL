package fr.univ.projet.gl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;

import exceptions.CRSVideException;
import fr.univ.projet.gl.utils.ConnexionUtils;


public class FilmDAO {
	
	public FilmDAO()
	{
		
	}
	
	/*
	 * noms_colonnes : noms des colonnes de la table GL_film
	 * enregistrement : valeurs à enregistrées
	 */
	public void enregistrer(String table, List<String> noms_colonnes, List<String[]> enregistrement) throws SQLException
	{
		//Remise à zéro de la table
		PreparedStatement stmt = ConnexionUtils.getInstance().prepareStatement("DROP TABLE "+table);
		try
		{
			try
			{
				//Lancement de la suppression
				stmt.execute();	
			}
			catch(SQLException e)
			{
				//La table n'existe pas encore
			}
		}
		finally
		{
			stmt.close();
		}
		//Création de la table
		String creation = "CREATE TABLE "+table+"(";
		for(int j=0; j<noms_colonnes.size(); j++)
		{
			creation += noms_colonnes.get(j)+" VARCHAR2(100)";
			if(j<noms_colonnes.size()-1)
			{
				creation += ", ";
			}
		}
		creation += ")";
		stmt = ConnexionUtils.getInstance().prepareStatement(creation);
		try
		{
			//Lancement de la création
			stmt.execute();	
		}
		finally
		{
			stmt.close();
		}
		String insertion;
		for(int i=0; i<enregistrement.size(); i++)
		{
			//Construction de l'insertion
			insertion = "INSERT INTO "+table+" (";
			for(int j=0; j<noms_colonnes.size(); j++)
			{
				insertion += noms_colonnes.get(j);
				if(j<noms_colonnes.size()-1)
				{
					insertion += ", ";
				}
			}
			insertion += ") VALUES(";
			for(int j=0; j<noms_colonnes.size(); j++)
			{
				insertion += "?";
				if(j<noms_colonnes.size()-1)
				{
					insertion += ", ";
				}
			}
			insertion += ")";
			stmt = ConnexionUtils.getInstance().prepareStatement(insertion);
			try
			{
				//Insertion des valeurs pour l'insertion
				for(int j=0; j<noms_colonnes.size(); j++)
				{
					stmt.setObject(j+1, (Object)enregistrement.get(i)[j]);
				}
				//Lancement de l'insertion
				stmt.execute();	
			}
			finally
			{
				stmt.close();
			}
		}
	}
	
	/*
	 * Récupération des tuples de la table GL_film sous la forme d'un CachedRowSet
	 */
	public CachedRowSet recuperer(String table) throws SQLException, CRSVideException
	{
		CachedRowSet crs = null;
		crs = new CachedRowSetImpl();
		crs.setCommand("SELECT * FROM "+table);
        crs.execute(ConnexionUtils.getInstance());
        crs = crs.createCopy();
        if(crs.isLast())
        {
			throw new CRSVideException("Le CRS ne contient pas d'enregistrements.");
        }
        else
        {
        	return crs;
        }
	}
}
