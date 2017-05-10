import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import exceptions.AttributNullException;
import exceptions.ColonneException;
import fr.univ.projet.gl.facade.FilmFacade;
import fr.univ.projet.gl.utils.ConnexionUtils;

public class AppMain {
	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, TransformerException, SQLException, ColonneException, AttributNullException 
	{
		FilmFacade facade = new FilmFacade();
		String[] options = { "Extraire les données de la BD vers le fichier", 
				"Extraire les données du fichier vers la BD" };
        int selection = JOptionPane.showOptionDialog(null, "Quelle action exécuter ?", 
        		"Ajouter un enquêteur", JOptionPane.DEFAULT_OPTION, 
        		JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(selection==0)
        {
        	facade.baseVersFichier();
        }
        if(selection==1)
        {
        	facade.fichierVersBase();
        }
        ConnexionUtils.closeConnect();
	}
}
