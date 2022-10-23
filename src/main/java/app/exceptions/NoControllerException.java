package app.exceptions;

public class NoControllerException extends Exception {
	private String errorMessage;
	
	public NoControllerException(String message) {
		this.errorMessage = message;
	}

	@Override
	public String getMessage() {
		return this.errorMessage;
	}
	
	private static final long serialVersionUID = 1L;
}
