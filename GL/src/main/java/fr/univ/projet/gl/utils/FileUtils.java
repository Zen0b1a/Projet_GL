package fr.univ.projet.gl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
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
	
	/*
	 * Enregistrment du Document passé en paramètre dans CHEMIN_FICHIER
	 */
	public static void ecrire(Document xml)
	{
		File file = new File(CHEMIN_FICHIER);
		try 
		{
			OutputStream out = new FileOutputStream(file);
			try 
			{
				TransformerFactory myFactory = TransformerFactory.newInstance();
				Transformer transformer = myFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(new DOMSource(xml), new StreamResult(out));
			} 
			catch (TransformerException e) 
			{
				e.printStackTrace();
			} 
			finally 
			{
				try 
				{
					out.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
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
