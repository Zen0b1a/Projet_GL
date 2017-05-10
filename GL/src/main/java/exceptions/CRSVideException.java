package exceptions;

public class CRSVideException extends Exception {

	/**
	 * Indique que le CachedRowSet est vide
	 */
	private static final long serialVersionUID = -2520763233740843068L;

	public CRSVideException()
	{
		super();
	}
	
	public CRSVideException(String message)
	{
		super(message);
	}
}
