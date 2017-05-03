package fr.univ.projet.gl.service;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.w3c.dom.*;

import fr.univ.projet.gl.dao.FilmDAO;
import fr.univ.projet.gl.utils.FileUtils;

/*
 * Classe récupérant et envoyant des informations au fichier XML
 */

public class FilmService {
	private Document xml;
	//https://openclassrooms.com/courses/java-et-le-xml/a-la-decouverte-de-dom-1
	public FilmService()
	{
		
	}
	
	/*
	 * Enregistrement dans la base de données
	 */
	public void sauvegarder()
	{
		List<String[]> enregistrement = new ArrayList<>();
		List<String> noms_colonnes = new ArrayList<>();
		//Lecture de Document xml
		Element root = this.xml.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        int nbNode = nodes.getLength();
        for(int i=0; i<nbNode; i++)
        {
        	System.out.println("Nouveau film");
        	Node n = nodes.item(i);
        	Element element = (Element)n;
        	NodeList film = element.getChildNodes();
        	String[] film_a_enregistrer = new String[film.getLength()];
        	for(int j=0; j<film.getLength(); j++)
        	{
        		Node attribut = film.item(j);
        		if(!noms_colonnes.contains(attribut.getNodeName()))
        		{
        			noms_colonnes.add(attribut.getNodeName());
        			System.out.println("Nom colonne : "+attribut.getNodeName());
        		}
        		film_a_enregistrer[j] = attribut.getTextContent();
        		System.out.println("Valeur : "+attribut.getTextContent());
        	}
        	enregistrement.add(film_a_enregistrer);
        }
		//Enregistrement
		//FilmDAO filmDAO = new FilmDAO();
		//filmDAO.enregistrer(noms_colonnes, enregistrement);
	}
	
	/*
	 * Exctraction des données reçues de la base de données dans Document xml
	 */
	public void extraire()
	{
		FilmDAO filmDAO = new FilmDAO();
		CachedRowSet crs = filmDAO.recuperer();
		//Lecture du crs pour stocker les données dans Document xml
		try 
		{
			ResultSetMetaData metaData = crs.getMetaData();
			crs.beforeFirst();
			while(crs.next())
			{
				for(int i=1; i<=metaData.getColumnCount(); i++)
				{
					
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Lecture du fichier XML dans Document xml
	 */
	public void lireFichier()
	{
		this.xml = FileUtils.lire();
	}
	
	/*
	 * Enregistrement dans le fichier xml
	 */
	public void ecrire()
	{
		FileUtils.ecrire(xml);
	}
}
