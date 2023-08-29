package in.fssa.jauntyrialto.exception;

public class ValidationException extends Exception {

	/**
	 * Creates a new ValidationException with the provided detail message.
	 * 
	 * @param message The extended message explaining the validation error.
	 */
	public ValidationException(String message) {

		super(message);

	}

	/**
	 * Creates a new ValidationException with the specified cause.
	 * 
	 * @param ex The cause of the validation exception.
	 */
	public ValidationException(Throwable ex) {
		super(ex);
	}

	/**
	 * Creates a new ValidationException with the provided detail message and cause.
	 * 
	 * @param message The extended message explaining the validation error.
	 * @param ex      The cause of the validation exception.
	 */
	public ValidationException(String message, Throwable ex) {
		super(message, ex);
	}
}
