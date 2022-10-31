package app.exceptions;

public class FieldToDistanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldToDistanceException(String message) {
		super(message);
	}
	
	public FieldToDistanceException() {
		super();
	}
}
