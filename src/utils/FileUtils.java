package utils;

import java.io.File;
import java.beans.*;
import java.io.IOException;

import javax.xml.*;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * Classe pour la gestion des fichiers XML
 */

public class FileUtils {
	
	final static private String CHEMIN_FICHIER = "src/fichier/stockage.xml";
	
	private FileUtils()
	{
		
	}
	
	private static class FileUtilsHolder
	{
		private final static FileUtils fileUtils = new FileUtils();
	}
	
	private static FileUtils getInstance()
	{
		return FileUtilsHolder.fileUtils;
	}
	
	public static void ecrire(Document xml)
	{
		
	}
	
	public static Document lire()
	{
		Document xml = null;
		return xml;
	}
	
	private static Document getXML()
	{
		//Récupération du fichier XML
		File fichier = new File(CHEMIN_FICHIER);
		Document xml = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			xml = builder.parse(fichier);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		return xml;
	}
}
