package fr.univ.projet.gl.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import exceptions.AttributNullException;
import exceptions.CRSVideException;
import exceptions.ColonneException;
import fr.univ.projet.gl.utils.FileUtils;

public class FilmServiceTest 
{
private static Logger Log = Logger.getLogger(FileUtils.class.getSimpleName());
	
	@BeforeClass
	public static void creationFichiers()
	{
		Log.info("Création des fichiers de test.");
		try 
		{
			File f = new File("src/test/java/fichier/stockageAttributNull.xml");
			FileWriter fw = new FileWriter(f);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><films>" +
					"<film><ID>1</ID><TITRE></TITRE><DUREE>120</DUREE></film>" +
					"<film><ID>2</ID><TITRE>titre2</TITRE><DUREE>130</DUREE></film></films>\n");
			fw.close();
			f = new File("src/test/java/fichier/stockageColonneDouble.xml");
			fw = new FileWriter(f);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><films>" +
					"<film><ID>1</ID><TITRE>titre1</TITRE><TITRE>titre1</TITRE><DUREE>120</DUREE></film>" +
					"<film><ID>2</ID><TITRE>titre2</TITRE><DUREE>130</DUREE></film></films>\n");
			fw.close();
			f = new File("src/test/java/fichier/stockageVide.xml");
			fw = new FileWriter(f);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><films></films>");
			fw.close();
			f = new File("src/test/java/fichier/stockage.xml");
			fw = new FileWriter(f);
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
		File f = new File("src/test/java/fichier/stockageAttributNull.xml");
		f.delete();
		f = new File("src/test/java/fichier/stockageColonneDouble.xml");
		f.delete();
		f = new File("src/test/java/fichier/stockageVide.xml");
		f.delete();
		f = new File("src/test/java/fichier/stockage.xml");
		f.delete();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void sauvegarderOK() throws SAXException, IOException, ParserConfigurationException, ColonneException, AttributNullException, SQLException
	{
		Log.info("Test de la sauvegarde OK");
		FilmService fs = new FilmService("src/test/java/fichier/stockage.xml");
		fs.sauvegarder();
	}
	
	@Test
	public void sauvegarderColonneDouble() throws SAXException, IOException, ParserConfigurationException, ColonneException, AttributNullException, SQLException
	{
		exception.expect(ColonneException.class);
		Log.info("Test de la sauvegarde avec une colonne en double.");
		FilmService fs = new FilmService("src/test/java/fichier/stockageColonneDouble.xml");
		fs.sauvegarder();
	}
	
	@Test
	public void sauvegarderAttributNull() throws SAXException, IOException, ParserConfigurationException, ColonneException, AttributNullException, SQLException
	{
		exception.expect(AttributNullException.class);
		Log.info("Test de la sauvegarde avec un attribut non renseigné.");
		FilmService fs = new FilmService("src/test/java/fichier/stockageAttributNull.xml");
		fs.sauvegarder();
	}
	
	@Test
	public void sauvegarderFichierVide() throws SAXException, IOException, ParserConfigurationException, ColonneException, AttributNullException, SQLException
	{
		exception.expect(NullPointerException.class);
		Log.info("Test de la sauvegarde avec un fichier vide.");
		FilmService fs = new FilmService("src/test/java/fichier/stockageVide.xml");
		fs.sauvegarder();
	}
}
