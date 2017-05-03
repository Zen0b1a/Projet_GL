package fr.univ.projet.gl.service;
import java.util.ArrayList;
import java.util.List;

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
		
		//Enregistrement
		FilmDAO filmDAO = new FilmDAO();
		filmDAO.enregistrer(noms_colonnes, enregistrement);
	}
	
	public void extraire()
	{
		
	}
	
	/*
	 * Lecture du fichier XML
	 */
	public void lireFichier()
	{
		this.xml = FileUtils.lire();
	}
	
	public void ecrire()
	{
		
	}
}
