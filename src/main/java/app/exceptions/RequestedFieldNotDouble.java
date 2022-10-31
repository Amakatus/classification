package app.exceptions;

public class RequestedFieldNotDouble extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RequestedFieldNotDouble(String message) {
		super(message);
	}
	
	public RequestedFieldNotDouble() {
		super();
	}
}
