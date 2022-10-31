package app.exceptions;

public class FieldNotDoubleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldNotDoubleException(String message) {
		super(message);
	}
	
	public FieldNotDoubleException() {
		super();
	}
}
