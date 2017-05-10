package fr.univ.projet.gl.service;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import exceptions.AttributNullException;
import exceptions.ColonneExistanteException;
import fr.univ.projet.gl.dao.FilmDAO;
import fr.univ.projet.gl.utils.FileUtils;

/*
 * Classe récupérant et envoyant des informations au fichier XML
 */

public class FilmService {
	private Document xml;
	private String chemin;
	private List<String> noms_colonnes;
	private List<String[]> enregistrement;

	public FilmService(String chemin) throws SAXException, IOException, ParserConfigurationException
	{
		this.chemin = chemin;
		this.xml = FileUtils.getXML(this.chemin);
		this.enregistrement = new ArrayList<>();
		this.noms_colonnes = new ArrayList<>();
	}
	
	public List<String> getNomsColonnes()
	{
		return this.noms_colonnes;
	}
	
	public List<String[]> getEnregistrement()
	{
		return this.enregistrement;
	}
	
	/*
	 * Enregistrement dans la base de données
	 */
	public void sauvegarder() throws ColonneExistanteException, AttributNullException
	{
		//Lecture de Document xml
		this.noms_colonnes.clear();
		this.enregistrement.clear();
		Element root = this.xml.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        int nbNode = nodes.getLength();
        for(int i=0; i<nbNode; i++)
        {
        	if(nodes.item(i).getNodeName().equals("film"))
        	{
	        	NodeList film = nodes.item(i).getChildNodes();
	        	String[] film_a_enregistrer = new String[(film.getLength())];
	        	int cpt = 0;
	        	for(int j=0; j<film.getLength(); j++)
	        	{
	        		Node attribut = film.item(j);
	        		if(!attribut.getNodeName().equals("#text"))
	        		{
	        			if(!this.noms_colonnes.contains(attribut.getNodeName()))
		        		{
		        			this.noms_colonnes.add(attribut.getNodeName());
		        		}
	        			else
	        			{
	        				if(i==0)
	        				{
	        					throw new ColonneExistanteException("Le nom de colonne "+attribut.getNodeName()+" est présent plusieurs fois.");
	        				}
	        				else
	        				{
	        					if(film.getLength()!=enregistrement.get(0).length)
	        					{
	        						throw new ColonneExistanteException("Le nombre de colonnes n'est pas le même pour tous les films.");
	        					}
	        				}
	        			}
		        		if(attribut.getTextContent()!=null && !attribut.getTextContent().equals(""))
		        		{
			        		film_a_enregistrer[cpt] = attribut.getTextContent();
			        		cpt++;
		        		}
		        		else
		        		{
		        			throw new AttributNullException("L'attribut n'a pas été renseigné.");
		        		}
	        		}
	        	}
	        	this.enregistrement.add(film_a_enregistrer);
        	}
        }
	}
	
	/*
	 * Enregistre le résultat obtenu dans la BD
	 */
	public void enregistrer() throws SQLException
	{
		FilmDAO filmDAO = new FilmDAO();
		filmDAO.enregistrer("GL_film", this.noms_colonnes, this.enregistrement);
	}
	
	/*
	 * Exctraction des données reçues de la base de données dans Document xml
	 */
	public void extraire() throws IOException, TransformerException, SQLException
	{
		FilmDAO filmDAO = new FilmDAO();
		CachedRowSet crs = filmDAO.recuperer("GL_film");
		Node racine = this.xml.getDocumentElement();
		racine.setNodeValue("films");
		int nbNode = racine.getChildNodes().getLength();
		for(int i=nbNode-1; i>=0; i--)
		{
			racine.removeChild(racine.getFirstChild());
		}
		//Lecture du crs pour stocker les données dans Document xml
		ResultSetMetaData metaData = crs.getMetaData();
		crs.beforeFirst();
		while(crs.next())
		{
			Node film = this.xml.createElement("film");
			for(int i=1; i<=metaData.getColumnCount(); i++)
			{
				Element e = this.xml.createElement(metaData.getColumnName(i));
				e.setTextContent(crs.getObject(i).toString());
				film.appendChild(e);
			}
			racine.appendChild(film);
		}
		this.ecrire();
	}
	
	/*
	 * Enregistrement dans le fichier xml
	 */
	private void ecrire() throws IOException, TransformerException
	{
		FileUtils.ecrire(this.xml, this.chemin);
	}
}
