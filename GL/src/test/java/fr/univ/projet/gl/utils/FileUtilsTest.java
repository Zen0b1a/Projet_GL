package fr.univ.projet.gl.utils;

import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

public class FileUtilsTest 
{
	private static Logger Log = Logger.getLogger(FileUtils.class.getSimpleName());
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testEcriture()
	{
		//exception.expect(//Exception);
	}
	
	@Test
	public void testLectureFichierOK()
	{
		//SAXException, IOException, ParserConfigurationException
		
	}
	
	@Test
	public void testLectureFichierInexistant()
	{
		
	}
	
	@Test
	public void testLectureFichierNonXML()
	{
		
	}
}
