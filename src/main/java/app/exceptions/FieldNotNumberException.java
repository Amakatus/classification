package app.exceptions;

public class FieldNotNumberException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FieldNotNumberException(String message) {
        super(message);
    }

    public FieldNotNumberException() {
        super();
    }
}
