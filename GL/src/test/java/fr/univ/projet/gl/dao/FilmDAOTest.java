package fr.univ.projet.gl.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import exceptions.AttributNullException;
import exceptions.ColonneExistanteException;
import fr.univ.projet.gl.service.FilmService;
import fr.univ.projet.gl.utils.FileUtils;

public class FilmDAOTest 
{
	private static Logger Log = Logger.getLogger(FileUtils.class.getSimpleName());
	
	@BeforeClass
	public static void creationFichiers()
	{
		Log.info("Création des fichiers de test.");
		try 
		{
			File f = new File("src/test/java/fichier/stockage.xml");
			FileWriter fw = new FileWriter(f);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><films>" +
					"<film><ID>1</ID><TITRE>titre1</TITRE><DUREE>120</DUREE></film>" +
					"<film><ID>2</ID><TITRE>titre2</TITRE><DUREE>130</DUREE></film></films>\n");
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void suppressionFichiers()
	{
		Log.info("Suppression des fichiers de test.");
		File f = new File("src/test/java/fichier/stockage.xml");
		f.delete();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void enregistrementOK() throws SAXException, IOException, ParserConfigurationException, ColonneExistanteException, AttributNullException, SQLException
	{
		Log.info("Test de l'enregistrement sur la bonne table.");
		FilmService fs = new FilmService("src/test/java/fichier/stockage.xml");
		fs.sauvegarder();
		FilmDAO fdao = new FilmDAO();
		fdao.enregistrer("GL_film", fs.getNomsColonnes(), fs.getEnregistrement());
	}
	
	@Test
	public void enregistrementMauvaiseTable() throws SAXException, IOException, ParserConfigurationException, ColonneExistanteException, AttributNullException, SQLException
	{
		exception.expect(SQLException.class);
		Log.info("Test de l'enregistrement sur la mauvaise table.");
		FilmService fs = new FilmService("src/test/java/fichier/stockage.xml");
		fs.sauvegarder();
		FilmDAO fdao = new FilmDAO();
		fdao.enregistrer("GL_musique", fs.getNomsColonnes(), fs.getEnregistrement());
	}
	
	@Test
	public void recuperationOK() throws SQLException
	{
		FilmDAO fdao = new FilmDAO();
		Assert.assertNotNull("CRS non null", fdao.recuperer("GL_film"));
	}
	
	@Test
	public void recuperationMauvaiseTable() throws SQLException
	{
		exception.expect(SQLException.class);
		FilmDAO fdao = new FilmDAO();
		fdao.recuperer("GL_musique");
	}
}
