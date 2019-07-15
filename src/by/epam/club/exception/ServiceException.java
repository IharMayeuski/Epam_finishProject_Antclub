package by.epam.club.exception;

/**
 * Exception of the level business logic
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class  ServiceException extends Exception {

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
