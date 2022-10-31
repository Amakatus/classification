package app.exceptions;

public class FieldToDistanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldToDistanceException(String fieldName) {
		super("Ignoring " + fieldName + " because cant find "+fieldName+"ToDistance method that return a valid distance.");
	}
	
	public FieldToDistanceException() {
		super();
	}
}
