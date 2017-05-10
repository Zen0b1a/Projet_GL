package fr.univ.projet.gl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * Classe pour la gestion des fichiers XML
 */

public class FileUtils {
	
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
	
	/*
	 * Enregistrement du Document passé en paramètre à l'emplacement chemin
	 */
	public static void ecrire(Document xml, String chemin) throws IOException, TransformerException
	{
		File file = new File(chemin);
		OutputStream out = new FileOutputStream(file);
		try 
		{
			TransformerFactory myFactory = TransformerFactory.newInstance();
			Transformer transformer = myFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(xml), new StreamResult(out));
		} 
		finally 
		{
			out.close();
		}
	}

	/*
	 * Récupère le fichier XML situé au chemin donné
	 */
	public static Document getXML(String chemin) throws SAXException, IOException, ParserConfigurationException
	{
		//Récupération du fichier XML
		File fichier = new File(chemin);
		Document xml = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		xml = builder.parse(fichier);
		return xml;
	}
}
