package fr.univ.projet.gl.facade;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import exceptions.AttributNullException;
import exceptions.ColonneException;
import fr.univ.projet.gl.service.FilmService;

public class FilmFacade 
{
	private FilmService filmService;
	
	public FilmFacade() throws SAXException, IOException, ParserConfigurationException
	{
		this.filmService = new FilmService("src/main/java/fr/univ/projet/gl/fichier/stockage.xml");
	}
	
	/*
	 * Extrait les données du fichier xml pour les enregistrer dans la base
	 */
	public void fichierVersBase() throws ColonneException, AttributNullException, SQLException
	{
		this.filmService.sauvegarder();
		this.filmService.enregistrer();
	}
	
	/*
	 * Extrait les données de la base pour les enregistrer dans le fichier xml
	 */
	public void baseVersFichier() throws IOException, TransformerException, SQLException
	{
		this.filmService.extraire();
	}
}
