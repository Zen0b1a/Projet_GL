package fr.univ.projet.gl.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.*;


public class ConnexionUtilsTest {
private static Logger Log = Logger.getLogger(ConnexionUtils.class.getSimpleName());
	
	@Test
	public void premierTest() throws SQLException
	{
		Log.info("Premier test.");
		Connection connect = ConnexionUtils.getInstance();
		Assert.assertNotNull(connect);
	}
}
