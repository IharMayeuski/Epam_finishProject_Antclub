package by.epam.club.exception;

/**
 * Exception of the level business logic
 * @author Maeuski Igor
 * @version 1.0
 */

public class DaoException extends Exception{

	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(Exception e) {
		super(e);
	}
	
	public DaoException(String message, Exception e) {
		super(message, e);
	}

}
