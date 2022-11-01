package app.exceptions;

import app.graphics.models.datas.data.Data;

public class FieldToDistanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FieldToDistanceException(String fieldName) {
		super("Ignoring " + fieldName + " because cant find "+fieldName+Data.TO_DOUBLE+" method that return a valid distance.");
	}
	
	public FieldToDistanceException() {
		super();
	}
}
