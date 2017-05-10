package fr.univ.projet.gl.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import fr.univ.projet.gl.service.FilmService;

public class FileUtilsTest 
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
			f = new File("src/test/java/fichier/test.txt");
			fw = new FileWriter(f);
			fw.write("");
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
		f = new File("src/test/java/fichier/test.txt");
		f.delete();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testEcritureOK() throws IOException, TransformerException
	{
		Log.info("Test d'écriture du fichier avec un chemin correct.");
		Document d = null;
		try 
		{
			d = FileUtils.getXML("src/test/java/fichier/stockage.xml");
		} 
		catch (SAXException | ParserConfigurationException | IOException e) 
		{
			Log.info("Erreur lors de la lecture du fichier.");
			throw new IOException();
		}
		FileUtils.ecrire(d, "src/test/java/fichier/stockage.xml");
	}
	
	@Test
	public void testEcritureXMLNull() throws IOException, TransformerException
	{
		exception.expect(NullPointerException.class);
		Log.info("Test d'écriture du fichier null.");
		FileUtils.ecrire(null, "src/test/java/fichier/stockage.xml");
	}
	
	@Test
	public void testLectureFichierOK() throws SAXException, IOException, ParserConfigurationException
	{
		Log.info("Test de lecture du fichier avec un chemin correct.");
		FileUtils.getXML("src/test/java/fichier/stockage.xml");
	}
	
	@Test
	public void testLectureFichierInexistant() throws SAXException, ParserConfigurationException, IOException
	{
		exception.expect(IOException.class);
		Log.info("Test de lecture du fichier avec un chemin incorrect.");
		FileUtils.getXML("src/test/java/fichier/stock.xml");
	}
	
	@Test
	public void testLectureFichierNonXML() throws SAXException, IOException, ParserConfigurationException
	{
		exception.expect(SAXException.class);
		Log.info("Test de lecture du fichier qui n'est pas de type XML.");
		FileUtils.getXML("src/test/java/fichier/test.txt");
	}
}
