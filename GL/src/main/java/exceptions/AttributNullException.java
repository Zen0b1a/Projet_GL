package exceptions;

public class AttributNullException extends Exception {

	/**
	 * Indique qu'un attribut du document XML n'est pas renseigné
	 */
	private static final long serialVersionUID = -8141897135727838239L;

	public AttributNullException()
	{
		super();
	}
	
	public AttributNullException(String message)
	{
		super(message);
	}
}
