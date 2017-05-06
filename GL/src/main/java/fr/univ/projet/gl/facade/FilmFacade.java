package fr.univ.projet.gl.facade;
import fr.univ.projet.gl.service.FilmService;

public class FilmFacade 
{
	private FilmService filmService;
	
	public FilmFacade()
	{
		this.filmService = new FilmService();
	}
	
	/*
	 * Extrait les données du fichier xml pour les enregistrer dans la base
	 */
	public void fichierVersBase()
	{
		this.filmService.sauvegarder();
	}
	
	/*
	 * Extrait les données de la base pour les enregistrer dans le fichier xml
	 */
	public void baseVersFichier()
	{
		this.filmService.extraire();
	}
}
