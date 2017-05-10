package exceptions;

public class ColonneException extends Exception {

	/**
	 * Indique qu'un nom de colonne est en double
	 */
	private static final long serialVersionUID = 6883733010858452500L;
	
	public ColonneException()
	{
		super();
	}
	
	public ColonneException(String message)
	{
		super(message);
	}
}
